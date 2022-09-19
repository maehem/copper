/*
 * Native interface for PiGPIO on Raspberry Pi
 * 
 * To generate the .c and .h files run:
 *    javac -h .  EssentialInterface.java UtilitiesInterface.java SPIInterface.java I2CInterface.java ServoInterface.java BasicInterface.java PWMInterface.java PiGPIOInterface.java NativeControllerImpl.java

 */
package com.maehem.copper.pi;

/**
 *  TODO:  Rename to NativeController
 * 
 * @author Mark J Koch
 */
public class NativeControllerImpl extends PiGPIOInterface {

    public NativeControllerImpl() {
        LibraryLoader l = new LibraryLoader();
        l.load();
    }
    
    @Override
    public native int initialise();

    @Override
    public native int terminate();
    
    // BASIC	
    @Override
    public native int setMode(int pin, int mode);

    @Override
    public native int getMode(int pin);

    @Override
    public native int setPullUpDown(int pin, int pud);

    @Override
    public native int read(int pin);

    @Override
    public native int write(int pin, int level);
    
    // PWM (overrides servo commands on same GPIO)	
    @Override
    public native int pwm(int pin, int duty);

    @Override
    public native int pwmGetDutyCycle(int pin);

    @Override
    public native int pwmSetFrequency(int pin, int freq);

    @Override
    public native int pwmGetFrequency(int pin);

    @Override
    public native int pwmSetRange(int pin, int range);

    @Override
    public native int pwmGetRange(int pin);

    @Override
    public native int pwmGetRealRange(int pin);

    //SERVO      (overrides PWM commands on same GPIO)	
    @Override
    public native int servo(int pin, int pulseWidth);

    @Override
    public native int servoGetPulsewidth(int pin);
    

    // UTILITIES
    @Override
    public native int getVersion();

    @Override
    public native int getHardwareRevision();

    @Override
    public native int[] time(int type);

    @Override
    public native int sleep(int type, int seconds, int micros);

    @Override
    public native int delay(long micros);

    @Override
    public native int tick();

    //
    // SPI Functions
    //
    
    @Override
    public native int spiOpen(int spiChan, int baud, int spiFlags);
         
    @Override
    public native int spiClose(int handle);
         
    @Override
    public native int spiRead(int handle, byte[] buf, int count);

    @Override
    public native int spiXfer(int handle, byte[] txBuf, byte[] rxBuf, int count);

    //
    // I2C Functions
    //
    
    @Override
    public native int i2cOpen(int i2cBus, int i2cAddr, int i2cFlags);

    @Override
    public native int i2cClose(int handle);

    @Override
    public native int i2cWriteQuick(int handle, int bit);

    @Override
    public native int i2cWriteByte(int handle, int bVal);
         
    @Override
    public native int i2cReadByte(int handle);
         
    @Override
    public native int i2cWriteByteData(int handle, int i2cReg, int bVal);

    @Override
    public native int i2cWriteWordData(int handle, int i2cReg, int wVal);
         
    @Override
    public native int i2cReadByteData(int handle, int i2cReg);

    @Override
    public native int i2cReadWordData(int handle, int i2cReg);

    @Override
    public native int i2cProcessCall(int handle, int i2cReg, int wVal);
         
    @Override
    public native int i2cWriteBlockData(int handle, int i2cReg, byte[] buf, int count);

    @Override
    public native int i2cReadBlockData(int handle, int i2cReg, byte[] buf);
         
    @Override
    public native int i2cBlockProcessCall(int handle, int i2cReg, byte[] buf, int count);
         
    @Override
    public native int i2cReadI2CBlockData(int handle, int i2cReg, byte[] buf, int count);

    @Override
    public native int i2cWriteI2CBlockData(int handle, int i2cReg, byte[] buf, int count);

    @Override
    public native int i2cReadDevice(int handle, byte[] buf, int count);

    @Override
    public native int i2cWriteDevice(int handle, byte[] buf, int count);

    @Override
    public native void i2cSwitchCombined(int setting);

//    @Override
//    public native int i2cSegments(int handle, Object[] segs, int numSegs);
         
//    @Override
//    public native int i2cZip(int handle, byte[] inBuf, int inLen, byte[] outBuf, int outLen);

}
