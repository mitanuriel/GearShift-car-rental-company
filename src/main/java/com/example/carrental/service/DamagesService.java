package com.example.carrental.service;

import com.example.carrental.model.Damages;
import com.example.carrental.repository.DamagesRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DamagesService {
    private DamagesRepository damagesRepository;
    //lavet af Oliver
    public DamagesService(DamagesRepository damagesRepository) {
        this.damagesRepository = damagesRepository;
    }
    //lavet af Oliver
    public List<Damages> getDamages(int contract_id) {
        return damagesRepository.getDamages(contract_id);
    }
    //lavet af Oliver
    public void insert(Damages damages) {
        damagesRepository.insert(damages);
    }
    //lavet af Oliver
    public void delete(int damages_id) {
        damagesRepository.delete(damages_id);
    }
    //lavet af Oliver
    public void update(Damages damages) {
        damagesRepository.update(damages);
    }
}