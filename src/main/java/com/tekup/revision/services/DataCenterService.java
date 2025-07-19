package com.tekup.revision.services;


import com.tekup.revision.entities.DataCenter;
import com.tekup.revision.repositories.DataCenterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DataCenterService {


    private final DataCenterRepository dataCenterRepository;

    public DataCenterService(DataCenterRepository dataCenterRepository) {
        this.dataCenterRepository = dataCenterRepository;
    }

    // Partie 3 : B Question 1
    public void ajouterDataCenter(DataCenter dataCenter){
        dataCenterRepository.save(dataCenter);
    }


    // Partie 3 : B Question 2
    public List<DataCenter> getDataCentersBetweenDates(LocalDate from, LocalDate to) {
        return dataCenterRepository.findByDateFabricationBetween(from, to);
    }

    // Partie 3 : B Question 3
    public List<DataCenter> filterDataCenters(LocalDate startDate, LocalDate endDate, long minSpace, String region) {
        return dataCenterRepository.findByDateFabricationBetweenAndEspaceLibreDisqueGreaterThanEqualAndRegionIgnoreCase(startDate, endDate, minSpace, region);
    }

    // Partie 3 : B Question 4
    public List<String> getRegionsAfterDate(LocalDate date) {
        return dataCenterRepository.findRegionsAfterDate(date);
    }


}
