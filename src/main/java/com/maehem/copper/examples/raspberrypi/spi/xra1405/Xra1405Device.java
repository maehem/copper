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
package com.maehem.copper.examples.raspberrypi.spi.xra1405;

import com.maehem.copper.pi.NativeControllerImpl;


/**
 * Implementation of the Exar XRA1405 GPIO Expander using SPI with Copper
 */
public class Xra1405Device {
    /**
     * Default SPI channel
     */
    public static final int DEFAULT_SPI_CHANNEL = 0;
    
    /**
     * Default SPI baud rate
     */
    public static final int DEFAULT_SPI_BAUD_RATE = 1000000;

    // Byte is pre-shifted up one bit as they are never used any other way.
    public static final byte GSR1  = (byte)0x00<<1; // GPIO State for P0-P7
    public static final byte GSR2  = (byte)0x01<<1; // GPIO State for P8-P15
    public static final byte OCR1  = (byte)0x02<<1; // Output Control for P0-7
    public static final byte OCR2  = (byte)0x03<<1; // Output Control for P8-15
    public static final byte PIR1  = (byte)0x04<<1; // Input Polarity Inversion for P0-P7
    public static final byte PIR2  = (byte)0x05<<1; // Input Polarity Inversion for P8-P15
    public static final byte GCR1  = (byte)0x06<<1; // GPIO Configuration for P0-P7
    public static final byte GCR2  = (byte)0x07<<1; // GPIO Configuration for P8-P15
    public static final byte PUR1  = (byte)0x08<<1; // Input Internal Pull-up Resistor Enable/Disable for P0-P7
    public static final byte PUR2  = (byte)0x09<<1; // Input Internal Pull-up Resistor Enable/Disable for P8-P15
    public static final byte IER1  = (byte)0x0A<<1; // Input Interrupt Enable for P0-P7
    public static final byte IER2  = (byte)0x0B<<1; // Input Interrupt Enable for P8-P15
    public static final byte TSCR1 = (byte)0x0C<<1; // Output Three-State Control for P0-P7
    public static final byte TSCR2 = (byte)0x0D<<1; // Output Three-State Control for P8-P15
    public static final byte ISR1  = (byte)0x0E<<1; // Input Interrupt Status for P0-P7
    public static final byte ISR2  = (byte)0x0F<<1; // Input Interrupt Status for P8-P15
    public static final byte REIR1 = (byte)0x10<<1; // Input Rising Edge Interrupt Enable for P0-P7
    public static final byte REIR2 = (byte)0x11<<1; // Input Rising Edge Interrupt Enable for P8-P15
    public static final byte FEIR1 = (byte)0x12<<1; // Input Falling Edge Interrupt Enable for P0-P7
    public static final byte FEIR2 = (byte)0x13<<1; // Input Falling Edge Interrupt Enable for P8-P15
    public static final byte IFR1  = (byte)0x14<<1; // Input Filter Enable/Disable for P0-P7
    public static final byte IFR2  = (byte)0x15<<1; // Input Filter Enable/Disable for P8-P15

    
    public static int setReg( NativeControllerImpl gpio, int handle, byte reg, byte val ) {
        byte[] packet = { reg, val };
        return write ( gpio, handle, packet );
    }
    
    // TODO: change to 'throws' so that we can return byte as value.
    public static int getReg( NativeControllerImpl gpio, int handle, byte reg, Byte[] val ) {
        byte[] packet = { (byte)(0x80 | reg) }; // Add read flag
        int ret = write ( gpio, handle, packet );
        if ( ret < 0 ) {
            return ret;
        }
        byte rVal[] = new byte[1];
        ret =  gpio.spiRead(handle, rVal, 1);
        val[0] = rVal[0];
        return ret;
    }
    
    public static int write( NativeControllerImpl gpio, int handle, byte[] val ) {
       return gpio.spiWrite(handle, val, val.length);
    }
    
    public static int write( NativeControllerImpl gpio, int handle, byte val ) {
       return write(gpio,handle, new byte[]{val});
    }
    
}