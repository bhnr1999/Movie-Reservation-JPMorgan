package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test // Special Code Discount
    void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));

        System.out.println(Duration.ofMinutes(90));
    }

    @Test // Discount for shows between 11am - 4pm
        void timeOfTheDayDiscount() {
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Showing showing = new Showing(turningRed, 4, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30)));
        assertEquals(8.25, turningRed.calculateTicketPrice(showing));

        System.out.println(Duration.ofMinutes(85));
    }

    @Test // 7th day of month discount
        void seventhDayOfMonthDiscount() {
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        Showing showing = new Showing(theBatMan, 9, LocalDateTime.of(2023, 01, 07, 23, 00));
        assertEquals(8, theBatMan.calculateTicketPrice(showing));

        System.out.println(Duration.ofMinutes(95));
    }

    @Test // First sequence of day discount
    void firstSequenceOfTheDayDiscount() {
    Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
    Showing showing = new Showing(turningRed, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
    assertEquals(8, turningRed.calculateTicketPrice(showing));

    System.out.println(Duration.ofMinutes(95));
}
}
