package com.epam.training.ticketservice.bookingmanager;

public interface BookingService {

    boolean Booking(String movieTitle, String roomName, String Date, String seats);

    boolean isTheChairTaken(int[] seats);

    boolean isThereAChair(int[] seats);

}
