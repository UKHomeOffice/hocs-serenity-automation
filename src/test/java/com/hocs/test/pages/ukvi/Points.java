package com.hocs.test.pages.ukvi;

import com.hocs.test.pages.decs.BasePage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
}
