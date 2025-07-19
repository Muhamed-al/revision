package com.tekup.revision.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.IdGeneratorType;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DataCenter {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dateFabrication;
    private long espaceLibreDisque;
    private String region;

    @OneToMany(mappedBy = "dataCenter")
    private List<VirtualMachine> virtualMachines;

}
