package com.example.vacationPayCalculator.service;

import com.example.vacationPayCalculator.model.VacationPayDto;
import com.example.vacationPayCalculator.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CalculateServiceTest {
    @Autowired
    private CalculateService calculateService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCalculate() {
        double averageSalary = 29300.0;
        int days = 10;

        VacationPayDto expected = new VacationPayDto(10000d);
        VacationPayDto result = calculateService.calculate(averageSalary, days);

        assertEquals(expected.getVacationPay(), result.getVacationPay());
    }

    @Test
    void testCalculateWithDate() {
        double averageSalary = 29300.0;
        int days = 10;
        String date = "01-01-2024";

        VacationPayDto expected = new VacationPayDto(3000);
        VacationPayDto result = calculateService.calculate(averageSalary, days, DateUtils.parseDate(date));

        assertEquals(expected.getVacationPay(), result.getVacationPay());
    }

    @Test
    void testCalculateForZeroDays() {
        double averageSalary = 29300.0;
        int days = 0;

        VacationPayDto result = calculateService.calculate(averageSalary, days);

        assertEquals(0.0, result.getVacationPay());
    }

    @Test
    void testCalculateWithNegativeSalary() {
        double averageSalary = -1000.0;
        int days = 10;

        VacationPayDto result = calculateService.calculate(averageSalary, days);

        assertEquals(0.0, result.getVacationPay());
    }

    @Test
    void testCalculateWithNegativeDays() {
        double averageSalary = 29300.0;
        int days = -10;

        VacationPayDto result = calculateService.calculate(averageSalary, days);

        assertEquals(0.0, result.getVacationPay());
    }
}
