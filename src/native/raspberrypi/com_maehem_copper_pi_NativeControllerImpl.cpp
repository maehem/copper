/*
 * C calls to PiGPIO
 * 
 */

#include "com_maehem_copper_pi_NativeControllerImpl.h"
#include <pigpio.h>

/* Header for class com_maehem_copper_pi_NativeControllerImpl */

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    initialise
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_initialise
  (JNIEnv* env, jobject obj) {
    return gpioInitialise();
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    terminate
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_terminate
  (JNIEnv* env, jobject obj) {
    gpioTerminate();
    return 0;
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    setMode
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_setMode
  (JNIEnv* env, jobject obj, jint pin, jint mode){
    return gpioSetMode( pin, mode);
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    getMode
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_getMode
  (JNIEnv* env, jobject obj, jint pin) {
    return gpioGetMode( pin );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    setPullUpDown
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_setPullUpDown
  (JNIEnv* env, jobject obj, jint pin, jint pud) {
    return gpioSetPullUpDown( pin, pud );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    read
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_read
  (JNIEnv* env, jobject obj, jint pin) {
    return gpioRead( pin );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    write
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_write
  (JNIEnv* env, jobject obj, jint pin, jint level) {
    return gpioWrite( pin, level );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    pwm
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_pwm
  (JNIEnv* env, jobject obj, jint pin, jint duty) {
    return gpioPWM( pin, duty );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    pwmGetDutyCycle
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_pwmGetDutyCycle
  (JNIEnv* env, jobject obj, jint pin) {
    return gpioGetPWMdutycycle( pin );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    pwmSetFrequency
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_pwmSetFrequency
  (JNIEnv* env, jobject obj, jint pin, jint freq) {
    return gpioSetPWMfrequency( pin, freq );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    pwmGetFrequency
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_pwmGetFrequency
  (JNIEnv* env, jobject obj, jint pin) {
    return gpioGetPWMfrequency( pin );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    pwmSetRange
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_pwmSetRange
  (JNIEnv* env, jobject obj, jint pin, jint range) {
    return gpioSetPWMrange( pin, range );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    pwmGetRange
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_pwmGetRange
  (JNIEnv* env, jobject obj, jint pin) {
    return gpioGetPWMrange( pin );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    pwmGetRealRange
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_pwmGetRealRange
  (JNIEnv* env, jobject obj, jint pin) {
    return gpioGetPWMrealRange( pin );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    servo
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_servo
  (JNIEnv* env, jobject obj, jint pin, jint pulseWidth) {
    return gpioServo( pin, pulseWidth );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    servoGetPulsewidth
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_servoGetPulsewidth
  (JNIEnv* env, jobject obj, jint pin) {
    return gpioGetServoPulsewidth( pin );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    getVersion
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_getVersion
  (JNIEnv* env, jobject obj) {
    return gpioVersion();
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    getHardwareRevision
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_getHardwareRevision
  (JNIEnv* env, jobject obj) {
    return gpioHardwareRevision();
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    time
 * Signature: (I)[I
 */
JNIEXPORT jintArray JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_time
  (JNIEnv* env, jobject obj, jint type) {
    jintArray result;
    result = (*env).NewIntArray(env, jsize);
    if (result == NULL) {
        return NULL; /* out of memory error thrown */
    }

    jint vals[3];
    int secs;
    int micros;
    vals[0] = gpioTime( type, &secs, &micros );
    vals[1] = secs;
    vals[2] = micros;

    (*env).SetIntArrayRegion(env, result, 0, jsize, vals);
    return result;
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    sleep
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_sleep
  (JNIEnv* env, jobject obj, jint pin, jint seconds, jint micros) {
    return gpioSleep( pin, seconds, micros );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    delay
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_delay
  (JNIEnv* env, jobject, jlong delay) {
    return gpioDelay( delay );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    tick
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_tick
  (JNIEnv* env, jobject obj) {
    return gpioTick();
}

#ifdef __cplusplus
}
#endif
