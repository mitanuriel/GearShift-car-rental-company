package com.example.carrental.service;

import java.util.Base64;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.carrental.model.CarImage;
import com.example.carrental.repository.CarImageRepository;

@Service
public class CarImageService {
    private CarImageRepository carImageRepository;

    public CarImageService(CarImageRepository carImageRepository) {
        this.carImageRepository = carImageRepository;
    }

    public List<CarImage> getAllImages(int car_id) {
        return carImageRepository.getAllImages(car_id);
    }

    public String getFirstImageAsString(int car_id) {
        List<CarImage> allImages = getAllImages(car_id);

        String imageString = null;
        if (allImages != null) {
            imageString = Base64.getEncoder().encodeToString(allImages.get(0).getImage());
        }

        return imageString;
    }

    public void insert(CarImage carImage) {
        carImageRepository.insert(carImage);
    }

    public void delete(int car_image_id) {
        carImageRepository.delete(car_image_id);
    }
}
