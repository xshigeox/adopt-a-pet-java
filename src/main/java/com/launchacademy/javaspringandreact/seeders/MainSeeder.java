package com.launchacademy.javaspringandreact.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {

  @Autowired
  PetSeeder petSeeder;

  @Autowired
  PetTypeSeeder petTypeSeeder;

  @Autowired
  AdminSeeder adminSeeder;

  @Override
  public void run(String... args) throws Exception {
    petTypeSeeder.seed();
    petSeeder.seed();
    adminSeeder.seed();
  }
}
