package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Theater {

    LocalDateProvider provider;
    private List<Showing> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        schedule = List.of(
            new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
            new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
            new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
            new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
            new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
            new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
            new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
            new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
            new Showing(theBatMan, 9, LocalDateTime.of(2023, 01, 07, 23, 00))
        );
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing = null;
        try {
            showing = schedule.get(sequence - 1);
            System.out.println();
            System.out.println("{ Show Number :"+showing.getSequenceOfTheDay() + ", Show Time:" + showing.getStartTime() + ", Movie Name:" + showing.getMovie().getTitle() + ", Movie Length:" + humanReadableFormat(showing.getMovie().getRunningTime())+"}");
            
            System.out.println("--------------------------------");
            System.out.println("Total Price       :"+ " $" +howManyTickets*showing.getMovieFee());
            System.out.println("--------------------------------");
            System.out.println("Discount Price    :"+ " $" +(howManyTickets*showing.getMovieFee()-showing.calculateFee(howManyTickets)));
            System.out.println("---------------------------------");
            System.out.println("Total Amount Paid :"+ " $" +showing.calculateFee(howManyTickets));
            System.out.println("---------------------------------");
            if(howManyTickets==1)
            	System.out.println(howManyTickets+" Ticket is Booked");
            else
            	System.out.println(howManyTickets+" Tickets are Booked.");
        } catch (RuntimeException ex) {
            
            System.out.println("Please Select Valid Show");
          //ex.printStackTrace();
//            Uncomment the below lines if you want see the error message
            //throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public void printSchedule() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter Your Name :");
        String name = sc.next();
        System.out.println("Please Enter Your Id :");
        String id = sc.next();
        Customer customer = new Customer(name,id);
        System.out.println("Please select the show from above list:");
        int seqNo = sc.nextInt();
        System.out.println("How Many Tickets Do You Want?");
        int count = sc.nextInt();
        theater.reserve(customer, seqNo, count);
        System.out.println("------------------------------------");
        
        sc.close();
        
    }
}
