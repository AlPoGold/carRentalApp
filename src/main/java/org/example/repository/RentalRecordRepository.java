package org.example.repository;

import org.example.model.RentalRecord;

import java.util.Optional;

public class RentalRecordRepository {
    public void save(RentalRecord rentalRecord) {
    }

    public Optional<RentalRecord> findTopByCarIdOrderByRentalDateDesc(Object returnCarId) {

        RentalRecord rr = new RentalRecord();
        return Optional.of(rr);

    }
}
