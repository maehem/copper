# Copper: a JNI Library for *PiGPIO*
Java native implementation for Raspberry Pi extension GPIO control (GPIO, SPI, I2C, PWM...), based on PiGPIO library.

## Description
The *Copper* project is dedicated to creating an convenient and easy-to-use Java class library for Raspberry Pi extension GPIO control (GPIO, SPI, I2C, PWM...). This project provides a wrapper for PiGPIO library. In most cases, you can use it just like PiGPIO library on Raspberry Pi. PiGPIO library is preloaded in the latest Raspbian operating system, and works without any additional tuning.

## How to use
You can use this Java package in Raspbian operating system without complex settings. Just follow these 2 steps:
1.  On your Raspberry Pi, compile *Copper* from the top folder (you may need to review pom.xml and update Java version)
```
    mvn package
```
A Jar file will have been generated. It might also have been copied to your
Maven repository cache (in your home directory under .m2). The Jar file
contains the Java library as well as some demo examples.

2.  Compile the libcopper.so and copy it to your /usr/lib directory:
```
    cd src/java/native
    ./compile.sh
    sudo cp libcopper.so /usr/lib
```
3.  Run a demo. Invoke Java on your Maven compiled project jar:<br>
    Do this from the top level of the git project.
```
     sudo java -cp target/copper-0.1-SNAPSHOT.jar com.maehem.copper.examples.raspberrypi.spi.xra1405.Xra1405Demo
```
*   Note: If you don't want to copy the library to the /usr/lib, you can specify the library path before running your Java program, like:
```
         sudo java -Djava.library.path=. -cp target/copper-0.1-SNAPSHOT.jar com.maehem.copper.examples.raspberrypi.spi.xra1405.Xra1405Demo
```
(this specifies the . directory as the native library path, so the libcopper.so should be found there.)

## Methods Copper provides
You can invoke these methods by instantiating *com.maehem.copper.pi.NativeControllerImpl*

### Implements EssentialInterface
```java
    int initialise();
    int terminate();
```

### Implements BasicInterface
```java
    int setMode(int pin, int mode);
    int getMode(int pin);
    int setPullUpDown(int pin, int pud);
    int read(int pin);
    int write(int pin, int level);
```

### Implements PWMInterface
```java
    int pwm(int pin, int duty);
    int pwmGetDutyCycle(int pin);
    int pwmSetFrequency(int pin, int freq);
    int pwmGetFrequency(int pin);
    int pwmSetRange(int pin, int range);
    int pwmGetRange(int pin);
    int pwmGetRealRange(int pin);
```
### Implements ServoInterface
```java
    int servo(int pin, int pulseWidth);
    int servoGetPulsewidth(int pin);
```

### Implements UtilitiesInterface
```java
    int getVersion();
    int getHardwareRevision();
    int[] time(int type);
    int sleep(int type, int seconds, int micros);
    int delay(long micros);
    int tick();
```
### Implements SPIInterface
```java
    int spiOpen(int spiChan, int baud, int spiFlags);
    int spiClose(int handle);
    int spiRead(int handle, byte[] buf, int count);
    int spiWrite(int handle, byte[] buf, int count);
    int spiXfer(int handle, byte[] txBuf, byte[] rxBuf, int count);
```

### Implements I2CInterface
```java
    int i2cOpen(int i2cBus, int i2cAddr, int i2cFlags);
    int i2cClose(int handle);
    int i2cWriteQuick(int handle, int bit);
    int i2cWriteByte(int handle, int bVal);
    int i2cReadByte(int handle);
    int i2cWriteByteData(int handle, int i2cReg, int bVal);
    int i2cWriteWordData(int handle, int i2cReg, int wVal);
    int i2cReadByteData(int handle, int i2cReg);
    int i2cReadWordData(int handle, int i2cReg);
    int i2cProcessCall(int handle, int i2cReg, int wVal);
    int i2cWriteBlockData(int handle, int i2cReg, byte[] buf, int count);
    int i2cReadBlockData(int handle, int i2cReg, byte[] buf);
    int i2cBlockProcessCall(int handle, int i2cReg, byte[] buf, int count);
    int i2cReadI2CBlockData(int handle, int i2cReg, byte[] buf, int count);
    int i2cWriteI2CBlockData(int handle, int i2cReg, byte[] buf, int count);
    int i2cReadDevice(int handle, byte[] buf, int count);
    int i2cWriteDevice(int handle, byte[] buf, int count);
    void i2cSwitchCombined(int setting);
```

