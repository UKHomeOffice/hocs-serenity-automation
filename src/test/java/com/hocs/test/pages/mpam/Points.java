package com.hocs.test.pages.mpam;

import com.hocs.test.pages.BasePage;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.jetbrains.annotations.NotNull;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class Points extends BasePage {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

    private List<String> holidayDays = new ArrayList<>();

    public int getPointsForRefType() {
        String refType = sessionVariableCalled("refType");
        if (refType.toUpperCase().equals("Ministerial")) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getPointsForUrgency() {
        String urgency = sessionVariableCalled("urgency");
        int points = 0;
        switch (urgency.toUpperCase()) {
            case "STANDARD":
                break;
            case "PRIORITY":
                String refType = sessionVariableCalled("refType");
                if (refType.toUpperCase().equals("MINISTERIAL")) {
                    points = 14;
                } else {
                    points = 7;
                }
                break;
            case "IMMEDIATE":
                points = 30;
                break;
            default:
                pendingStep(urgency + " is not defined within " + getMethodName());
        }
        return points;
    }

    public String getDateANumberOfWorkDaysAgo(int numberOfWorkDays) {
        int daysAgo = 0;
        Calendar cal = Calendar.getInstance();
        while (daysAgo < numberOfWorkDays) {
            cal.add(Calendar.DATE, -1);
            if (checkIfWeekday(cal)) {
                daysAgo++;
            }
        }
        return dateFormat.format(cal.getTime());
    }

    private boolean checkIfWeekday(Calendar date) {
        boolean weekday = (date.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY || date.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY);
        return weekday;
    }

//    holidayDays
//
//        LocalDate receivedDate = LocalDate.parse();
//        String deadlineString = null;
//                LocalDate displayedDeadlineDate = LocalDate.parse(deadlineString);
//                LocalDate newDate = receivedDate;
//                while (newDate != displayedDeadlineDate && workingDaysAfterReceived <= expectedNumberOfDays) {
//                    newDate = receivedDate.plusDays(1);
//                    Object dayOfWeek = newDate.getDayOfWeek().toString();
//                    Boolean leapYear = newDate.isLeapYear();
//                    if (dayOfWeek != "Saturday" && dayOfWeek != "Sunday" && !leapYear) {
//                        workingDaysAfterReceived += 1;
//                    }
//                }

//    public int getPointsForAge() {
////        int numberOfWorkdaysPast = getNumberOfWorkdays();
//        return numberOfWorkdaysPast;
//
//    }

//    public int getNumberOfWorkdays() {
//        public int calculateWorkingDaysElapsedForCaseType(LocalDate fromDate) {
//            List<LocalDate> exemptions = holidayDateRepository.findAllByCaseType(caseType).stream().map(ExemptionDate::getDate).collect(Collectors.toList());
//            LocalDate now = LocalDate.now();
//            if (fromDate == null || now.isBefore(fromDate) || now.isEqual(fromDate)) {
//                return 0;
//            }
//            LocalDate date = fromDate ;
//            int workingDays = 0;
//            while( date.isBefore( now ) ) {
//                if(!DateUtils.isWeekend(date) && !exemptions.contains(date)) {
//                    workingDays++;
//                }
//                date = date.plusDays( 1 );
//            }
//            return workingDays;
//        }
//    }

}
