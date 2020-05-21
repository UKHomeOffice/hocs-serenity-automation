package com.hocs.test.pages.ukvi;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class Points extends BasePage {

    public int getPointsForRefType() {
        String refType = sessionVariableCalled("refType");
        if (refType.toUpperCase().equals("M:REF")) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getPointsForPriority() {
        String priority = sessionVariableCalled("priority");
        int points = 0;
        switch (priority.toUpperCase()) {
            case "STANDARD":
                break;
            case "PRIORITY":
                String refType = sessionVariableCalled("refType");
                if (refType.toUpperCase().equals("M:REF")) {
                    points = 14;
                } else {
                    points = 7;
                }
                break;
            case "IMMEDIATE":
                points = 30;
                break;
            default:
                pendingStep(priority + " is not defined within " + getMethodName());
        }
        return points;
    }

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
