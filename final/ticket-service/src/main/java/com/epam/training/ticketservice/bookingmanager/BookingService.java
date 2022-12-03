package com.epam.training.ticketservice.bookingmanager;

public interface BookingService {

    boolean booking(String movieTitle, String roomName, String date, String seats);

    boolean isTheChairTaken(int[] seats);

    boolean isThereAChair(int[] seats);

}
