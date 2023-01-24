package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFeeSpiderMan() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                2,
                LocalDateTime.now()
        );
        
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 37.5);
    }

    @Test
    void totalFeeTurningRed() {
        var customer = new Customer("Nirush Reddy", "11");
        var showing = new Showing(
                new Movie("Turning Red", Duration.ofMinutes(85), 11, 0),
                1,
                LocalDateTime.now()
        );
        
        assertTrue(new Reservation(customer, showing, 5).totalFee() == 55);
    }

    @Test
    void totalFeeBatMan() {
        var customer = new Customer("Morgan", "100");
        var showing = new Showing(
            new Movie("The Batman", Duration.ofMinutes(95), 9, 0),
                3,
                LocalDateTime.now()
        );
        
        assertTrue(new Reservation(customer, showing, 2).totalFee() == 18);
    }
}
