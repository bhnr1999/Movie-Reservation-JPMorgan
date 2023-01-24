package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
       
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(50, reservation.totalFee());
        
//      Second Test Case-Checking sequence 1
        Customer nirush = new Customer("Nirush Reddy", "id-54321");
        Reservation reservation1 = theater.reserve(nirush, 1, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(44, reservation1.totalFee());
        
//      Thrid Test Case - Checking if the Customer select 7th Sequence number
        Customer nirush1 = new Customer("Nirush Reddy", "id-21324");
        Reservation reservation2 = theater.reserve(nirush1, 7, 3);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(33,reservation2.totalFee());
        
        
//      Test Case - Checking if the Customer select 4th Sequence number
        Customer nirush4 = new Customer("Nirush Reddy", "id-21324");
        Reservation reservation3 = theater.reserve(nirush4, 4, 3);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(33,reservation3.totalFee());
        
        

//      Second Test Case-Checking sequence 6    
        Customer nirush5 = new Customer("John Doe", "id-67865");
        Reservation reservation4 = theater.reserve(nirush5, 6, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        assertEquals(36,reservation4.totalFee());
        
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }
}
