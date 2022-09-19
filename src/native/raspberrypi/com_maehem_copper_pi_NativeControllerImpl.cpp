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

    int vals[3];
    int secs;
    int micros;
    vals[0] = gpioTime( type, &secs, &micros );
    vals[1] = secs;
    vals[2] = micros;

    jintArray ret = env->NewIntArray(3);
    env->SetIntArrayRegion(ret,0,3,vals);
    return ret;
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
  (JNIEnv* env, jobject obj, jlong delay) {
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

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    spiOpen
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_spiOpen
  (JNIEnv *env, jobject obj, jint chan, jint baud, jint flags) {
    return spiOpen(chan,baud,flags);
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    spiClose
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_spiClose
  (JNIEnv *env, jobject obj, jint handle) {
    return spiClose(handle);
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    spiRead
 * Signature: (I[BI)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_spiRead
  (JNIEnv *env, jobject obj, jint handle, jbyteArray buffer, jint count) {
    jbyte* b = env->GetByteArrayElements(env, buffer, NULL);
    int ret = spiRead( handle, (char *)b, count );
    env->ReleaseByteArrayElements(env, buffer, b, 0);
    return ret;
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    spiXfer
 * Signature: (I[B[BI)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_spiXfer
  (JNIEnv *env, jobject obj, jint handle, jbyteArray txBuf, jbyteArray rxBuf, jint count) {
    jbyte* bTx = env->GetByteArrayElements(env, txBuf, NULL);
    jbyte* bRx = env->GetByteArrayElements(env, rxBuf, NULL);
    int ret = spiXfer( handle, (char *)bTx, (char *)bRx, count );
    env->ReleaseByteArrayElements(env, txBuf, bTx, 0);
    env->ReleaseByteArrayElements(env, rxBuf, bRx, 0);

    return ret;
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cOpen
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cOpen
  (JNIEnv *env, jobject obj, jint bus, jint addr, jint flags) {
    return i2cOpen(bus,addr,flags);
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cClose
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cClose
  (JNIEnv *env, jobject obj, jint handle) {
    return i2cClose(handle);
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cWriteQuick
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cWriteQuick
  (JNIEnv *env, jobject obj, jint handle, jint bit) {
    return i2cWriteQuick(handle, bit);
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cWriteByte
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cWriteByte
  (JNIEnv *env, jobject obj, jint handle, jint bVal) {
    return i2cWriteByte(handle, bVal);
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cReadByte
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cReadByte
  (JNIEnv *env, jobject obj, jint handle) {
    return i2cReadByte( handle );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cWriteByteData
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cWriteByteData
  (JNIEnv *env, jobject obj, jint handle, jint i2cReg, jint bVal) {
    return i2cWriteByteData( handle, i2cReg, bVal );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cWriteWordData
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cWriteWordData
  (JNIEnv *env, jobject obj, jint handle, jint i2cReg, jint wVal) {
    return i2cWriteWordData( handle, i2cReg, wVal );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cReadByteData
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cReadByteData
  (JNIEnv *env, jobject obj, jint handle, jint i2cReg) {
    return i2cReadByteData( handle, i2cReg );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cReadWordData
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cReadWordData
  (JNIEnv *env, jobject obj, jint handle, jint i2cReg) {
    return i2cReadWordData( handle, i2cReg );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cProcessCall
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cProcessCall
  (JNIEnv *env, jobject obj, jint handle, jint i2cReg, jint wVal) {
    return i2cProcessCall( handle, i2cReg, wVal );
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cWriteBlockData
 * Signature: (II[BI)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cWriteBlockData
  (JNIEnv *env, jobject obj, jint handle, jint i2cReg, jbyteArray buffer, jint count) {
    jbyte* b = env->GetByteArrayElements(env, buffer, NULL);
    int ret = i2cWriteBlockData( handle, i2cReg, (char *)b, count );
    env->ReleaseByteArrayElements(env, buffer, b, 0);

    return ret;
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cReadBlockData
 * Signature: (II[B)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cReadBlockData
  (JNIEnv *env, jobject obj, jint handle, jint i2cReg, jbyteArray buffer) {
    jbyte* b = env->GetByteArrayElements(env, buffer, NULL);
    int ret = i2cReadBlockData( handle, i2cReg, (char *)b );
    env->ReleaseByteArrayElements(env, buffer, b, 0);

    return ret;
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cBlockProcessCall
 * Signature: (II[BI)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cBlockProcessCall
  (JNIEnv *env, jobject obj, jint handle, jint i2cReg, jbyteArray buffer, jint count) {
    jbyte* b = env->GetByteArrayElements(env, buffer, NULL);
    int ret = i2cBlockProcessCall( handle, i2cReg, (char *)b, count );
    env->ReleaseByteArrayElements(env, buffer, b, 0);

    return ret;
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cReadI2CBlockData
 * Signature: (II[BI)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cReadI2CBlockData
  (JNIEnv *env, jobject obj, jint handle, jint i2cReg, jbyteArray buffer, jint count) {
    jbyte* b = env->GetByteArrayElements(env, buffer, NULL);
    int ret = i2cReadI2CBlockData( handle, i2cReg,  (char *)b, count );
    env->ReleaseByteArrayElements(env, buffer, b, 0);

    return ret;
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cWriteI2CBlockData
 * Signature: (II[BI)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cWriteI2CBlockData
  (JNIEnv *env, jobject obj, jint handle, jint i2cReg, jbyteArray buffer, jint count) {
    jbyte* b = env->GetByteArrayElements(env, buffer, NULL);
    int ret = i2cWriteI2CBlockData( handle, i2cReg, (char *)b, count );
    env->ReleaseByteArrayElements(env, buffer, b, 0);

    return ret;
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cReadDevice
 * Signature: (I[BI)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cReadDevice
  (JNIEnv *env, jobject obj, jint handle, jbyteArray buffer, jint count) {
    jbyte* b = env->GetByteArrayElements(env, buffer, NULL);
    int ret = i2cReadDevice( handle, (char *)b, count );
    env->ReleaseByteArrayElements(env, buffer, b, 0);

    return ret;
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cWriteDevice
 * Signature: (I[BI)I
 */
JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cWriteDevice
  (JNIEnv *env, jobject obj, jint handle, jbyteArray buffer, jint count) {
    jbyte* b = env->GetByteArrayElements(env, buffer, NULL);
    int ret = i2cWriteDevice( handle, (char *)b, count );
    env->ReleaseByteArrayElements(env, buffer, b, 0);

    return ret;
}

/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cSwitchCombined
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cSwitchCombined
  (JNIEnv *env, jobject obj, jint setting) {
    i2cSwitchCombined( setting );
}

// i2cSegments not implemented yet.
/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cSegments
 * Signature: (I[Ljava/lang/Object;I)I
 */
// JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cSegments
//  (JNIEnv *env, jobject obj, jint handle, jobjectArray segs, jint nSegs) {
//    return i2cSegments( handle, segs, nSegs );
//}

// i2cZip not implemented yet
/*
 * Class:     com_maehem_copper_pi_NativeControllerImpl
 * Method:    i2cZip
 * Signature: (I[BI[BI)I
 */
// JNIEXPORT jint JNICALL Java_com_maehem_copper_pi_NativeControllerImpl_i2cZip
//  (JNIEnv *env, jobject obj, jint, jbyteArray, jint, jbyteArray, jint) {}

#ifdef __cplusplus
}
#endif
