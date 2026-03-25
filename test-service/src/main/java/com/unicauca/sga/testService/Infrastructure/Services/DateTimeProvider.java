package com.unicauca.sga.testService.Infrastructure.Services;

import com.unicauca.sga.testService.Domain.Services.IDateTimeProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DateTimeProvider implements IDateTimeProvider {

    @Override
    public LocalDateTime getActualDateTime() {
        return LocalDateTime.now();
    }

    @Override
    public boolean isInCurrentSemester(LocalDateTime date) {
        if(date == null) return false;

        LocalDateTime now = LocalDateTime.now();

        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();
        int currentSemester = (currentMonth <= 6) ? 1 : 2;

        int dateYear = date.getYear();
        int dateMonth = date.getMonthValue();
        int dateSemester = (dateMonth <= 6) ? 1 : 2;

        return currentYear == dateYear && currentSemester == dateSemester;
    }
}
