package com.weather;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by anilkothari on 04/04/17.
 * Package com.weather
 * Project WeatherApp
 */
public class ALERT_TYPETest {
    private WeatherAlert weatherAlert;

    @Before
    public void setUp() throws Exception {
        weatherAlert = new WeatherAlert();

    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void testWeatherAlertRequired(){
         boolean required = weatherAlert.checkIsAlertRequired(232);
        assertTrue(required);
    }

    @Test
    public void testWeatherAlertTitle(){
        boolean required = weatherAlert.checkIsAlertRequired(958);
        assertTrue(required);

        String GALE_TITLE = "Prediction: STRONG WINDS";
        assertEquals(GALE_TITLE, weatherAlert.getTitle());
    }


    @Test
    public void testWeatherAlertSubTitle(){
        boolean required = weatherAlert.checkIsAlertRequired(958);
        assertTrue(required);

        String GALE_MESSAGE = "High Winds outside, Please avoid going out, if not extremely urgent";
        assertEquals(GALE_MESSAGE, weatherAlert.getMessage());
    }


    @Test
    public void testWeatherAlertNotRequired(){
        boolean notRequired = weatherAlert.checkIsAlertRequired(100);
        assertFalse(notRequired);
    }





}