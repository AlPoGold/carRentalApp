package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Car {

    private boolean isAvailable;


    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
