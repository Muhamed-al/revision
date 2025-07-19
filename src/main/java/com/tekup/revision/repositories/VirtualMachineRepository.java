package com.tekup.revision.repositories;

import com.tekup.revision.entities.EtatVM;
import com.tekup.revision.entities.VirtualMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VirtualMachineRepository extends JpaRepository<VirtualMachine, Integer> {

    // Partie 2 : A. Question 1
    List<VirtualMachine> findByEtatVM(EtatVM etatVM);

    // Partie 2 : A. Question 2
    @Query("SELECT v from VirtualMachine v WHERE v.tailleDisque > :tailleDisque")
    List<VirtualMachine> findByTailleDisqueGreaterThan(int tailleDisque);

    // Partie 2 : A. Question 3
    List<VirtualMachine> findByEtatVMAndOsIgnoreCaseAndTailleDisqueLessThanEqual(
            EtatVM etatVM,
            String os,
            long tailleMax
    );

}
