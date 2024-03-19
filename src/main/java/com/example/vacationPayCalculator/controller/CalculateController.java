package com.example.vacationPayCalculator.controller;

import com.example.vacationPayCalculator.model.VacationPayDto;
import com.example.vacationPayCalculator.service.CalculateService;
import com.example.vacationPayCalculator.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class CalculateController {

    private CalculateService calculateService;

    @RequestMapping("/calculate")
    public VacationPayDto calculateVacationPay(@RequestParam double averageSalary, @RequestParam int days, @RequestParam(required = false) String date) {
        if (date != null) {
            return calculateService.calculate(averageSalary, days, DateUtils.parseDate(date));
        }
        return calculateService.calculate(averageSalary, days);
    }
}
