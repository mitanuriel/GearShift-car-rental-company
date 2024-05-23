package com.example.carrental.service;

import com.example.carrental.model.Administrator;
import com.example.carrental.repository.AdministratorRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {
    private AdministratorRepository administratorRepository;
    
    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public Administrator getAdministrator() {
        return administratorRepository.getAdministrator();
    }

    public boolean checkCookie(String passwd) {
        return administratorRepository.getAdministrator().getPassword().equals(passwd);
    }
}
