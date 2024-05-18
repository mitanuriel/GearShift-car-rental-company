package com.example.carrental.service;

import com.example.carrental.model.Equipment;
import com.example.carrental.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {
    private EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getAllEquipment(int car_id) {
        return equipmentRepository.getAllEquipment(car_id);
    }

    public void insert(Equipment equipment) {
        equipmentRepository.insert(equipment);
    }

    public void delete(int equipment_id) {
        equipmentRepository.delete(equipment_id);
    }
}