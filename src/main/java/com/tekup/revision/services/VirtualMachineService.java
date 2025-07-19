package com.tekup.revision.services;


import com.tekup.revision.entities.DataCenter;
import com.tekup.revision.entities.EtatVM;
import com.tekup.revision.entities.Utilisateur;
import com.tekup.revision.entities.VirtualMachine;
import com.tekup.revision.exceptions.ResourceNotFoundException;
import com.tekup.revision.repositories.DataCenterRepository;
import com.tekup.revision.repositories.UtilisateurRepository;
import com.tekup.revision.repositories.VirtualMachineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VirtualMachineService {

    private final VirtualMachineRepository vmRepository;
    private final UtilisateurRepository userRepository;
    private final DataCenterRepository dcRepository;
    public VirtualMachineService(VirtualMachineRepository vmRepository, UtilisateurRepository userRepository, DataCenterRepository dcRepository) {
        this.vmRepository = vmRepository;
        this.userRepository = userRepository;
        this.dcRepository = dcRepository;
    }

    // Partie 3 : A Question 1
    public void ajouterVM(VirtualMachine vm){
        vmRepository.save(vm);
    }

    // Partie 3 : A Question 4
    public void affecterVmUser(int idVm , int idUser){
        VirtualMachine vm = vmRepository.findById(idVm)
                .orElseThrow( () -> new ResourceNotFoundException("VM not found with id : " + idVm));

        Utilisateur user  = userRepository.findById(idUser)
                .orElseThrow( ()-> new ResourceNotFoundException("User not found with id : " + idUser));

        user.getVirtualMachines().add(vm);
        userRepository.save(user);
    }

    // Partie 3 : A Question 5
    public void affecterVmDc(int idVm){
        VirtualMachine vm = vmRepository.findById(idVm)
                .orElseThrow( () -> new ResourceNotFoundException("VM not found with id : " + idVm));
        dcRepository.findAll()
                .stream()
                .filter(dc -> dc.getEspaceLibreDisque() > vm.getTailleDisque())
                .findFirst()
                .ifPresent(dc ->{
                    vm.setDataCenter(dc);
                    dc.setEspaceLibreDisque(dc.getEspaceLibreDisque() - vm.getTailleDisque());
                    vmRepository.save(vm);
                    dcRepository.save(dc);
                });
    }

    // Partie 3 : A Question 6
    public void demarrerInstanceUser(int idVm){
        VirtualMachine vm = vmRepository.findById(idVm)
                .orElseThrow(() -> new ResourceNotFoundException("VM not found with id : " + idVm));
        vm.setEtatVM(EtatVM.RUNNING);
        vmRepository.save(vm);
    }

    // Partie 3 : A Question 7
    public void arreterInstanceUser(int idVm){
        VirtualMachine vm = vmRepository.findById(idVm)
                .orElseThrow(() -> new ResourceNotFoundException("VM not found with id : " + idVm));
        vm.setEtatVM(EtatVM.STOPPED);
        vmRepository.save(vm);
    }

    // Partie 3 : A Question 8
    public List<VirtualMachine> getVMsByEtat(EtatVM etatVM) {
        return vmRepository.findByEtatVM(etatVM);
    }

    // Partie 3 : A Question 9
    public List<VirtualMachine> getVMsByTailleDisqueGreaterThan(int tailleDisque) {
        return vmRepository.findByTailleDisqueGreaterThan(tailleDisque);
    }

    // Partie 3 : A Question 10
    public List<VirtualMachine> filterVMs(EtatVM etatVM, String os, long tailleMax) {
        return vmRepository.findByEtatVMAndOsIgnoreCaseAndTailleDisqueLessThanEqual(etatVM, os, tailleMax);
    }
}
