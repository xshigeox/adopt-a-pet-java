package com.launchacademy.javaspringandreact.controllers.api.v1;

import com.launchacademy.javaspringandreact.models.AdoptionApplication;
import com.launchacademy.javaspringandreact.models.Pet;
import com.launchacademy.javaspringandreact.repositories.AdoptionApplicationRepository;
import com.launchacademy.javaspringandreact.repositories.PetRepository;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdoptionApplicationApiController {

  @Autowired
  private AdoptionApplicationRepository adoptionApplicationRepo;

  @Autowired
  private PetRepository petRepo;

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

  private class AdoptionApplicationNotFound extends RuntimeException {

  }

  @ControllerAdvice
  private class AdoptionApplicationNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AdoptionApplicationNotFound.class)
    String adoptionApplicationNotFound(AdoptionApplicationNotFound ex) {
      return ex.getMessage();
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

  @PutMapping("/api/v1/approval_status/{id}")
  public AdoptionApplication update(@RequestBody AdoptionApplication newAdoptionApplication,
      @PathVariable Integer id) {
    return adoptionApplicationRepo.findById(id).map(adoptionApplication -> {
      Pet pet = newAdoptionApplication.getPet();
      adoptionApplication.setId(id);
      adoptionApplication.setName(newAdoptionApplication.getName());
      adoptionApplication.setPhoneNumber(newAdoptionApplication.getPhoneNumber());
      adoptionApplication.setEmail(newAdoptionApplication.getEmail());
      adoptionApplication.setHomeStatus(newAdoptionApplication.getHomeStatus());
      adoptionApplication.setApplicationStatus(newAdoptionApplication.getApplicationStatus());
      adoptionApplication.setPet(newAdoptionApplication.getPet());
      if (adoptionApplication.getApplicationStatus().equals("Approved")) {
        pet.setAdoptionStatus("Approved");
        petRepo.save(pet);
      }
      return adoptionApplicationRepo.save(adoptionApplication);
    }).orElseThrow(AdoptionApplicationNotFound::new);
  }
}
