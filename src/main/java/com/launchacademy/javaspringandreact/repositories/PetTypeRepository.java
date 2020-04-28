package com.launchacademy.javaspringandreact.repositories;

import com.launchacademy.javaspringandreact.models.PetType;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PetTypeRepository extends PagingAndSortingRepository<PetType, Integer> {

  public List<PetType> findAll();

  public PetType findByType(String type);
}

