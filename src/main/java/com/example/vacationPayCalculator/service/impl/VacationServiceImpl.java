package com.example.vacationPayCalculator.service.impl;

import com.example.vacationPayCalculator.model.HolidayDto;
import com.example.vacationPayCalculator.service.VacationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacationServiceImpl implements VacationService {

    private final RestTemplate restTemplate;
    private final List<LocalDate> holidays;

    public VacationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.holidays = getHolidays();
    }

    @Override
    public List<LocalDate> getHolidays() {
        LocalDate localDate = LocalDate.now();
        ResponseEntity<HolidayDto[]> holidaysFromApi = restTemplate.getForEntity("https://date.nager.at/api/v3/publicholidays/" + localDate.getYear() + "/RU", HolidayDto[].class);
        List<LocalDate> holidays = new ArrayList<>();
        if (holidaysFromApi.getBody() == null) {
            return holidays;
        }
        for (HolidayDto holiday : holidaysFromApi.getBody()) {
            holidays.add(holiday.getDate());
        }
        return holidays;
    }

    @Override
    public boolean isVacation(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return true;
        }
        for (LocalDate holiday: holidays) {
            if (holiday.getDayOfMonth() == date.getDayOfMonth() && holiday.getMonth() == date.getMonth()) {
                return true;
            }
        }
        return false;
    }
}
