package com.ae.gestion.facture.security.web;

import com.ae.gestion.facture.security.domaine.UserClient;
import com.ae.gestion.facture.security.service.UserClientService;
import com.ae.gestion.facture.security.web.dto.AuthenticationResp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserRestController {
    private final UserClientService userClientService;

    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResp> login(@RequestBody UserClient login) {
        AuthenticationResp token = this.userClientService.login(login);
        return ResponseEntity.ok(token);
    }

    @GetMapping(path = "/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestHeader(value = "x-auth-token") String req) {
        try {
            return ResponseEntity.ok(this.userClientService.refreshToken(req));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied (Token not valid)");
        }
    }

    @PostMapping(path = "/user/signup")
    public ResponseEntity<UserClient> signup(@RequestBody UserClient signup) throws Exception {
        UserClient userClient = this.userClientService.addUser(signup);
        return ResponseEntity.ok(userClient);
    }
}