## Getting started
A demo for getting started.
1.  copy the file libcopper.so to /usr/lib <br />
```sudo cp libcopper.so /usr/lib```
2.  save the following as RPiGPIODemo.java:

```java
import com.maehem.copper.pi.*;

public class RPiGPIODemo {
    public static final int HIGH = 1;
    public static final int LOW = 0;
    public static final int OUTPUT = 1;

    public static void main(String[] args) {
        NativeControllerImpl gpio = new NativeControllerImpl();
        if (gpio.initialise() < 0) {
            System.out.println("PiGPIO/Copper setup error");
            return;
        }
        gpio.setMode(25, OUTPUT);
        while(true) {
            gpio.write(25, HIGH);
            gpio.delay(1000000);
            gpio.write(25, LOW);
            gpio.delay(1000000);
        }
    }
}
```
## TODO Update for using Maven i.e. Include copper from mvn repo.
3.  copy the directory pigpio to the directory of RPiGPIODemo.java
4.  compile the program with
        `javac RPiGPIODemo.java`
        future: mvn package
5.  run the program
        `java RPiGPIODemo`
        sudo java -cp target/copper-0.1-SNAPSHOT.jar RPiGPIODemo
6.  expected result: the voltage level of GPIO.29 pin will be toggled continuously.

## Pin mapping of Raspberry Pi
```
 +-----+---------+------+----Pi 3/4/Z/Z2---+------+---------+-----+
 | BCM |   Name  | Mode | V | Physical | V | Mode | Name    | BCM |
 +-----+---------+------+---+----++----+---+------+---------+-----+
 |     |    3.3v |      |   |  1 || 2  |   |      | 5v      |     |
 |   2 |   SDA.1 | ALT0 | 1 |  3 || 4  |   |      | 5v      |     |
 |   3 |   SCL.1 | ALT0 | 1 |  5 || 6  |   |      | 0v      |     |
 |   4 | GPIO. 7 |   IN | 1 |  7 || 8  | 1 | ALT5 | TxD     | 14  |
 |     |     GND |      |   |  9 || 10 | 1 | ALT5 | RxD     | 15  |
 |  17 | GPIO.17 |   IN | 0 | 11 || 12 | 0 | IN   | GPIO.18 | 18  |
 |  27 | GPIO.27 |   IN | 0 | 13 || 14 |   |      | 0v      |     |
 |  22 | GPIO.22 |   IN | 0 | 15 || 16 | 0 | IN   | GPIO.23 | 23  |
 |     |    3.3v |      |   | 17 || 18 | 0 | IN   | GPIO.24 | 24  |
 |  10 |SPI0.DI  | ALT0 | 0 | 19 || 20 |   |      | 0v      |     |
 |   9 |SPI0.DO  | ALT0 | 0 | 21 || 22 | 0 | IN   | GPIO.25 | 25  |
 |  11 |SPI0.CLK | ALT0 | 0 | 23 || 24 | 1 | OUT  | SPI0.CE0| 8   |
 |     |     GND |      |   | 25 || 26 | 1 | OUT  | SPI0.CE1| 7   |
 |   0 |   SDA.0 |   IN | 1 | 27 || 28 | 1 | IN   | SCL.0   | 1   |
 |   5 | GPIO.5  |   IN | 1 | 29 || 30 |   |      | 0v      |     |
 |   6 | GPIO.6  |   IN | 1 | 31 || 32 | 0 | IN   | GPIO.12 | 12  |
 |  13 | GPIO.13 |   IN | 0 | 33 || 34 |   |      | 0v      |     |
 |  19 | GPIO.19 |  OUT | 1 | 35 || 36 | 1 | OUT  | GPIO.16 | 16  |
 |  26 | GPIO.26 |   IN | 0 | 37 || 38 | 0 | IN   | GPIO.20 | 20  |
 |     |     GND |      |   | 39 || 40 | 0 | IN   | GPIO.21 | 21  |
 +-----+---------+------+---+----++----+---+------+---------+-----+
 | BCM |   Name  | Mode | V | Physical | V | Mode | Name    | BCM |
 +-----+---------+------+---+----------+---+------+---------+-----+
```
### NOTE: This project started as a fork of jWiringPi but soon realized that
PiGPIO is now the official way to interact with GPIO on a Raspberry Pi.  The
author of Copper liked the format for the GIT project and decided to use it
as the basis for the Copper project.