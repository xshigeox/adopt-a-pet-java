package com.launchacademy.javaspringandreact.repositories;

import com.launchacademy.javaspringandreact.models.AdoptionApplication;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdoptionApplicationRepository extends
    PagingAndSortingRepository<AdoptionApplication, Integer> {

}
