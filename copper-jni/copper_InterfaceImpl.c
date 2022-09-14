/**
  * @filename       : copper_InterfaceImpl.c
  * @project        : JWiringPi
  * @date           : June 26 2017
  * @description:
  *      Java wrapper of Arduino like Wiring library for the Raspberry Pi.
  *      The implements are based on WiringPi library.
  *      WiringPi Library Copyright (c) 2012-2017 Gordon Henderson
  *      JWiringPi project Copyright (c) 2017 @soonuse from GitHub
  *      Copper project Copyright (c) 2022 @maehem from GitHub
  ***********************************************************************
  * This file is part of JWiringPi interface.
  *
  * The purpose of JWiringPi project is to create a convenient IO control
  * interface (containing the implements of class) for Raspberry Pi Java
  * programming.
  *
  * JWiringPi is free software: you can redistribute it and/or modify
  * it under the terms of the General Public License (GPL).
  *
  * JWiringPi is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public License
  * along with wiringPi.  If not, see <http://www.gnu.org/licenses/>.
  ***********************************************************************
 */

#include <jni.h>
#include <stdlib.h>
#include <unistd.h>
#include <wiringPi.h>
#include <wiringPiSPI.h>
#include <wiringPiI2C.h>
/* Implements for class copper_InterfaceImpl */

#ifndef _Included_copper_InterfaceImpl
#define _Included_copper_InterfaceImpl
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiSetup
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiSetup
  (JNIEnv *env, jobject obj) 
{
    return wiringPiSetup();
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiSetupGpio
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiSetupGpio
  (JNIEnv *env, jobject obj) 
{
    return wiringPiSetupGpio();
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiSetupPhys
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiSetupPhys
  (JNIEnv *env, jobject obj) 
{
    return wiringPiSetupPhys();
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiSetupSys
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiSetupSys
  (JNIEnv *env, jobject obj) 
{
    return wiringPiSetupSys();
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    pinMode
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_copper_InterfaceImpl_pinMode
  (JNIEnv *env, jobject obj, jint pin, jint mode) 
{
    pinMode(pin, mode);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    pullUpDnControl
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_copper_InterfaceImpl_pullUpDnControl
  (JNIEnv *env, jobject obj, jint pin, jint pud) 
{
    pullUpDnControl(pin, pud);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    digitalWrite
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_copper_InterfaceImpl_digitalWrite
  (JNIEnv *env, jobject obj, jint pin, jint value) 
{
    digitalWrite(pin, value);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    pwmWrite
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_copper_InterfaceImpl_pwmWrite
  (JNIEnv *env, jobject obj, jint pin, jint value) 
{
    pwmWrite(pin, value);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    digitalRead
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_digitalRead
  (JNIEnv *env, jobject obj, jint pin) 
{
    return digitalRead(pin);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    analogRead
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_analogRead
  (JNIEnv *env, jobject obj, jint pin) 
{
    return analogRead(pin);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    analogWrite
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_copper_InterfaceImpl_analogWrite
  (JNIEnv *env, jobject obj, jint pin, jint value) 
{
    analogWrite(pin, value);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    millis
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_millis
  (JNIEnv *env, jobject obj) 
{
    return millis();
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    micros
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_micros
  (JNIEnv *env, jobject obj) 
{
    return micros();
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    delay
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_copper_InterfaceImpl_delay
  (JNIEnv *env, jobject obj, jint howLong) 
{
    delay(howLong);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    delayMicroseconds
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_copper_InterfaceImpl_delayMicroseconds
  (JNIEnv *env, jobject obj, jint howLong) 
{
    delayMicroseconds(howLong);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiSPISetup
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiSPISetup
  (JNIEnv *env, jobject obj, jint channel, jint speed) 
{
    return wiringPiSPISetup (channel, speed);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiSPIDataRW
 * Signature: (I[BI)I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiSPIDataRW
  (JNIEnv *env, jobject obj, jint channel, jbyteArray data, jint length) 
{
    int i;
    unsigned char *buffer;

    // dynamically allocate buffer size
    buffer = malloc(length);

    // copy the bytes from the data array argument into a native unsigned character buffer
    jbyte *body = (*env)->GetByteArrayElements(env, data, 0);
    for (i = 0; i < length; i++) {
        buffer[i] = (unsigned char) body[i];
    }

    jint result = wiringPiSPIDataRW(channel, (unsigned char *)buffer, length);

    // copy the resulting buffer bytes back into the data array argument
    for (i = 0; i < length; i++) {
        body[i] = buffer[i];
    }
    (*env)->ReleaseByteArrayElements(env, data, body, 0);

    // free allocated buffer memory
    free(buffer);

    return result;
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiI2CSetup
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiI2CSetup
  (JNIEnv *env, jobject obj, jint devId) 
{
    return wiringPiI2CSetup(devId);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiI2CRead
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiI2CRead
  (JNIEnv *env, jobject obj, jint handle) 
{
    return wiringPiI2CRead(handle);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiI2CWrite
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiI2CWrite__II
  (JNIEnv *env, jobject obj, jint handle, jint data) 
{
    return wiringPiI2CWrite(handle, data);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiI2CWrite
 * Signature: (I[BI)I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiI2CWrite__I_3BI
  (JNIEnv *env, jobject obj, jint handle, jbyteArray data, jint length)
{
    int i;
    unsigned char *buffer;

    // dynamically allocate buffer size
    buffer = malloc(length);

    // copy the bytes from the data array argument into a native unsigned character buffer
    jbyte *body = (*env)->GetByteArrayElements(env, data, 0);
    for (i = 0; i < length; i++) {
        buffer[i] = (unsigned char) body[i];
    }

    // call the system write() to write continuously to I2C device
    jint result = write(handle, (unsigned char *)buffer, length);

    // release the byte array elements
    (*env)->ReleaseByteArrayElements(env, data, body, 0);

    // free allocated buffer memory
    free(buffer);

    return result;
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiI2CWriteReg8
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiI2CWriteReg8
  (JNIEnv *env, jobject obj, jint handle, jint reg, jint data) 
{
    return wiringPiI2CWriteReg8(handle, reg, data);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiI2CWriteReg16
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiI2CWriteReg16
  (JNIEnv *env, jobject obj, jint handle, jint reg, jint data) 
{
    return wiringPiI2CWriteReg16(handle, reg, data);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiI2CReadReg8
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiI2CReadReg8
  (JNIEnv *env, jobject obj, jint handle, jint reg) 
{
    return wiringPiI2CReadReg8(handle, reg);
}

/*
 * Class:     copper_InterfaceImpl
 * Method:    wiringPiI2CReadReg16
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_copper_InterfaceImpl_wiringPiI2CReadReg16
  (JNIEnv *env, jobject obj, jint handle, jint reg) 
{
    return wiringPiI2CReadReg16(handle, reg);
}

#ifdef __cplusplus
}
#endif
#endif
