package com.ae.gestion.security.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResp {
    private String jwt;
    private String refreshJwt;
}
