package com.sobolev.calculatorapp_info_tecs_25

object NativeCalculator {
    init {
        System.loadLibrary("native-lib")
    }

    @JvmStatic external fun add(a: Double, b: Double): Double
    @JvmStatic external fun subtract(a: Double, b: Double): Double
    @JvmStatic external fun multiply(a: Double, b: Double): Double
    @JvmStatic external fun divide(a: Double, b: Double): Double
    @JvmStatic external fun modulo(a: Double, b: Double): Double

}
