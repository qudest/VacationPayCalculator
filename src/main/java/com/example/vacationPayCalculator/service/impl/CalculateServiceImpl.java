package com.example.vacationPayCalculator.service.impl;

import com.example.vacationPayCalculator.model.VacationPayDto;
import com.example.vacationPayCalculator.service.CalculateService;
import com.example.vacationPayCalculator.service.VacationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CalculateServiceImpl implements CalculateService {
    private VacationService vacationService;

    @Override
    public VacationPayDto calculate(double averageSalary, int days) {
        if (averageSalary < 0 || days < 0) {
            throw new IllegalArgumentException();
        }
        return new VacationPayDto(averageSalary / 29.3d * days);
    }

    @Override
    public VacationPayDto calculate(double averageSalary, int days, LocalDate date) {
        return calculate(averageSalary, calculateWorkDays(date, days));
    }

    private int calculateWorkDays(LocalDate date, int countOfDaysAfterDate) {
        int count = 0;
        LocalDate currentDate = date;
        for (int i = 0; i < countOfDaysAfterDate; i++) {
            if (vacationService.isVacation(currentDate)) {
                currentDate = currentDate.plusDays(1L);
                continue;
            }
            count++;
            currentDate = currentDate.plusDays(1L);
        }
        return count;
    }
}
