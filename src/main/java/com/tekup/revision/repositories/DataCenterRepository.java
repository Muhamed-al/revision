package com.tekup.revision.repositories;

import com.tekup.revision.entities.DataCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DataCenterRepository extends JpaRepository<DataCenter, Integer> {


    // Partie 2 : B. Question 1
    List<DataCenter> findByDateFabricationBetween(LocalDate from, LocalDate to);

    // Partie 2 : B. Question 2
    List<DataCenter> findByDateFabricationBetweenAndEspaceLibreDisqueGreaterThanEqualAndRegionIgnoreCase(
            LocalDate startDate,
            LocalDate endDate,
            long minSpace,
            String region
    );

    // Partie 2 : B. Question 3
    @Query("SELECT d.region FROM DataCenter d WHERE d.dateFabrication > :date")
    List<String> findRegionsAfterDate(@Param("date") LocalDate date);
}
