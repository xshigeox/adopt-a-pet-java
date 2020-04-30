package com.launchacademy.javaspringandreact.seeders;

import com.launchacademy.javaspringandreact.models.Pet;
import com.launchacademy.javaspringandreact.models.PetType;
import com.launchacademy.javaspringandreact.repositories.PetRepository;
import com.launchacademy.javaspringandreact.repositories.PetTypeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetSeeder {

  @Autowired
  private PetRepository petRepo;

  @Autowired
  private PetTypeRepository petTypeRepo;

  public void seed() {
    List<Pet> petList = new ArrayList<>();
    PetType typeReptile = petTypeRepo.findByType("reptile");
    PetType typeGuineaPig = petTypeRepo.findByType("guineapig");

    if (petRepo.count() == 0) {

      Pet reptile = new Pet();
      reptile.setName("Artemis");
      reptile.setImgUrl(
          "https://lafeber.com/vet/wp-content/uploads/Veiled-chameleon-by-Mrs-Logic-cropped-square.jpg");
      reptile.setAge(5);
      reptile.setVaccinationStatus(false);
      reptile.setAdoptionStory("Is a chameleon that gets excited at the sight of lettuce.");
      reptile.setAdoptionStatus("Pending");
      reptile.setPetType(typeReptile);
      petList.add(reptile);

      Pet reptile1 = new Pet();
      reptile1.setName("Sticky");
      reptile1.setImgUrl("https://live.staticflickr.com/8576/15166690374_6dcf167afa_b.jpg");
      reptile1.setAge(1);
      reptile1.setVaccinationStatus(true);
      reptile1.setAdoptionStory("This guy loves crickets");
      reptile1.setAdoptionStatus("Pending");
      reptile1.setPetType(typeReptile);
      petList.add(reptile1);

      Pet reptile2 = new Pet();
      reptile2.setName("Dino");
      reptile2.setImgUrl(
          "https://www.zillarules.com/-/media/images/zilla-na/us/blog-articles/myths-facts-about-bearded-dragon-care/bearded-dragon.jpg?h=350&w=350&la=en&hash=BB45C333590D6C0AF6E426FC419D6A3B224212BE");
      reptile2.setAge(3);
      reptile2.setVaccinationStatus(true);
      reptile2.setAdoptionStory("Great lizard just looking to play.");
      reptile2.setAdoptionStatus("Pending");
      reptile2.setPetType(typeReptile);
      petList.add(reptile2);

      Pet guineaPig = new Pet();
      guineaPig.setName("Helena");
      guineaPig.setImgUrl(
          "https://www.reptilecentre.com/images/wmfixed/Reptile/frozen-guinea-pig-v1-495-495.jpg?v=2");
      guineaPig.setAge(3);
      guineaPig.setVaccinationStatus(true);
      guineaPig.setAdoptionStory("A sweet guinea pig that loves belly rubs.");
      guineaPig.setAdoptionStatus("Pending");
      guineaPig.setPetType(typeGuineaPig);
      petList.add(guineaPig);

      Pet guineaPig1 = new Pet();
      guineaPig1.setName("Pancake");
      guineaPig1.setImgUrl(
          "https://i.pinimg.com/originals/f8/bc/80/f8bc8082646c8bc7e7a87b9d6b02543f.jpg");
      guineaPig1.setAge(4);
      guineaPig1.setVaccinationStatus(true);
      guineaPig1.setAdoptionStory("This guy loves to lounge around");
      guineaPig1.setAdoptionStatus("Pending");
      guineaPig1.setPetType(typeGuineaPig);
      petList.add(guineaPig1);

      Pet guineaPig2 = new Pet();
      guineaPig2.setName("Rocky");
      guineaPig2.setImgUrl("https://cdn.mos.cms.futurecdn.net/gJJFamQca86CibEeDmegk-1024-80.jpg");
      guineaPig2.setAge(7);
      guineaPig2.setVaccinationStatus(false);
      guineaPig2.setAdoptionStory("An ornery guinea pig that hates belly rubs.");
      guineaPig2.setAdoptionStatus("Pending");
      guineaPig2.setPetType(typeGuineaPig);
      petList.add(guineaPig2);

      for (Pet pet : petList) {
        petRepo.save(pet);
      }
    }
  }
}
