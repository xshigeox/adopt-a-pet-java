package com.launchacademy.javaspringandreact.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="admin_table")
@Getter
@Setter
@NoArgsConstructor
public class Admin {

  @Id
  @SequenceGenerator(name = "admin_generator", sequenceName = "admin_table_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @NotBlank
  @Column(name="name")
  private String name;

  @NotBlank
  @Column(name="username")
  private String userName;


  @NotBlank
  @Column(name="password")
  private String password;

}
