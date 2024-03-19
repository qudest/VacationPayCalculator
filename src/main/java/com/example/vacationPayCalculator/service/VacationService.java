package com.example.vacationPayCalculator.service;

import java.time.LocalDate;
import java.util.List;

public interface VacationService {
    List<LocalDate> getHolidays();

    boolean isVacation(LocalDate date);
}
