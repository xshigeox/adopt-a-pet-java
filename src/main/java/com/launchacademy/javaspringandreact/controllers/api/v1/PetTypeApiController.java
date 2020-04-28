package com.launchacademy.javaspringandreact.controllers.api.v1;

import com.launchacademy.javaspringandreact.models.Pet;
import com.launchacademy.javaspringandreact.models.PetType;
import com.launchacademy.javaspringandreact.repositories.PetRepository;
import com.launchacademy.javaspringandreact.repositories.PetTypeRepository;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PetTypeApiController {

  @Autowired
  private PetTypeRepository petTypeRepo;

  @Autowired
  private PetRepository petRepo;

  @NoArgsConstructor
  private class PetTypeNotFoundException extends RuntimeException {

  }

  @ControllerAdvice
  private class PetTypeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PetTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urlNotFoundHandler(PetTypeNotFoundException ex) {
      return ex.getMessage();
    }
  }

  private class InvalidContractorException extends RuntimeException {

  }

  @ControllerAdvice
  private class InvalidContractorAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidContractorException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String invalidContractor(InvalidContractorException ic) {
      return "";
    }
  }

  @GetMapping("/pets")
  public List getList() {
    return petTypeRepo.findAll();
  }

  @GetMapping("/pets/{id}")
  public PetType getOne(@PathVariable Integer id) {
    return petTypeRepo.findById(id).orElseThrow(() -> new PetTypeNotFoundException());
  }

  @GetMapping("/{petType}")
  public List getPetTypeList(@PathVariable String petType) {
    PetType type = petTypeRepo.findByType(petType);
    return petRepo.findAllByPetType(type);
  }

  @GetMapping("/{petType}/{id}")
  public Pet getOnePet(@PathVariable String petType, @PathVariable Integer id) {
    PetType type = petTypeRepo.findByType(StringUtils.capitalize(petType));
    List<Pet> petsList = petRepo.findAllByPetType(type);
    Pet foundPet = new Pet();
    for (Pet pet : petsList) {
      if (pet.getId().equals(id)) {
        foundPet = pet;
      }
    }
    return foundPet;
  }
}
