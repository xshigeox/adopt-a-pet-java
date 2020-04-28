package com.launchacademy.javaspringandreact.seeders;

import com.launchacademy.javaspringandreact.models.PetType;
import com.launchacademy.javaspringandreact.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PetTypeSeeder implements CommandLineRunner {

  @Autowired
  private PetTypeRepository petTypeRepo;

  @Override
  public void run(String... args) throws Exception {
    if (petTypeRepo.count() == 0) {
      PetType petType = new PetType();
      petType.setType("Reptile");
      petType.setDescription("All things reptilian");
      petTypeRepo.save(petType);

      PetType petType1 = new PetType();
      petType1.setType("Guinea Pig");
      petType1.setDescription("Not quite pigs");
      petTypeRepo.save(petType1);
    }
  }
}
