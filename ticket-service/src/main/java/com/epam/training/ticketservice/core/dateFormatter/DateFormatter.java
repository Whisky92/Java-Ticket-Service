package com.epam.training.ticketservice.core.dateFormatter;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@EqualsAndHashCode
public class DateFormatter {

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public Optional<Date> formatStringToDate(String date) {
        Date formattedDate;
        try {
            formattedDate = format.parse(date);
        } catch (ParseException e) {
            return Optional.empty();
        }
        return Optional.of(formattedDate);
    }

    public String formatDateToString(Date date) {
        return format.format(date);
    }
}
