/**
 * Test functionality of a USB gamepad for RaspBerryPi(or other Linux)
 *
 * By Mark J Koch - 2021/10
 *
 */
package com.maehem.copper.examples.raspberrypi.spi.xra1405;

import static com.maehem.copper.pi.BasicInterface.HIGH;
import static com.maehem.copper.pi.BasicInterface.LOW;
import static com.maehem.copper.pi.BasicInterface.OUTPUT;
import com.maehem.copper.pi.NativeControllerImpl;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Xra1405Demo
 */
public class Xra1405Demo {
    public static final Logger logger = Logger.getLogger("XRA1405 Demo");

    private static final int CS_PIN = 7; // (BCM)
    private static final int SPI_BAUD = 100000;

    public static void main(String[] args) {

        NativeControllerImpl gpio = new NativeControllerImpl();
        if (gpio.initialise() < 0) {
            logger.log(Level.SEVERE, "PiGPIO setup error");
            return;
        } else {
            logger.log(Level.INFO, "Copper: PiGPIO XRA1405 Demo");
        }
        
        gpio.setMode(CS_PIN, OUTPUT);
        //gpio.write(RESET_PIN, HIGH);

        int handle = gpio.spiOpen(0, SPI_BAUD, 0);
        
        // PUR1 to pullups.
        Xra1405Device.write( gpio, handle, (byte)0xff);
        Xra1405Device.setReg(gpio, handle, Xra1405Device.PUR1, (byte) 0b00000000); // All inputs
        Xra1405Device.setReg(gpio, handle, Xra1405Device.PUR2, (byte) 0b11100000); // upper 3 are output: led1, led2 and rumble
        Xra1405Device.setReg(gpio, handle, Xra1405Device.OCR1, (byte) 0b00000000); // output pins to 0.
        
        while(true) {
            // Read Pins and print
            Byte[] vals = new Byte[1];
            int ret = Xra1405Device.getReg(gpio, handle, Xra1405Device.GSR1, vals );
            if ( ret < 0 ) {
                logger.severe("SPI read of register GSR1 failed!");
                System.exit(100);
            }
            logger.log(Level.WARNING, 
                    "XRA1405 Button Read:{0}", 
                    String.format(
                            "%8s", 
                            Integer.toBinaryString(vals[0]))
                                .replace(' ', '0'));
        }
                
    }

}
