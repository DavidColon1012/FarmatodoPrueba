package com.david.farmatodoprueba;

import com.david.farmatodoprueba.models.Calculate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculatorTest {

    @Test
    public void multiple_test() {
        Calculate calculate = new Calculate();
        assertEquals(0, calculate.getMultiple(0));
        assertEquals(3, calculate.getMultiple(6));
        assertEquals(3, calculate.getMultiple(12));
    }
}