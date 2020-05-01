package com.launchacademy.javaspringandreact.controllers.api.v1;

import com.launchacademy.javaspringandreact.models.Admin;
import com.launchacademy.javaspringandreact.models.Pet;
import com.launchacademy.javaspringandreact.models.PetSurrenderApplication;
import com.launchacademy.javaspringandreact.models.PetType;
import com.launchacademy.javaspringandreact.repositories.AdminRepository;
import com.launchacademy.javaspringandreact.repositories.PetRepository;
import com.launchacademy.javaspringandreact.repositories.PetSurrenderApplicationRepository;
import com.launchacademy.javaspringandreact.repositories.PetTypeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @Autowired
  private PetSurrenderApplicationRepository petSurrenderApplicationRepo;

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

  @NoArgsConstructor
  private class InvalidPetException extends RuntimeException {

  }

  @ControllerAdvice
  private class InvalidPetAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidPetException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String invalidPet(InvalidPetException ex) {
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

  @GetMapping("/api/v1/adopted")
  public List getAdoptedPets() {
    Iterable<Pet> pets = petRepo.findAll();
    List<Pet> adoptedPets = new ArrayList<>();
    for (Pet pet : pets) {
      if (pet.getAdoptionStatus().equals("Approved")) {
        adoptedPets.add(pet);
      }
    }
    return adoptedPets;
  }

  @GetMapping("/api/v1/{petType}/{id}")
  public Pet getOnePet(@PathVariable String petType, @PathVariable Integer id) {
    PetType type = petTypeRepo.findByType(petType);
    return petRepo.findByIdAndPetType(id, type).orElseThrow(PetNotFoundException::new);
  }

  @PostMapping("/api/v1/approve_pet/{id}")
  public Pet create(@RequestBody @Valid Pet pet, @PathVariable Integer id,
      BindingResult bindingResult) {
    Optional<PetSurrenderApplication> petSurrenderApplication = petSurrenderApplicationRepo
        .findById(id);
    if (bindingResult.hasErrors()) {
      throw new InvalidPetException();
    } else {
      if (petSurrenderApplication.isPresent()) {
        PetSurrenderApplication application = petSurrenderApplication.get();
        application.setApplicationStatus("Approved");
        petSurrenderApplicationRepo.save(application);
      }
      return petRepo.save(pet);
    }
  }

  @GetMapping("/api/v1/login")
  public Iterable<Admin> login() {
    return adminRepo.findAll();
  }
}
