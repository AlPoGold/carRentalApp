package org.example.service;

import org.example.model.Car;
import org.example.model.RentalRecord;
import org.example.model.ReturnRequest;
import org.example.model.RentalRequest;
import org.example.repository.CarRepository;
import org.example.repository.RentalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarRentalService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentalRecordRepository rentalRecordRepository;

    public void bookCar(RentalRequest rentalRequest) {
        // Logic to book a car
        Optional<Car> optionalCar = carRepository.findById(rentalRequest.getCarId());
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            if (car.isAvailable()) {
                // Update car availability status
                car.setAvailable(false);
                carRepository.save(car);

                // Create and save rental record
                // Note: You may want to implement a RentalRecordService for more complex logic
                rentalRecordRepository.save(rentalRequest.toRentalRecord(car));
            } else {
                throw new RuntimeException("Car is not available for booking");
            }
        } else {
            throw new RuntimeException("Car not found");
        }
    }

    public void returnCar(ReturnRequest returnRequest) {
        // Logic to return a car
        Optional<RentalRecord> optionalRentalRecord = rentalRecordRepository.findTopByCarIdOrderByRentalDateDesc(returnRequest.getReturnCarId());
        if (optionalRentalRecord.isPresent()) {
            RentalRecord rentalRecord = optionalRentalRecord.get();
            rentalRecord.setReturnDate(LocalDateTime.now());
            rentalRecordRepository.save(rentalRecord);

            // Update car availability status
            Car car = rentalRecord.getCar();
            car.setAvailable(true);
            carRepository.save(car);
        } else {
            throw new RuntimeException("Rental record not found");
        }
    }

    public List<Car> getAvailableCars() {
        // Retrieve a list of available cars
        return carRepository.findByAvailableTrue();
    }
}