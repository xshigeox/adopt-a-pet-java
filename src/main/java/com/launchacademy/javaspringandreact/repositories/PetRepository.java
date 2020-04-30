package com.launchacademy.javaspringandreact.repositories;

import com.launchacademy.javaspringandreact.models.Pet;
import com.launchacademy.javaspringandreact.models.PetType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PetRepository extends PagingAndSortingRepository<Pet, Integer> {

  public List<Pet> findAllByPetType(PetType petType);

  public Optional<Pet> findByIdAndPetType(Integer id, PetType petType);

}
