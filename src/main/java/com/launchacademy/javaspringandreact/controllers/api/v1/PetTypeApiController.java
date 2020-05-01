package com.launchacademy.javaspringandreact.controllers.api.v1;

import com.launchacademy.javaspringandreact.models.PetType;
import com.launchacademy.javaspringandreact.repositories.PetTypeRepository;
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
public class PetTypeApiController {

  @Autowired
  private PetTypeRepository petTypeRepo;

  @NoArgsConstructor
  private class PetTypeNotFoundException extends RuntimeException {

  }

  @ControllerAdvice
  private class PetTypeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PetTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String petTypeNotFoundHandler(PetTypeNotFoundException ex) {
      return ex.getMessage();
    }
  }

  @GetMapping("/api/v1/pets")
  public List getPetTypes() {
    return petTypeRepo.findAll();
  }

  @GetMapping("/api/v1/pets/{id}")
  public PetType getOneType(@PathVariable Integer id) {
    return petTypeRepo.findById(id).orElseThrow(PetTypeNotFoundException::new);
  }
}
