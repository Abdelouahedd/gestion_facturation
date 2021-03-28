package com.ae.gestion.facture.security.service;

import com.ae.gestion.facture.security.domaine.UserClient;
import com.ae.gestion.facture.security.jwt.JwtConfig;
import com.ae.gestion.facture.security.jwt.JwtUtil;
import com.ae.gestion.facture.security.repository.UserRepository;
import com.ae.gestion.facture.security.web.dto.AuthenticationResp;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserClientService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final ApplicationUserService applicationUserService;
    private final JwtUtil jwtUtil;
    private final JwtConfig jwtConfig;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResp login(UserClient login) throws BadCredentialsException {
        try {
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
            final UserDetails userDetails = this.applicationUserService.loadUserByUsername(login.getUsername());
            final String jwt = this.jwtUtil.generateToken(userDetails);
            final String refreshToken = this.jwtUtil.generateRefreshToken(userDetails);
            return new AuthenticationResp(jwt, refreshToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password ", e);
        }
    }

    public AuthenticationResp refreshToken(String authorizationToken) throws Exception {
        try {
            if (authorizationToken != null && authorizationToken.startsWith(this.jwtConfig.getTokenPrefix())) {
                String jwt = authorizationToken.substring(this.jwtConfig.getTokenPrefix().length());
                String username = this.jwtUtil.extractUsername(jwt);
                final UserDetails userDetails = this.applicationUserService.loadUserByUsername(username);
                final String newJwt = this.jwtUtil.generateToken(userDetails);
                return new AuthenticationResp(newJwt, jwt);
            } else {
                throw new Exception("Vous ne pouvez pas acceder ");
            }
        } catch (Exception e) {
            throw new Exception("Vous ne pouvez pas acceder   ");
        }
    }

    public UserClient addUser(UserClient userClient) throws Exception {
        try {
            String password = userClient.getPassword();
            String newPassword = passwordEncoder.encode(password);
            userClient.setPassword(newPassword);
            return this.userRepository.saveAndFlush(userClient);
        } catch (Exception e) {
            throw new Exception("User not saved");
        }
    }
}
