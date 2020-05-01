package com.launchacademy.javaspringandreact.seeders;

import com.launchacademy.javaspringandreact.models.Admin;
import com.launchacademy.javaspringandreact.repositories.AdminRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder {

  @Autowired
  private AdminRepository adminRepo;

  public void seed() {
    if(adminRepo.count() == 0) {
      List<Admin> admins = new ArrayList<>();
      Admin admin1 = new Admin();
      admin1.setName("Mike");
      admin1.setUserName("admin");
      admin1.setPassword("password");
      admins.add(admin1);

      Admin admin2 = new Admin();
      admin2.setName("Captain Ooo ooo iek the Hairless");
      admin2.setUserName("monkey");
      admin2.setPassword("monkey");
      admins.add(admin2);

      for(Admin admin: admins) {
        adminRepo.save(admin);
      }
    }
  }
}
