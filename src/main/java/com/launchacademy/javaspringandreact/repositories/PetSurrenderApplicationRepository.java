package com.launchacademy.javaspringandreact.repositories;

import com.launchacademy.javaspringandreact.models.PetSurrenderApplication;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PetSurrenderApplicationRepository extends
    PagingAndSortingRepository<PetSurrenderApplication, Integer> {

  public List<PetSurrenderApplication> findAll();

}
