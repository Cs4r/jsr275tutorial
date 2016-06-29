package cs4r.labs.learning.jsr275;

import org.junit.Test;

import javax.measure.Measurable;
import javax.measure.Measure;
import javax.measure.quantity.*;
import javax.measure.unit.Unit;

import static javax.measure.unit.NonSI.*;
import static javax.measure.unit.SI.*;

/**
 * Created by caguiler on 29/06/2016.
 */
public class FirstExample {

    @Test
    public void basicWorkingWithMeasures() {

        Measure<Double, Volume> volume = Measure.valueOf(33.0, LITER);
        Measure<Double, Duration> time = Measure.valueOf(24.0, HOUR);
    }

    @Test
    public void unitMismatchCausingCompileTimeError() {
        Measurable<Mass> weight = Measure.valueOf(60, KILOGRAM);
        double weightInKg = weight.doubleValue(KILOGRAM);
        double weightInLb = weight.doubleValue(POUND);
        // double wrong = weight.doubleValue(LITER); // Compile time error
    }

    @Test
    public void ParameterizationForDimensionConsistency() {
        Unit<Length> inch = CENTI(METER).times(2.54); // OK.
        // Unit<Length> inch2 = CENTI(LITER).times(2.54); // Compile error.

        Measurable<Duration> d = Measure.valueOf(2, MINUTE); // OK.
        //Measurable<Duration> d = Measure.valueOf(2, WATT); // Compile Error.


        long milliseconds = d.longValue(MILLI(SECOND)); // OK.
        //long milliseconds = d.longValue(MILLI(HERTZ)); // Compile error.
    }


    @Test
    public void CompilerWarningsAndHowToRemoveThem() {
        // Compiler Warnings??
        //Unit<Acceleration> mps2 = Unit.valueOf("m/s²");
        //Unit<Power> watt = JOULE.divide(SECOND);

        // Adding run-time check of dimension consistency removes the errors.
        Unit<Acceleration> mps2Good = Unit.valueOf("m/s²").asType(Acceleration.class);
        Unit<Power> WattGood = JOULE.divide(SECOND).asType(Power.class);

        // Use wildcard parameterized type (no warning and no check).
        Unit<?> kelvinPerSec = KELVIN.divide(SECOND);
    }


    @Test
    public void measureVsMeasurable(){
        Measurable<Mass> weight1 = Measure.valueOf(60, KILOGRAM);

        System.out.println(weight1.doubleValue(GRAM));

        Measure<Integer, Mass> weight2 = Measure.valueOf(60, KILOGRAM);

        System.out.println(weight2.to(GRAM));
    }



}
