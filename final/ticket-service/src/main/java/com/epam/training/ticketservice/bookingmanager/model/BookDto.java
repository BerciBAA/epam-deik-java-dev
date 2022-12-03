package com.epam.training.ticketservice.bookingmanager.model;

import com.epam.training.ticketservice.screeningmanager.model.ScreeningDto;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BookDto {
    ScreeningDto screening;
    int[] seat;
    int price;

    public static BookDto createBook(ScreeningDto screening, int[] seat, int price) {
        return BookDto.builder()
                .screening(screening)
                .seat(seat)
                .price(price)
                .build();
    }
}
