package com.epam.training.ticketservice.bookingmanager;

import com.epam.training.ticketservice.accountmanager.AccountManager;
import com.epam.training.ticketservice.accountmanager.model.UserDto;
import com.epam.training.ticketservice.screeningmanager.model.ScreeningDto;
import com.epam.training.ticketservice.screeningmanager.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.LinkedList;
import java.util.Optional;


public class BookingServiceImp implements BookingService{

    List<Book> booksList;

    @Autowired
    ScreeningService screeningService;

    @Autowired
    AccountManager accountManager;


    public void initUser() {
        booksList = new LinkedList<>();
    }

    @Override
    public boolean Booking(String movieTitle, String roomName, String Date, String seats) {

        Optional<UserDto> user = accountManager.getLoggedInUser();
        //return Pattern.matches("(\d+),(\d+)", seats);
        return false;
    }

    @Override
    public boolean isTheChairTaken(int[] seats) {
        return false;
    }

    @Override
    public boolean isThereAChair(int[] seats) {
        return false;
    }
}
