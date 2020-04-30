package com.launchacademy.javaspringandreact.controllers.api.v1;

import com.launchacademy.javaspringandreact.models.AdoptionApplication;
import com.launchacademy.javaspringandreact.models.Pet;
import com.launchacademy.javaspringandreact.models.PetSurrenderApplication;
import com.launchacademy.javaspringandreact.models.PetType;
import com.launchacademy.javaspringandreact.repositories.AdoptionApplicationRepository;
import com.launchacademy.javaspringandreact.repositories.PetRepository;
import com.launchacademy.javaspringandreact.repositories.PetSurrenderApplicationRepository;
import com.launchacademy.javaspringandreact.repositories.PetTypeRepository;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PetApiController {

  @Autowired
  private PetTypeRepository petTypeRepo;

  @Autowired
  private PetRepository petRepo;

  @Autowired
  private PetSurrenderApplicationRepository petSurrenderRepo;

  @Autowired
  private AdoptionApplicationRepository adoptionApplicationRepo;

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

  @NoArgsConstructor
  private class PetNotFoundException extends  RuntimeException {

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

  private class InvalidPetSurrenderApplicationException extends RuntimeException {

  }

  @ControllerAdvice
  private class InvalidPetSurrenderApplicationAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidPetSurrenderApplicationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String invalidPetSurrenderApplication(InvalidPetSurrenderApplicationException ic) {
      return "";
    }
  }

  private class InvalidAdoptionApplicationException extends RuntimeException {

  }

  @ControllerAdvice
  private class InvalidAdoptionApplicationAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidAdoptionApplicationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String invalidAdoptionApplication(InvalidAdoptionApplicationException ic) {
      return "";
    }
  }

  @GetMapping("/pets")
  public List getList() {
    return petTypeRepo.findAll();
  }

  @GetMapping("/pets/{id}")
  public PetType getOne(@PathVariable Integer id) {
    return petTypeRepo.findById(id).orElseThrow(PetTypeNotFoundException::new);
  }

  @GetMapping("/{petType}")
  public List getPetTypeList(@PathVariable String petType) {
    PetType type = petTypeRepo.findByType(petType);

    return petRepo.findAllByPetType(type);
  }

  @GetMapping("/{petType}/{id}")
  public Pet getOnePet(@PathVariable String petType, @PathVariable Integer id) {
    PetType type = petTypeRepo.findByType(petType);
    return petRepo.findByIdAndPetType(id, type).orElseThrow(PetNotFoundException::new);
  }

  @PostMapping("/newPet")
  public PetSurrenderApplication create(
      @RequestBody @Valid PetSurrenderApplication petSurrenderApplication,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      throw new InvalidPetSurrenderApplicationException();
    } else {
      return petSurrenderRepo.save(petSurrenderApplication);
    }
  }

  @PostMapping("/adoptionApplication")
  public AdoptionApplication create(@RequestBody @Valid AdoptionApplication adoptionApplication,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      throw new InvalidAdoptionApplicationException();
    } else {
      return adoptionApplicationRepo.save(adoptionApplication);
    }
  }
}
