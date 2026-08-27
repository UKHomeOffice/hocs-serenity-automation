package com.hocs.test.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;

public class ScreenplayHooks {

    @Before
    public void initialiseStage() {
        setTheStage(new OnlineCast());
    }
}