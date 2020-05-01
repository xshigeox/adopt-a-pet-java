package com.launchacademy.javaspringandreact.controllers.api.v1;

import com.launchacademy.javaspringandreact.models.PetSurrenderApplication;
import com.launchacademy.javaspringandreact.repositories.PetSurrenderApplicationRepository;
import java.util.List;
import javax.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetSurrenderApiController {

  @Autowired
  private PetSurrenderApplicationRepository petSurrenderRepo;

  @NoArgsConstructor
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

  @GetMapping("/api/v1/pet_surrender_applications")
  public List getPetSurrenderApplications() {
    return petSurrenderRepo.findAll();
  }

  @PostMapping("/api/v1/new_pet")
  public PetSurrenderApplication create(
      @RequestBody @Valid PetSurrenderApplication petSurrenderApplication,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      throw new InvalidPetSurrenderApplicationException();
    } else {
      return petSurrenderRepo.save(petSurrenderApplication);
    }
  }
}
