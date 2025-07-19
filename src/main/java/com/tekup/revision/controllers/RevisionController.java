package com.tekup.revision.controllers;


import com.tekup.revision.entities.DataCenter;
import com.tekup.revision.entities.EtatVM;
import com.tekup.revision.entities.Utilisateur;
import com.tekup.revision.entities.VirtualMachine;
import com.tekup.revision.services.DataCenterService;
import com.tekup.revision.services.UtilisateurService;
import com.tekup.revision.services.VirtualMachineService;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.rmi.dgc.VMID;
import java.time.LocalDate;
import java.util.List;

// Partie 5 : Controller
@RestController
public class RevisionController {

    private final DataCenterService dcService;
    private final UtilisateurService userService;
    private final VirtualMachineService vmService;

    public RevisionController(DataCenterService dcService, UtilisateurService userService, VirtualMachineService vmService) {
        this.dcService = dcService;
        this.userService = userService;
        this.vmService = vmService;
    }

    @PostMapping("/user")
    public void ajouterUser(@RequestBody Utilisateur user){
        userService.ajouterUser(user);
    }

    @PostMapping("/vm")
    public void ajouterVm(@RequestBody VirtualMachine vm){
        vmService.ajouterVM(vm);
    }

    @PostMapping("/datacenter")
    public void ajouterDc(@RequestBody DataCenter dc){
        dcService.ajouterDataCenter(dc);
    }

    @PutMapping("/vm/{idVm}/affecter/{idUser}")
    public void affecterVmUser(@PathVariable int idVm , @PathVariable int idUser){
        vmService.affecterVmUser(idVm, idUser);
    }

    @PutMapping("/vm/{idVm}/affecter")
    public void affecterVmDc(@PathVariable int idVm) {
        vmService.affecterVmDc(idVm);
    }


    @PutMapping("/vm/{idVm}/demarrer")
    public void demarrerInstanceUser(@PathVariable int idVm) {
        vmService.demarrerInstanceUser(idVm);
    }

    @PutMapping("/vm/{idVm}/arreter")
    public void arreterInstanceUser(@PathVariable int idVm) {
        vmService.arreterInstanceUser(idVm);
    }

    @GetMapping("/vm/etat/{etat}")
    public List<VirtualMachine> vmsByEtat(@PathVariable EtatVM etat) {
        return vmService.getVMsByEtat(etat);
    }

    @GetMapping("/vm/min/{tailleDisque}")
    public List<VirtualMachine> vmsByTailleDisqueGreaterThan(@PathVariable int tailleDisque) {
        return vmService.getVMsByTailleDisqueGreaterThan(tailleDisque);
    }

    @GetMapping("/vm/filtre")
    public List<VirtualMachine> filterVMs(
            @RequestParam(required = false) EtatVM etatVM,
            @RequestParam(required = false) String os,
            @RequestParam(required = false) long tailleMax) {

        return vmService.filterVMs(etatVM, os, tailleMax);
    }



    @GetMapping("/datacenter/between")
    public List<DataCenter> getDataCentersBetweenDates(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        return dcService.getDataCentersBetweenDates(from, to);
    }

    @GetMapping("/datacenter/filtre")
    public List<DataCenter> filterDataCenters(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long minSpace, @RequestParam String region) {
        return dcService.filterDataCenters(startDate, endDate, minSpace, region);
    }

    @GetMapping("/datacenter/regions")
    public List<String> getRegionsAfterDate(@RequestParam LocalDate date) {
        return dcService.getRegionsAfterDate(date);
    }

}
