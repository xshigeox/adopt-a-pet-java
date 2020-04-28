package com.launchacademy.javaspringandreact.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "adoptable_pets")
@Getter
@Setter
@NoArgsConstructor
public class Pet {

  @Id
  @SequenceGenerator(name = "adoptable_pet_generator", sequenceName = "adoptable_pets_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adoptable_pet_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @NotBlank(message = "Must include a name")
  @Column(name = "name")
  private String name;

  @NotBlank(message = "Must include an image url")
  @URL
  @Column(name = "img_url")
  private String imgUrl;

  @Column(name = "age")
  private Integer age;

  @Column(name = "vaccination_status")
  private Boolean vaccinationStatus;

  @NotBlank(message = "Must include an adoption story")
  @Column(name = "adoption_story")
  private String adoptionStory;

  @NotBlank
  @Column(name = "adoption_status")
  private String adoptionStatus;

  @ManyToOne
  @JoinColumn(name = "pet_type_id")
  private PetType petType;
}
