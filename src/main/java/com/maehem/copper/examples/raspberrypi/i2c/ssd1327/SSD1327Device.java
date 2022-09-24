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

/**
 *
 * @author mark
 */
public class SSD1327Device {

    public static final byte SET_COL_ADDR            = (byte)0x15;
    public static final byte SET_ROW_ADDR            = (byte)0x75;
    
    public static final byte SET_CONTRAST_CURRENT    = (byte)0x81;

    public static final byte SET_REMAP               = (byte)0xA0;
    public static final byte SET_DISPLAY_START_LINE  = (byte)0xA1;
    public static final byte SET_DISPLAY_OFFSET      = (byte)0xA2;

    public static final byte NORMAL_DISPLAY          = (byte)0xA4;
    public static final byte SET_ENTIRE_DISP_ON      = (byte)0xA5;
    public static final byte SET_ENTIRE_DISP_OFF     = (byte)0xA6;
    public static final byte INVERSE_DISPLAY         = (byte)0xA7;

    public static final byte SET_MUX_RATIO           = (byte)0xA8;
    public static final byte FUNC_SELECT_A           = (byte)0xAB;
    
    public static final byte SET_DISPLAY_OFF         = (byte)0xAE;
    public static final byte SET_DISPLAY_ON          = (byte)0xAF;
    
    public static final byte SET_PHASE_LEN           = (byte)0xB1;
    public static final byte SET_FRONT_CLOCK_DIV     = (byte)0xB3;
    
    public static final byte SET_GPIO                = (byte)0xB5;
    public static final byte SET_SECOND_PRECHARGE_PD = (byte)0xB6;
    public static final byte SET_GREY_SCALE_TBL      = (byte)0xB8;
    public static final byte SET_DEFAULT_LIN_GREY    = (byte)0xB9;
    public static final byte SET_PRE_CHG_VOLTAGE     = (byte)0xBC;
    public static final byte SET_VCOMH_VOLTAGE       = (byte)0xBE;
    public static final byte FUNC_SELECT_B           = (byte)0xD5;
    public static final byte SET_CMD_LOCK            = (byte)0xFD;
    
    // Graphic Accelleration Commands
    public static final byte HORIZ_SCROLL_SETUP_A    = (byte)0x26;
    public static final byte HORIZ_SCROLL_SETUP_B    = (byte)0x27;
    public static final byte DEACTIVATE_SCROLL       = (byte)0x2E;
    public static final byte ACTIVATE_SCROLL         = (byte)0x2F;

    /**
     * Packet of commands to init OLED
     */
    public final static byte[] INIT_CMDS = {
        SET_DISPLAY_OFF, 
        SET_CONTRAST_CURRENT, (byte)0x80,     // set contrast to middle
        SET_REMAP, 0b0101001,           // 0x51,  COM split odd-even, COM remap, col addr remap
        SET_DISPLAY_START_LINE, 0x00,
        SET_DISPLAY_OFFSET, 0x00,
        SET_ENTIRE_DISP_OFF,
        SET_MUX_RATIO, 0x7F,            // 128 MUX (RESET)
        SET_PHASE_LEN, 0b00010001,      // Ph2= 1 DCLK, Ph1= 1 DCLK 
            
        SET_FRONT_CLOCK_DIV, 0x00,      // DCLK  100Hz
        FUNC_SELECT_A, 0x01,            // Use internal regulator
        SET_SECOND_PRECHARGE_PD, 0x04,  // 4 DCLKs
        SET_VCOMH_VOLTAGE, 0x0F,        // COM deslect Volts, 0.86xVcc
        SET_PRE_CHG_VOLTAGE, 0x08,      // Pre-charge Volt level, 0.613xVcc
        FUNC_SELECT_B, 0x62,            // Enable 2nd pre-charge, Internal VSL(reset)
        SET_CMD_LOCK, 0x12 ,            // Unlock OLED driver (reset)
        NORMAL_DISPLAY,
        SET_DISPLAY_ON
    };

}
