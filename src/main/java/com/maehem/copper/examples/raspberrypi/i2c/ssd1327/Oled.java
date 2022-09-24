/*
 * This is free and unencumbered software released into the public domain.
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * For more information, please refer to <http://unlicense.org/>
 */
package com.maehem.copper.examples.raspberrypi.i2c.ssd1327;

import static com.maehem.copper.pi.BasicInterface.*;
import com.maehem.copper.pi.NativeControllerImpl;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * OLED SSD1327 (128x128) Demo
 *
 * @author Mark J. Koch (GutHub @maehem)
 */
public class Oled {

    public static final Logger logger = Logger.getLogger("Oled");

    private static final int RESET_PIN = 5;  // BCM Pin

    private static final int CMD = 0x00;
    private static final int DATA = 0x40;
    
    private static final int ADDR = 0x3D;
    
    private static final int OLED_WIDTH = 128;
    private static final int OLED_HEIGHT = 128;
    
    // A pixel byte holds two pixels, each nibble a 4-bit greyscale value.
    //  byte[0:3] is even pixel, byte[4:7] is odd pixel.
    public static byte pixels[];// = new byte[OLED_WIDTH/2 * OLED_HEIGHT];

    public static void main(String[] args) {
        // TODO: Option for reset on GPIO pin
        pixels = new byte[OLED_WIDTH/2 * OLED_HEIGHT];


        NativeControllerImpl gpio = new NativeControllerImpl();
        if (gpio.initialise() < 0) {
            logger.log(Level.SEVERE, "PiGPIO setup error");
            return;
        } else {
            logger.log(Level.INFO, "Copper: PiGPIO SSD1327 Oled Demo");
        }
        
        gpio.setMode(RESET_PIN, OUTPUT);
        gpio.write(RESET_PIN, HIGH);
        gpio.delay(10000);
        gpio.write(RESET_PIN, LOW);
        gpio.delay(10000);
        gpio.write(RESET_PIN, HIGH);

        int handle = gpio.i2cOpen(0, ADDR, 0);
        // Send the init packet to the OLED.
        write( CMD, gpio, handle, SSD1327Device.INIT_CMDS );
                
        // delay about 100mS
        gpio.delay(100000);
        
        //gpio.i2cWriteByte(handle, SSD1327Device.SET_DISPLAY_ON );
        write(CMD, gpio, handle, SSD1327Device.SET_DISPLAY_ON);
        
        // Set the contrast.
        gpio.i2cWriteDevice(handle, new byte[]{ SSD1327Device.SET_CONTRAST_CURRENT, 0x30 }, 2);

        // Device is set up.  Let's write some pixels.
        fillCheckerboard(pixels);
        
        write( DATA, gpio, handle, pixels);
    }

    
    private static int write( int type, NativeControllerImpl dev, int handle, byte[]b ) {
        // First Byte is 0x00
        int ret = 0;
        for ( int i=0; i<b.length; i++ ) {
            ret = dev.i2cWriteByteData(handle, type, b[i]);
            if ( ret != 0 ) return ret;
        }
        return ret;
    }
    
    private static int write( int type, NativeControllerImpl dev, int handle, byte b ) {
        return write( type, dev, handle, new byte[]{b});
    }
        
    private static void fillCheckerboard( byte[]pixels ) {
        byte val = (byte)0xff;
        for (int y=0; y<OLED_HEIGHT; y+=8) {
            // repeat the row n times.
            for ( int i=0; i<8; i++ ) {
                for ( int x=0; x < OLED_WIDTH-8; x+=8 ) {
                    for ( int j=0; j<8; j++ ) {
                     pixels[y*OLED_WIDTH+i*OLED_WIDTH+x+j] = val;
                    }
                    if ( val == 0xff ) {
                        val = 0;
                    } else {
                        val = (byte)0xff;
                    }
                }
                if ( val == 0xff ) {
                    val = 0;
                } else {
                    val = (byte)0xff;
                }
            }
            if ( val == 0xff ) {
                val = 0;
            } else {
                val = (byte)0xff;
            }
        } 
    }
}
