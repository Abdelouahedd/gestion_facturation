package com.ae.gestion.facture.security.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResp {
    private String jwt;
    private String refreshJwt;
}
