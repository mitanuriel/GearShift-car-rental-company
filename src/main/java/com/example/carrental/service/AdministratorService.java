package com.example.carrental.service;

import com.example.carrental.model.Administrator;
import com.example.carrental.repository.AdministratorRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {
    private AdministratorRepository administratorRepository;
    //lavet af Oliver
    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }
    //lavet af Oliver
    public Administrator getAdministrator() {
        return administratorRepository.getAdministrator();
    }
    //lavet af Oliver
    public boolean checkCookie(String passwd) {
        return administratorRepository.getAdministrator().getPassword().equals(passwd);
    }
}
