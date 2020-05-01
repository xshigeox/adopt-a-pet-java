package com.launchacademy.javaspringandreact.controllers.api.v1;

import com.launchacademy.javaspringandreact.models.AdoptionApplication;
import com.launchacademy.javaspringandreact.repositories.AdoptionApplicationRepository;
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
public class AdoptionApplicationApiController {

  @Autowired
  private AdoptionApplicationRepository adoptionApplicationRepo;

  @NoArgsConstructor
  private class InvalidAdoptionApplicationException extends RuntimeException {

  }

  @ControllerAdvice
  private class InvalidAdoptionApplicationAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidAdoptionApplicationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String invalidAdoptionApplication(InvalidAdoptionApplicationException ex) {
      return "";
    }
  }

  @GetMapping("/api/v1/adoption_applications")
  public List getAdoptionApplications() {
    return adoptionApplicationRepo.findAll();
  }

  @PostMapping("/api/v1/adoption_application")
  public AdoptionApplication create(@RequestBody @Valid AdoptionApplication adoptionApplication,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      throw new InvalidAdoptionApplicationException();
    } else {
      return adoptionApplicationRepo.save(adoptionApplication);
    }
  }
}
