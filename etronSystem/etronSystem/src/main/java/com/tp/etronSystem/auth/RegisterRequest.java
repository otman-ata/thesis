package com.tp.etronSystem.auth;

import com.tp.etronSystem.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String prenom;
    private String nom;
    private String email;
    private String password;

    private String vin;
    private Long idModele;

    private Role role;
}
