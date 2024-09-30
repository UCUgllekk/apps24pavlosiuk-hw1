package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import java.util.InputMismatchException;

import org.junit.Before;
import org.junit.Test;

import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis seriesAnalysisEmpty;
    private TemperatureSeriesAnalysis seriesAnalysis;

    @Before
    public void setUp(){
        double[] temperatureSeriesEmpty = {};
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        seriesAnalysisEmpty = new 
                            TemperatureSeriesAnalysis(temperatureSeriesEmpty);

        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test
    public void testAverage() {
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        // expect exception here
        seriesAnalysisEmpty.average();
    }
        
    @Test
    public void testDeviation(){
        double expResult = 3.74165;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        seriesAnalysisEmpty.deviation();
    }

    @Test
    public void testFindClosestToZero(){
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, 0.00001, 0.2, -0.00001};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.00001;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.0000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroEmptyArray(){
        seriesAnalysisEmpty.findTempClosestToZero();
    }

    @Test
    public void testFindClosestToValue(){
        double tempValue = 2.0;
        double expResult = 3.0;

        double actualResult = seriesAnalysis.findTempClosestToValue(tempValue);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueEmptyArray(){
        double tempValue = 5.0;

        seriesAnalysisEmpty.findTempClosestToValue(tempValue);
    }

    @Test
    public void testFindTempsLessThan(){
        double tempValue = 2.0;
        double[] expResult = {-5.0, 1.0};

        double[] actualResult = seriesAnalysis.findTempsLessThen(tempValue);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsLessThenEmptyArray(){
        double tempValue = 5.0;

        seriesAnalysisEmpty.findTempsLessThen(tempValue);
    }

    @Test
    public void testFindTempsGreaterThan(){
        double tempValue = 2.0;
        double[] expResult = {3.0, 5.0};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(tempValue);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsGreaterThenEmptyArray(){
        double tempValue = 2.0;

        seriesAnalysisEmpty.findTempsGreaterThen(tempValue);
    }

    @Test
    public void testFindTempsInRange(){
        double tempLower = 3.0;
        double tempUpper = 5.0;
        double[] expResult = {3.0, 5.0};

        double[] actualResult = seriesAnalysis.findTempsInRange(tempLower, tempUpper);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsInRangeEmptyArray(){
        double tempLower = 3.0;
        double tempUpper = 5.0;

        seriesAnalysisEmpty.findTempsInRange(tempLower, tempUpper);
    }
    @Test
    public void testReset(){
        int expResult = 0;
        seriesAnalysis.reset();
        double[] seriesAnalysisArray = seriesAnalysis.getTemps();
        int actualResult = seriesAnalysisArray.length;

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResetEmptyArray(){
        seriesAnalysisEmpty.reset();
    }

    @Test
    public void testSortTemps(){
        double[] expResult = {-5.0, 1.0, 3.0, 5.0};

        double[] actualResult = seriesAnalysis.sortTemps();

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSortTempsEmptyArray(){
        seriesAnalysisEmpty.sortTemps();
    }

    @Test
    public void testAddTemps(){
        double[] forExpResult = {3.0, -5.0, 1.0, 5.0, -1.0};
        int expResult = forExpResult.length;

        double[] toAdd = {-1.0};
        int actualResult = seriesAnalysis.addTemps(toAdd);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsBelowAbsoluteZero(){
        double[] newArr = {3.0, 2.5, -274};
        seriesAnalysis.addTemps(newArr);
    }
}
