package com.unicauca.sga.testService.Domain.Services;

import java.time.LocalDateTime;

public interface IDateTimeProvider {
    LocalDateTime getActualDateTime();
    boolean isInCurrentSemester(LocalDateTime date);
}
