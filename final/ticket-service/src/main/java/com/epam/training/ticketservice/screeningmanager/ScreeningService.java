package com.epam.training.ticketservice.screeningmanager;


import com.epam.training.ticketservice.screeningmanager.model.ScreeningDto;
import com.epam.training.ticketservice.screeningmanager.persistence.Screening;

import java.util.List;
import java.util.Optional;

public interface ScreeningService {

    Optional<ScreeningDto> createScreening(String movieTitle, String roomName, String date);

    void deleteScreening(String movieTitle, String roomName, String date);

    List<ScreeningDto> listScreenings();

    boolean isOverlapping(ScreeningDto screening, int breakTime);

    Optional<Screening> makeScreening(String movieTitle, String roomName, String strDate);

    String screeningsListToString();


}
