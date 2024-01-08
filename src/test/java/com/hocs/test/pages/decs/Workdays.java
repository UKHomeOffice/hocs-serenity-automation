package com.hocs.test.pages.decs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import config.CaseType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Workdays extends BasePage{

    private List<LocalDate> bankHolidays = new ArrayList<>();

    private void generateBankHolidaysForCaseType(CaseType caseType) {
        addEnglishAndWelshBankHolidays();
        if (caseType.equals(CaseType.FOI)) {
            addAdditionalScottishAndNIBankHolidays();
        }
    }

    private void addEnglishAndWelshBankHolidays() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        bankHolidays.add(LocalDate.parse("02/01/2023", formatter));
        bankHolidays.add(LocalDate.parse("07/04/2023", formatter));
        bankHolidays.add(LocalDate.parse("10/04/2023", formatter));
        bankHolidays.add(LocalDate.parse("01/05/2023", formatter));
        bankHolidays.add(LocalDate.parse("08/05/2023", formatter));
        bankHolidays.add(LocalDate.parse("29/05/2023", formatter));
        bankHolidays.add(LocalDate.parse("28/08/2023", formatter));
        bankHolidays.add(LocalDate.parse("25/12/2023", formatter));
        bankHolidays.add(LocalDate.parse("26/12/2023", formatter));
        bankHolidays.add(LocalDate.parse("01/01/2024", formatter));
        bankHolidays.add(LocalDate.parse("29/03/2024", formatter));
        bankHolidays.add(LocalDate.parse("01/04/2024", formatter));
        bankHolidays.add(LocalDate.parse("06/05/2024", formatter));
        bankHolidays.add(LocalDate.parse("27/05/2024", formatter));
        bankHolidays.add(LocalDate.parse("26/08/2024", formatter));
        bankHolidays.add(LocalDate.parse("25/12/2024", formatter));
        bankHolidays.add(LocalDate.parse("26/12/2024", formatter));
    }

    private void addAdditionalScottishAndNIBankHolidays() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        bankHolidays.add(LocalDate.parse("03/01/2023", formatter));
        bankHolidays.add(LocalDate.parse("17/03/2023", formatter));
        bankHolidays.add(LocalDate.parse("12/07/2023", formatter));
        bankHolidays.add(LocalDate.parse("07/08/2023", formatter));
        bankHolidays.add(LocalDate.parse("30/11/2023", formatter));
        bankHolidays.add(LocalDate.parse("01/01/2024", formatter));
        bankHolidays.add(LocalDate.parse("18/04/2024", formatter));
        bankHolidays.add(LocalDate.parse("21/04/2024", formatter));
        bankHolidays.add(LocalDate.parse("05/05/2024", formatter));
        bankHolidays.add(LocalDate.parse("26/05/2024", formatter));
        bankHolidays.add(LocalDate.parse("25/08/2024", formatter));
        bankHolidays.add(LocalDate.parse("25/12/2024", formatter));
        bankHolidays.add(LocalDate.parse("26/12/2024", formatter));
    }

    private boolean isWorkday(LocalDate inputDate) {
        return (isNotWeekendDay(inputDate) && isNotBankHoliday(inputDate));
    }

    private boolean isNotWeekendDay(LocalDate inputDate) {
        return (inputDate.getDayOfWeek() != DayOfWeek.SATURDAY && inputDate.getDayOfWeek() != DayOfWeek.SUNDAY);
    }

    private boolean isNotBankHoliday(LocalDate inputDate) {
        return !bankHolidays.contains(inputDate);
    }

    public String getDateXWorkdaysAgoForGivenCaseType(int targetAmount, CaseType caseType) {
        generateBankHolidaysForCaseType(caseType);
        int totalWorkDays = 0;
        assert totalWorkDays <= targetAmount;
        LocalDate targetDay = LocalDate.now();
        while (totalWorkDays < targetAmount) {
            targetDay = targetDay.minusDays(1);
            if (isWorkday(targetDay)) {
                totalWorkDays ++;
            }
        }
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        return targetDay.format(formatters);
    }

    public String getDateXWorkdaysFromTodayForGivenCaseType(int targetAmount, CaseType caseType) {
        return getDateXWorkdaysFromSetDateForGivenCaseType(targetAmount, getTodaysDate(), caseType);
    }

    public String getDateXWorkdaysFromSetDateForGivenCaseType(int targetAmount, String startDate, CaseType caseType) {
        generateBankHolidaysForCaseType(caseType);
        int totalWorkDays = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(startDate, formatter);
        while (!isWorkday(date)) {
            date = date.plusDays(1);
        }
        while (totalWorkDays < targetAmount) {
            date = date.plusDays(1);
            if (isWorkday(date)) {
                totalWorkDays += 1;
            }
        }
        return date.format(formatter);
    }
}
