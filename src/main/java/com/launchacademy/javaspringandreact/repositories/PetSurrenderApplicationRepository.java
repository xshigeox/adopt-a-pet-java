package com.launchacademy.javaspringandreact.repositories;

import com.launchacademy.javaspringandreact.models.PetSurrenderApplication;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PetSurrenderApplicationRepository extends
    PagingAndSortingRepository<PetSurrenderApplication, Integer> {

}
