package com.launchacademy.javaspringandreact.seeders;

import com.launchacademy.javaspringandreact.models.PetType;
import com.launchacademy.javaspringandreact.repositories.PetTypeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetTypeSeeder {

  @Autowired
  private PetTypeRepository petTypeRepo;

  public void seed() {
    List<PetType> petTypeList = new ArrayList<>();

    if (petTypeRepo.count() == 0) {
      PetType petType = new PetType();
      petType.setType("Reptile");
      petType.setDescription("All things reptilian");
      petTypeList.add(petType);

      PetType petType1 = new PetType();
      petType1.setType("Guinea Pig");
      petType1.setDescription("Not quite pigs");
      petTypeList.add(petType1);

      for (PetType type : petTypeList) {
        petTypeRepo.save(type);
      }
    }
  }
}
