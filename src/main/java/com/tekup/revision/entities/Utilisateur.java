package com.tekup.revision.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomUser;
    private String prenomUser;
    private String login;
    private String pwd;

    @OneToMany
    private List<VirtualMachine> virtualMachines;

}
