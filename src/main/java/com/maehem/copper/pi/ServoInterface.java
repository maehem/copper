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
public interface ServoInterface {
    /**
     * Starts servo pulses on the GPIO, 0 (off), 500 (most anti-clockwise) 
     * to 2500 (most clockwise).
     * 
     * <p>
     * The range supported by servos varies and should probably be determined 
     * by experiment. A value of 1500 should always be safe and represents the 
     * mid-point of rotation. You can DAMAGE a servo if you command it to move
     * beyond its limits.
     * </p>
     * 
     * <p>
     * The following causes an on pulse of 1500 microseconds duration to 
     * be transmitted on GPIO 17 at a rate of 50 times per second. This will 
     * command a servo connected to GPIO 17 to rotate to its mid-point.
     * </p>
     * 
     * <p>Example:
     * <pre>{@code
     *   gpio.servo(17, 1000); // Move servo to safe position anti-clockwise.
     *   gpio.servo(23, 1500); // Move servo to centre position.
     *   gpio.servo(25, 2000); // Move servo to safe position clockwise.
     *   }</pre></p>
     *
     * <p>OTHER UPDATE RATES:<br>
     * This function updates servos at 50Hz. If you wish to use a different update frequency you will have to use the PWM functions.
     * <pre>
     *     PWM Hz     50   100  200  400  500
     *     1E6/Hz  20000 10000 5000 2500 2000
     * </pre>
     * </p>
     * <p>
     * Firstly set the desired PWM frequency using gpioSetPWMfrequency.
     * </p>
     * <p>
     * Then set the PWM range using gpioSetPWMrange to 1E6/frequency. Doing this allows you to use units of microseconds when setting the servo pulsewidth.
     * </p>
     * 
     * <p>
     * E.g. If you want to update a servo connected to GPIO25 at 400Hz
     * <pre>{@code
     *      gpio.pwmSetFrequency(25, 400);
     *      gpio.pwmSetRange(25, 2500);
     * }</pre>
     * </p>
     * 
     * <p>
     * Thereafter use the PWM command to move the servo, e.g. gpioPWM(25, 1500) will set a 1500 us pulse.
     * </p>
     * 
     * @param pin 0-31
     * @param pulseWidth 0, 500-2500
     * @return 0 if OK, otherwise PI_BAD_USER_GPIO or PI_BAD_PULSEWIDTH.

     */
    public int servo( int pin, int pulseWidth );
    
    /**
     * Returns the servo pulse width setting for the GPIO.
     * 
     * 
     * @param pin 0-31
     * @return 0 (off), 500 (most anti-clockwise) to 2500 (most clockwise) if OK, otherwise PI_BAD_USER_GPIO or PI_NOT_SERVO_GPIO.
     */
    public int servoGetPulsewidth(int pin);
    
}
