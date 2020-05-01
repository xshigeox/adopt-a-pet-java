package com.launchacademy.javaspringandreact.controllers.api.v1;

import com.launchacademy.javaspringandreact.models.Admin;
import com.launchacademy.javaspringandreact.models.Pet;
import com.launchacademy.javaspringandreact.models.PetType;
import com.launchacademy.javaspringandreact.repositories.AdminRepository;
import com.launchacademy.javaspringandreact.repositories.PetRepository;
import com.launchacademy.javaspringandreact.repositories.PetTypeRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetApiController {

  @Autowired
  private PetTypeRepository petTypeRepo;

  @Autowired
  private PetRepository petRepo;

  @Autowired
  private AdminRepository adminRepo;

  @NoArgsConstructor
  private class PetNotFoundException extends RuntimeException {

  }

  @ControllerAdvice
  private class PetNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String petNotFoundHandler(PetNotFoundException ex) {
      return ex.getMessage();
    }
  }

  @GetMapping("/api/v1/{petType}")
  public List getPetByTypeList(@PathVariable String petType) {
    PetType type = petTypeRepo.findByType(petType);

    return petRepo.findAllByPetType(type);
  }

  @GetMapping("/api/v1/not_approved/{petType}")
  public List getNotApprovedPetsByType(@PathVariable String petType) {
    PetType type = petTypeRepo.findByType(petType);
    List<Pet> pets = petRepo.findAllByPetType(type);
    List<Pet> notApproved = new ArrayList<>();

    for (Pet pet : pets) {
      if (!pet.getAdoptionStatus().equals("Approved")) {
        notApproved.add(pet);
      }
    }
    return notApproved;

  }

  @GetMapping("/api/v1/{petType}/{id}")
  public Pet getOnePet(@PathVariable String petType, @PathVariable Integer id) {
    PetType type = petTypeRepo.findByType(petType);
    return petRepo.findByIdAndPetType(id, type).orElseThrow(PetNotFoundException::new);
  }

  @GetMapping("/api/v1/login")
  public Iterable<Admin> login() {
    return adminRepo.findAll();
  }
}
