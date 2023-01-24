package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    // changed private to public because we are this method in another class
    public double calculateTicketPrice(Showing showing) {
    	
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay(),showing.getStartTime(),showing);
    }

    private double getDiscount(int showSequence,LocalDateTime showStartTime, Showing showing) {
    	LocalDate currentDate = showStartTime.toLocalDate();
        String ar[]= currentDate.toString().split("-");
        
        String ar1[]= showStartTime.toString().split("T");
        String ar2[]=ar1[1].split(":");
        
        double specialDiscount = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }
        double sequenceDiscount1 = 0;
        double sequenceDiscount = 0;
        if(ar[2]== "07"  || showing.getDay() == 7 || showSequence == 7) {
        	sequenceDiscount= 1; // $1 discount for all shows only on 7th day or $1 discount for 7th show
        }
        
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } 
        if(showSequence == 2) {
        	sequenceDiscount1 = 1; // $1 discount for 2nd show
        }
        else if ((Integer.parseInt(ar2[0])>= 11 && Integer.parseInt(ar2[1])>=0  && Integer.parseInt(ar2[0])< 16)|| (Integer.parseInt(ar2[0])== 16 && Integer.parseInt(ar2[1])==0 ) || (Integer.parseInt(ar2[0])<16 && Integer.parseInt(ar2[1])>=0 && Integer.parseInt(ar2[0])>= 11 )) {

            sequenceDiscount = ticketPrice * 0.25; // $25% discount for 2nd show,3rd show,4th show
        }
        sequenceDiscount=sequenceDiscount1>sequenceDiscount?sequenceDiscount1:sequenceDiscount;
        
        
//        else {
//            throw new IllegalArgumentException("failed exception");
//        }

        // biggest discount wins
        return specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}