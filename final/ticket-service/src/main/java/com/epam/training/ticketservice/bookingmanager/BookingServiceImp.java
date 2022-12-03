package com.epam.training.ticketservice.bookingmanager;

import com.epam.training.ticketservice.accountmanager.AccountManager;
import com.epam.training.ticketservice.bookingmanager.model.BookDto;
import com.epam.training.ticketservice.screeningmanager.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;


public class BookingServiceImp implements BookingService {

    List<BookDto> booksList;

    @Autowired
    ScreeningService screeningService;

    @Autowired
    AccountManager accountManager;


    public void initUser() {
        booksList = new LinkedList<>();
    }

    @Override
    public boolean booking(String movieTitle, String roomName, String date, String seats) {

        List<String> splitStringList = Arrays.stream(seats.split(" ")).collect(Collectors.toList());
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
