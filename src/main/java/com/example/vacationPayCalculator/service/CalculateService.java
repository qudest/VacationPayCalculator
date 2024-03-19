package com.example.vacationPayCalculator.service;

import com.example.vacationPayCalculator.model.VacationPayDto;

import java.time.LocalDate;

public interface CalculateService {
    VacationPayDto calculate(double averageSalary, int days);

    VacationPayDto calculate(double averageSalary, int days, LocalDate date);
}
