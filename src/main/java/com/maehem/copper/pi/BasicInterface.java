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
package com.maehem.copper.pi;

/**
 *
 * @author Mark J. Koch (GitHub @maehem)
 */
public interface BasicInterface {

    // Pull up/down/none
    public static final int PUD_OFF = 0;
    public static final int PUD_DOWN = 1;
    public static final int PUD_UP = 2;

    /* gpio: 0-53 */
    public static final int PI_MIN_GPIO = 0;
    public static final int PI_MAX_GPIO = 53;

    /* user_gpio: 0-31 */
    public static final int PI_MAX_USER_GPIO = 31;

    /* level: 0-1 */
    public static final int PI_OFF = 0;
    public static final int PI_ON = 1;

    public static final int PI_CLEAR = 0;
    public static final int PI_SET = 1;

    public static final int PI_LOW = 0;
    public static final int PI_HIGH = 1;
    public static final int LOW = PI_LOW;
    public static final int HIGH = PI_HIGH;

    /* level: only reported for GPIO time-out, see gpioSetWatchdog */
    public static final int PI_TIMEOUT = 2;

    /* mode: 0-7 */
    public static final int PI_INPUT = 0;
    public static final int PI_OUTPUT = 1;
    public static final int INPUT = 0;
    public static final int OUTPUT = 1;
    public static final int PI_ALT0 = 4;
    public static final int PI_ALT1 = 5;
    public static final int PI_ALT2 = 6;
    public static final int PI_ALT3 = 7;
    public static final int PI_ALT4 = 3;
    public static final int PI_ALT5 = 2;

    /* pud: 0-2 */
    public static final int PI_PUD_OFF = 0;
    public static final int PI_PUD_DOWN = 1;
    public static final int PI_PUD_UP = 2;

    /**
     * Set a GPIO mode
     * 
     * <p>
     * Sets the GPIO mode, typically input or output.
     * </p>
     * <p>
     * Arduino style: pinMode.
     * </p>
     * <p>
     * Examples:
     * <pre>{@code
     *      gpio.setMode(17, PI_INPUT);  // Set GPIO17 as input.
     *      gpio.setMode(18, PI_OUTPUT); // Set GPIO18 as output.
     *      gpio.setMode(22,PI_ALT0);    // Set GPIO22 to alternative mode 0.
     * }</pre>
     * </p>
     * 
     * @param pin  0-53
     * @param mode 0-7
     * @return 0 if OK, otherwise PI_BAD_GPIO or PI_BAD_MODE.
     */
    public int setMode( int pin, int mode );
    
    /**
     * Gets the GPIO mode.
     * 
     * <p>
     * Example:
     * <pre>{@code
     *     if (gpio.getMode(17) != PI_ALT0) {
     *        gpio.setMode(17, PI_ALT0);  // set GPIO17 to ALT0
     *     }
     * }</pre>
     * </p>
     * @param pin 0-53
     * @return the GPIO mode if OK, otherwise PI_BAD_GPIO.
     */
    public int getMode( int pin );
    
    /**
     * Set/clear GPIO pull up/down resistor on the GPIO
     * 
     * <p>
     * Example:
     * <pre>{@code
     *    gpio.setPullUpDown(17, PI_PUD_UP);   // Sets a pull-up.
     *    gpio.setPullUpDown(18, PI_PUD_DOWN); // Sets a pull-down.
     *    gpio.setPullUpDown(23, PI_PUD_OFF);  // Clear any pull-ups/downs.
     * }</pre>
     * </p>
     * 
     * @param pin 0-53
     * @param pud 0-2
     * @return 0 if OK, otherwise PI_BAD_GPIO or PI_BAD_PUD.
     */
    public int setPullUpDown( int pin, int pud );
    
    /**
     * Reads the GPIO level, on or off.
     * 
     * <p>
     * Arduino style: digitalRead.
     * </p>
     * <p>
     * Example:
     * <pre>{@code
     *     String str = "GPIO24 is level " + gpio.read(24);
     * }</pre>
     * </p>
     * 
     * @param pin 0-53
     * @return the GPIO level if OK, otherwise PI_BAD_GPIO.
     */
    public int read( int pin );
    
    /**
     * Sets the GPIO level, on or off.
     * <p>
     * If PWM or servo pulses are active on the GPIO they are switched off.
     * </p>
     * <p>
     * Example:
     * <pre>{@code
     *      gpio.write(24, 1); // Set GPIO24 high.
     * }</pre>
     * </p>
     * @param pin 0-53
     * @param level 0-1
     * @return 0 if OK, otherwise PI_BAD_GPIO or PI_BAD_LEVEL.
     */
    public int write( int pin, int level);    
    
}
