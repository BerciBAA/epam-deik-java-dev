package com.epam.training.ticketservice.bookingmanager;

import com.epam.training.ticketservice.screeningmanager.model.ScreeningDto;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Book {
    ScreeningDto screening;
    int[] seat;
    int price;

    public static Book createBook(ScreeningDto screening, int[] seat, int price) {
        return Book.builder()
                .screening(screening)
                .seat(seat)
                .price(price)
                .build();
    }
}
