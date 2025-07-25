package com.sobolev.calculatorapp_info_tecs_25

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NativeCalculatorInstrumentedTest {


    @Test
    fun testAdd() {
        assertEquals(5.0, NativeCalculator.add(2.0, 3.0), 0.0001)
    }

    @Test
    fun testSubtract() {
        assertEquals(1.0, NativeCalculator.subtract(4.0, 3.0), 0.0001)
    }

    @Test
    fun testMultiply() {
        assertEquals(12.0, NativeCalculator.multiply(4.0, 3.0), 0.0001)
    }

    @Test
    fun testDivide() {
        assertEquals(2.0, NativeCalculator.divide(6.0, 3.0), 0.0001)
    }

    @Test
    fun testDivideByZero() {
        val result = NativeCalculator.divide(6.0, 0.0)
        assert(result.isNaN())
    }

    @Test
    fun testMod() {
        assertEquals(1.0, NativeCalculator.modulo(10.0, 3.0), 0.0001)
    }
}
