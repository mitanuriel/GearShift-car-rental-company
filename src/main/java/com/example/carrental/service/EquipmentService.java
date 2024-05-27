package com.example.carrental.service;

import com.example.carrental.model.Equipment;
import com.example.carrental.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {
    private EquipmentRepository equipmentRepository;
    //lavet af Oliver
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }
    //lavet af Oliver
    public List<Equipment> getAllEquipment(int car_id) {
        return equipmentRepository.getAllEquipment(car_id);
    }
    //lavet af Oliver
    public void insert(Equipment equipment) {
        equipmentRepository.insert(equipment);
    }
    //lavet af Oliver
    public void delete(int equipment_id) {
        equipmentRepository.delete(equipment_id);
    }
}