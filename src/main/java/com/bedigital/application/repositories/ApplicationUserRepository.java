package com.bedigital.application.repositories;


import com.bedigital.application.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findByUsernameIgnoreCase(String username);

    void deleteByUsernameIgnoreCase(String username);

}
