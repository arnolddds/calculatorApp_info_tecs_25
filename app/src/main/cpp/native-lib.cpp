#include <jni.h>
#include <android/log.h>
#include <cmath>

#define LOG_TAG "NativeCalc"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

extern "C" JNIEXPORT jdouble JNICALL
Java_com_sobolev_calculatorapp_1info_1tecs_125_NativeCalculator_add(JNIEnv *, jobject, jdouble a, jdouble b) {
    LOGI("Add: %f + %f", a, b);
    return a + b;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_com_sobolev_calculatorapp_1info_1tecs_125_NativeCalculator_subtract(JNIEnv *, jobject, jdouble a, jdouble b) {
    LOGI("Subtract: %f - %f", a, b);
    return a - b;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_com_sobolev_calculatorapp_1info_1tecs_125_NativeCalculator_multiply(JNIEnv *, jobject, jdouble a, jdouble b) {
    LOGI("Multiply: %f * %f", a, b);
    return a * b;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_com_sobolev_calculatorapp_1info_1tecs_125_NativeCalculator_divide(JNIEnv *, jobject, jdouble a, jdouble b) {
    if (b == 0.0) {
        LOGI("Attempt to divide by zero");
        return NAN;
    }
    LOGI("Divide: %f / %f", a, b);
    return a / b;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_com_sobolev_calculatorapp_1info_1tecs_125_NativeCalculator_modulo(JNIEnv *, jobject, jdouble a, jdouble b) {
    if (b == 0.0) {
        LOGI("Attempt to modulo by zero");
        return NAN;
    }
    LOGI("Modulo: %f %% %f", a, b);
    return fmod(a, b);
}

