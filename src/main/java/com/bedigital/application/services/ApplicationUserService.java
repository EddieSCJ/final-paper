package com.bedigital.application.services;

import com.bedigital.application.domain.ApplicationUser;
import com.bedigital.application.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public ApplicationUser save(ApplicationUser applicationUser) {
        String password = UtilsService.hashPassword(applicationUser.getPassword());
        applicationUser.setPassword(password);
        ApplicationUser applicationUserReturned = applicationUserRepository.save(applicationUser);
        return applicationUserReturned;
    }

    public void delete(ApplicationUser applicationUser) {
        applicationUserRepository.deleteById(applicationUser.getId());
    }

}
