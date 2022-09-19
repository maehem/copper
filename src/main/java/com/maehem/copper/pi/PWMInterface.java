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
public interface PWMInterface {

    /**
     * Starts PWM on the GPIO, dutycycle between 0 (off) and range (fully on).
     *
     * <p>
     * Range defaults to 255.</p>
     * <p>
     * Arduino style: analogWrite</p>
     *
     * <p>
     * his and the servo functionality use the DMA and PWM or PCM peripherals to
     * control and schedule the pulse lengths and dutycycles.</p>
     *
     * <p>
     * The gpioSetPWMrange function may be used to change the default range of
     * 255.</p>
     *
     * Example:
     * <pre>{@code
     *      gpio.pwm(17, 255); // Sets GPIO17 full on.
     *      gpio.pwm(18, 128); // Sets GPIO18 half on.
     *      gpio.pwm(23, 0);   // Sets GPIO23 full off.
     * }</pre>
     *
     * @param pin 0-31
     * @param duty 0-range
     * @return 0 if OK, otherwise PI_BAD_USER_GPIO or PI_BAD_DUTYCYCLE.
     */
    public int pwm(int pin, int duty);

    /**
     * Returns the PWM dutycycle setting for the GPIO.
     * <p>
     * For normal PWM the dutycycle will be out of the defined range for the
     * GPIO (see gpioGetPWMrange).
     * </p>
     * <p>
     * If a hardware clock is active on the GPIO the reported dutycycle will be
     * 500000 (500k) out of 1000000 (1M).
     * <p>
     * If hardware PWM is active on the GPIO the reported dutycycle will be out
     * of a 1000000 (1M).
     * </p>
     * <p>
     * Normal PWM range defaults to 255.
     * </p>
     *
     * @param pin 0 31
     * @return between 0 (off) and range (fully on) if OK, otherwise
     * PI_BAD_USER_GPIO or PI_NOT_PWM_GPIO.
     */
    public int pwmGetDutyCycle(int pin);

    /**
     * Sets the frequency in hertz to be used for the GPIO.
     *
     * <p>
     * If PWM is currently active on the GPIO it will be switched off and then
     * back on at the new frequency.</p>
     *
     * <p>
     * Each GPIO can be independently set to one of 18 different PWM
     * frequencies.</p>
     *
     * <p>
     * The selectable frequencies depend upon the sample rate which may be 1, 2,
     * 4, 5, 8, or 10 microseconds (default 5).</p>
     *
     * <p>
     * The frequencies for each sample rate are:</p>
     * <pre>
     * Hertz
     * ------------------------------------------------
     * 1: 40000 20000 10000 8000 5000 4000 2500 2000 1600
     * 1250  1000   800  500  400  250  200  100   50
     *
     * 2: 20000 10000  5000 4000 2500 2000 1250 1000  800
     * 625   500   400  250  200  125  100   50   25
     *
     * 4: 10000  5000  2500 2000 1250 1000  625  500  400
     * 313   250   200  125  100   63   50   25   13
     * sample
     * rate
     * (us)  5:  8000  4000  2000 1600 1000  800  500  400  320
     * 250   200   160  100   80   50   40   20   10
     *
     * 8:  5000  2500  1250 1000  625  500  313  250  200
     * 156   125   100   63   50   31   25   13    6
     *
     * 10:  4000  2000  1000  800  500  400  250  200  160
     * 125   100    80   50   40   25   20   10    5
     * </pre>
     *
     * Example:
     * <pre>{@code
     * gpioSetPWMfrequency(23, 0); // Set GPIO23 to lowest frequency.
     * gpioSetPWMfrequency(24, 500); // Set GPIO24 to 500Hz.
     * gpioSetPWMfrequency(25, 100000); // Set GPIO25 to highest frequency.
     * }</pre>
     *
     * @param pin 0-31
     * @param freq >=0
     * @return the numerically closest frequency if OK, otherwise
     * PI_BAD_USER_GPIO.
     */
    public int pwmSetFrequency(int pin, int freq);

    /**
     * Get the frequency (in hertz) used for the GPIO.
     *
     * @param pin
     * @return the frequency (in hertz) used for the GPIO if OK, otherwise
     * PI_BAD_USER_GPIO.
     */
    public int pwmGetFrequency(int pin);

    /**
     * Selects the dutycycle range to be used for the GPIO. Subsequent calls to
     * gpioPWM will use a dutycycle between 0 (off) and range (fully on).
     *
     * <p>
     * If PWM is currently active on the GPIO its dutycycle will be scaled to
     * reflect the new range.
     * </p>
     *
     * <p>
     * The real range, the number of steps between fully off and fully on for
     * each frequency, is given in the following table.
     * <pre>
     *       25,   50,  100,  125,  200,  250,  400,   500,   625,
     *      800, 1000, 1250, 2000, 2500, 4000, 5000, 10000, 20000 </pre>
     * </p>
     * <p>
     * The real value set by gpioPWM is (dutycycle * real range) / range.</p>
     *
     * <p>
     * Example:</p>
     * <pre>{@code
     *      gpio.pwmSetRange(24, 2000);  // Now 2000 is fully on
     *                                   //     1000 is half on
     *                                   //      500 is quarter on, etc.
     * }</pre>
     *
     * @param pin 0-31
     * @param range 25-40000
     * @return the real range for the given GPIO's frequency if OK, otherwise
     * PI_BAD_USER_GPIO or PI_BAD_DUTYRANGE.
     */
    public int pwmSetRange(int pin, int range);

    /**
     * <p>
     * Returns the duty cycle range used for the GPIO if OK, otherwise
     * PI_BAD_USER_GPIO.</p>
     *
     * <p>
     * If a hardware clock or hardware PWM is active on the GPIO the reported
     * range will be 1000000 (1M).</p>
     *
     * <p>
     * Example:<br>
     * <pre>{@code
     *      int r = gpio.pwmGetRange(23);
     * }</pre>
     * </p>
     *
     * @param pin 0-31
     * @return the duty cycle range used for the GPIO if OK, otherwise
     * PI_BAD_USER_GPIO.
     */
    public int pwmGetRange(int pin);

    /**
     * Get real range used for the GPIO
     *
     * <p>
     * If a hardware clock is active on the GPIO the reported real range will be
     * 1000000 (1M).
     * </p>
     * <p>
     * If hardware PWM is active on the GPIO the reported real range will be
     * approximately 250M divided by the set PWM frequency.
     * </p>
     * <p>
     * Example:
     * <pre>{@code
     * rr = gpio.pwmGetRealRange(17);
     * }</pre>
     * </p>
     *
     * @param pin 0-31
     * @return the real range used for the GPIO if OK, otherwise
     * PI_BAD_USER_GPIO.
     */
    public int pwmGetRealRange(int pin);

}
