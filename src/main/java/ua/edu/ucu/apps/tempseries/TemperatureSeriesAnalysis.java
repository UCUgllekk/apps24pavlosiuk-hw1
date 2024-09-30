package ua.edu.ucu.apps.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] tempSeries;

    public TemperatureSeriesAnalysis() {
        tempSeries = new double[0];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        final double ERRORNUMBER = -273.0; 
        for  (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < ERRORNUMBER) {
                throw new InputMismatchException();
            }
        }
        tempSeries = temperatureSeries;
    }

    public double average() {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        double sum = 0.0;
        for (double temp: tempSeries) {
            sum += temp;
        }
        return sum/(double) tempSeries.length;
    }

    public double deviation() {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        double average = average();
        double sumOfSquares = 0;
        for  (double temp: tempSeries) {
            sumOfSquares += (temp-average)*(temp-average);
        }
        return Math.sqrt(sumOfSquares/tempSeries.length);
    }

    public double min() {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        double min = Double.POSITIVE_INFINITY;
        for  (double temp: tempSeries) {
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    public double max() {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        double max = Double.NEGATIVE_INFINITY;
        for  (double temp: tempSeries) {
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        double closest = Double.POSITIVE_INFINITY;
        for  (double temp: tempSeries) {
            if (Math.abs(temp) < Math.abs(closest)) {
                closest = temp;
            }
        }
        return Math.abs(closest);
    }

    public double findTempClosestToValue(double tempValue) {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        double closestToCompare = Double.POSITIVE_INFINITY;
        double closest = 0;
        for  (double temp: tempSeries) {
            if (Math.abs(temp - tempValue) < closestToCompare) {
                closest = temp;
                closestToCompare = Math.abs(temp - tempValue);
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        int lenght = 0;
        for (double temp: tempSeries) {
            if (temp < tempValue) {
                lenght++;
            }
        }
        double[] lessThen = new double[lenght];
        int i = 0;
        for (double temp: tempSeries) {
            if (temp < tempValue) {
                lessThen[i] = temp;
                i++;
            }
        }
        return lessThen;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        int lenght = 0;
        for (double temp: tempSeries) {
            if (temp > tempValue) {
                lenght++;
            }
        }
        double[] greaterThen = new double[lenght];
        int i = 0;
        for (double temp: tempSeries) {
            if (temp > tempValue) {
                greaterThen[i] = temp;
                i++;
            }
        }
        return greaterThen;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        int lenght = 0;
        for (double temp: tempSeries) {
            if (temp <= upperBound && temp >= lowerBound) {
                lenght++;
            }
        }
        double[] inRange = new double[lenght];
        int i = 0;
        for (double temp: tempSeries) {
            if (temp <= upperBound && temp >= lowerBound) {
                inRange[i] = temp;
                i++;
            }
        }
        return inRange;
    }

    public void reset() {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        tempSeries = new double[0]; 
    }

    public double[] sortTemps() {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        boolean sorted = false;
        int i = 1;
        while (!sorted && i <= tempSeries.length-1) {
            double temp;
            sorted = true;
            if (tempSeries[i-1] > tempSeries[i]) {
                temp = tempSeries[i];
                tempSeries[i] = tempSeries[i-1];
                tempSeries[i-1] = temp;
                sorted = false;
            }
            i++;
        }
        return tempSeries;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (tempSeries.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        return null;
    }

    public int addTemps(double... temps) {
        int totalSize = tempSeries.length + temps.length;
        if (tempSeries.length < tempSeries.length + temps.length) {
            double[] newTempSeries = new double[totalSize*2];
            int i = 0;
            for (double temp: tempSeries) {
                newTempSeries[i] = temp;
                i++;
            }
            for (double temp: temps) {
                newTempSeries[i] = temp;
                i++;
            }
            tempSeries = newTempSeries;
        }
        else {
            int i = tempSeries.length;
            for (double temp: temps) {
                tempSeries[i] = temp;
                i++;
            }
        }
        return totalSize;
    }
}