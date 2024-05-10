package com.example.carrental.service;

import com.example.carrental.model.Damages;
import com.example.carrental.repository.DamagesRepository;
import org.springframework.stereotype.Service;

@Service
public class DamagesService {
    private DamagesRepository damagesRepository;

    public DamagesService(DamagesRepository damagesRepository) {
        this.damagesRepository = damagesRepository;
    }

    public Damages getDamages(int damages_id) {
        return damagesRepository.getDamages(damages_id);
    }

    public void insert(Damages damages) {
        damagesRepository.insert(damages);
    }

    public void delete(int damages_id) {
        damagesRepository.delete(damages_id);
    }

    public void update(Damages damages) {
        damagesRepository.update(damages);
    }
}