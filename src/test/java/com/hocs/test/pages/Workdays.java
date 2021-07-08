package com.hocs.test.pages;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Workdays extends BasePage{

    private List<LocalDate> bankHolidays = new ArrayList<>();

    public Workdays(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        bankHolidays.add(LocalDate.parse("10/04/2020", formatter));
        bankHolidays.add(LocalDate.parse("13/04/2020", formatter));
        bankHolidays.add(LocalDate.parse("08/05/2020", formatter));
        bankHolidays.add(LocalDate.parse("25/05/2020", formatter));
        bankHolidays.add(LocalDate.parse("31/08/2020", formatter));
        bankHolidays.add(LocalDate.parse("25/12/2020", formatter));
        bankHolidays.add(LocalDate.parse("28/12/2020", formatter));
        bankHolidays.add(LocalDate.parse("01/01/2021", formatter));
        bankHolidays.add(LocalDate.parse("02/04/2021", formatter));
        bankHolidays.add(LocalDate.parse("05/04/2021", formatter));
        bankHolidays.add(LocalDate.parse("03/05/2021", formatter));
        bankHolidays.add(LocalDate.parse("31/05/2021", formatter));
        bankHolidays.add(LocalDate.parse("30/08/2021", formatter));
        bankHolidays.add(LocalDate.parse("27/12/2021", formatter));
        bankHolidays.add(LocalDate.parse("28/12/2021", formatter));
        bankHolidays.add(LocalDate.parse("03/01/2022", formatter));
    }

    public boolean isWorkday(LocalDate inputDate) {
        return (isNotWeekendDay(inputDate) && isNotBankHoliday(inputDate));
    }

    private boolean isNotWeekendDay(LocalDate inputDate) {
        return (inputDate.getDayOfWeek() != DayOfWeek.SATURDAY && inputDate.getDayOfWeek() != DayOfWeek.SUNDAY);
    }

    private boolean isNotBankHoliday(LocalDate inputDate) {
        return !bankHolidays.contains(inputDate);
    }

    public String getDateXWorkdaysAgo(int targetAmount) {
        int totalWorkDays = 0;
        assert totalWorkDays <= targetAmount;
        LocalDate targetDay = LocalDate.now();
        while (totalWorkDays < targetAmount) {
            targetDay = targetDay.minusDays(1);
            if (isWorkday(targetDay)) {
                totalWorkDays ++;
            }
        }
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        return targetDay.format(formatters);
    }
}
