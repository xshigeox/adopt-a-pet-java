package com.launchacademy.javaspringandreact.repositories;

import com.launchacademy.javaspringandreact.models.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminRepository extends PagingAndSortingRepository<Admin, Integer> {

}
