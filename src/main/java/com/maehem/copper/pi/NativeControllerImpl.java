/*
 * Native interface for PiGPIO on Raspberry Pi
 * 
 * To generate the .c and .h files run:
 *    javac -h . NativeControllerImpl.java
 * 
 */
package com.maehem.copper.pi;

/**
 *
 * @author Mark J Koch
 */
public class NativeControllerImpl {
    
    // ESSENTIAL	
    /**
     * Initialize the library
     *
     * <p>
     * Must be called before using the other library functions with 
     * the following exceptions:<br>
     * <pre>
     *      gpioCfg*
     *      getVersion()
     *      getHardwareRevision()</pre>
     * </p>
     * <p>
     * Example:
     * <pre>{@code
     *      if (gpio.initialise() < 0 ) {
     *          // pigpio initialisation failed.
     *      } else {
     *         // pigpio initialised okay.
     *      }
     *}</pre>
     * </p>
     *
     * @return piGpio verion number if OK, otherwise PI_INIT_FAILED.
     */
    public native int initialise();
    
    /**
     * Terminates the library.
     * 
     * <p>Call before program exit.
     * This function resets the used DMA channels, releases memory, and 
     * terminates any running threads.
     * </p>
     * <p>Example:
     * <pre>{@code
     *      gpio.terminate();
     * }</pre>
     * </p>
     * 
     * @return nothing
     */
    public native int terminate();

    // BASIC	
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
    public native int setMode( int pin, int mode );
    
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
    public native int getMode( int pin );
    
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
    public native int setPullUpDown( int pin, int pud );
    
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
    public native int read( int pin );
    
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
    public native int write( int pin, int level);    
    
    // PWM (overrides servo commands on same GPIO)	
    /**
     * Starts PWM on the GPIO, dutycycle between 0 (off) and range (fully on).
     * 
     * <p>Range defaults to 255.</p>
     * <p>Arduino style: analogWrite</p>
     * 
     * <p>his and the servo functionality use the DMA and PWM or PCM peripherals 
     * to control and schedule the pulse lengths and dutycycles.</p>
     * 
     * <p>The gpioSetPWMrange function may be used to change the 
     * default range of 255.</p>
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
    public native int pwm( int pin, int duty );
    
    /**
     * Returns the PWM dutycycle setting for the GPIO.
     * <p>
     * For normal PWM the dutycycle will be out of the defined range for 
     * the GPIO (see gpioGetPWMrange).
     * </p>
     * <p>
     * If a hardware clock is active on the GPIO the reported dutycycle 
     * will be 500000 (500k) out of 1000000 (1M).
     * <p>
     * If hardware PWM is active on the GPIO the reported dutycycle will 
     * be out of a 1000000 (1M).
     * </p>
     * <p>
     * Normal PWM range defaults to 255.
     * </p>
     * @param pin 0 31
     * @return between 0 (off) and range (fully on) if OK, otherwise PI_BAD_USER_GPIO or PI_NOT_PWM_GPIO.
     */
    public native int pwmGetDutyCycle( int pin );
    
    /**
     * Sets the frequency in hertz to be used for the GPIO.
     * 
     * <p>If PWM is currently active on the GPIO it will be switched off 
     * and then back on at the new frequency.</p>
     * 
     * <p>Each GPIO can be independently set to one of 18 different PWM 
     * frequencies.</p>
     * 
     * <p>The selectable frequencies depend upon the sample rate which may 
     * be 1, 2, 4, 5, 8, or 10 microseconds (default 5).</p>
     * 
     * <p>The frequencies for each sample rate are:</p>
     * <pre>
                       Hertz
         ------------------------------------------------
       1: 40000 20000 10000 8000 5000 4000 2500 2000 1600
           1250  1000   800  500  400  250  200  100   50

       2: 20000 10000  5000 4000 2500 2000 1250 1000  800
            625   500   400  250  200  125  100   50   25

       4: 10000  5000  2500 2000 1250 1000  625  500  400
            313   250   200  125  100   63   50   25   13
sample
 rate
 (us)  5:  8000  4000  2000 1600 1000  800  500  400  320
            250   200   160  100   80   50   40   20   10

       8:  5000  2500  1250 1000  625  500  313  250  200
            156   125   100   63   50   31   25   13    6

      10:  4000  2000  1000  800  500  400  250  200  160
            125   100    80   50   40   25   20   10    5
    </pre>
     *
     * Example:
     * <pre>{@code
            gpioSetPWMfrequency(23, 0); // Set GPIO23 to lowest frequency.
            gpioSetPWMfrequency(24, 500); // Set GPIO24 to 500Hz.
            gpioSetPWMfrequency(25, 100000); // Set GPIO25 to highest frequency.
      }</pre>
     * 
     * @param pin 0-31
     * @param freq >=0
     * @return the numerically closest frequency if OK, otherwise PI_BAD_USER_GPIO.
     */
    public native int pwmSetFrequency( int pin, int freq );

    /**
     * Get the frequency (in hertz) used for the GPIO.
     * 
     * @param pin
     * @return the frequency (in hertz) used for the GPIO if OK, otherwise PI_BAD_USER_GPIO.
     */
    public native int pwmGetFrequency( int pin );
    
    /**
     * Selects the dutycycle range to be used for the GPIO. Subsequent calls 
     * to gpioPWM will use a dutycycle between 0 (off) and range (fully on).
     * 
     * <p>
     * If PWM is currently active on the GPIO its dutycycle will be scaled 
     * to reflect the new range.
     * </p>
     * 
     * <p>
     * The real range, the number of steps between fully off and fully on 
     * for each frequency, is given in the following table.
     *  <pre>
     *       25,   50,  100,  125,  200,  250,  400,   500,   625,
     *      800, 1000, 1250, 2000, 2500, 4000, 5000, 10000, 20000 </pre>
     * </p>
     * <p>The real value set by gpioPWM is (dutycycle * real range) / range.</p>
     *
     * <p>Example:</p>
     * <pre>{@code
     *      gpio.pwmSetRange(24, 2000);  // Now 2000 is fully on
     *                                   //     1000 is half on
     *                                   //      500 is quarter on, etc.
     * }</pre>
     * 
     * @param pin 0-31
     * @param range 25-40000
     * @return the real range for the given GPIO's frequency if OK, otherwise PI_BAD_USER_GPIO or PI_BAD_DUTYRANGE.
     */
    public native int pwmSetRange( int pin, int range );

    /**  
     * <p>Returns the duty cycle range used for the GPIO if OK, otherwise 
     * PI_BAD_USER_GPIO.</p>
     * 
     * <p>If a hardware clock or hardware PWM is active on the GPIO the reported 
     * range will be 1000000 (1M).</p>
     * 
     * <p>Example:<br>
     * <pre>{@code
     *      int r = gpio.pwmGetRange(23);
     * }</pre>
     * </p>
     * 
     * @param pin 0-31
     * @return the duty cycle range used for the GPIO if OK, otherwise PI_BAD_USER_GPIO.
     */
    public native int pwmGetRange( int pin );

    /** 
     * Get real range used for the GPIO 
     * 
     * <p>
     * If a hardware clock is active on the GPIO the reported real 
     * range will be 1000000 (1M).
     * </p>
     * <p>
     * If hardware PWM is active on the GPIO the reported real range 
     * will be approximately 250M divided by the set PWM frequency.
     * </p>
     * <p>Example:
     * <pre>{@code
            rr = gpio.pwmGetRealRange(17);
     * }</pre>
     * </p>
     * 
     * @param pin 0-31
     * @return the real range used for the GPIO if OK, otherwise PI_BAD_USER_GPIO.
     */
    public native int pwmGetRealRange( int pin );
    
    //SERVO      (overrides PWM commands on same GPIO)	
    
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
    public native int servo( int pin, int pulseWidth );
    
    /**
     * Returns the servo pulse width setting for the GPIO.
     * 
     * 
     * @param pin 0-31
     * @return 0 (off), 500 (most anti-clockwise) to 2500 (most clockwise) if OK, otherwise PI_BAD_USER_GPIO or PI_NOT_SERVO_GPIO.
     */
    public native int servoGetPulsewidth(int pin);

    // UTILITIES
    /** 
     * Get the pigpio version.
     * 
     * @return the pigpio version.
     */
    public native int getVersion();
    
    /**
     * Returns the hardware revision.
     * 
     * <p>If the hardware revision can not be found or is not a valid hexadecimal 
     * number the function returns 0.</p>
     * 
     * <p>The hardware revision is the last few characters on the Revision 
     * line of /proc/cpuinfo.</p>
     * 
     * <p>The revision number can be used to determine the assignment of GPIO 
     * to pins (see gpio).</p>
     * 
     * <p>There are at least three types of board.</p>
     * 
     * <ul>
     * <li>Type 1 boards have hardware revision numbers of 2 and 3.</li>
     * <li>Type 2 boards have hardware revision numbers of 4, 5, 6, and 15.</li>
     * <li>Type 3 boards have hardware revision numbers of 16 or greater.</li>
     * </ul>
     * 
     * <ul>
     * <li>for "Revision : 0002" the function returns 2.</li>
     * <li>for "Revision : 000f" the function returns 15.</li>
     * <li>for "Revision : 000g" the function returns 0.</li>
     * </ul>
     * 
     * 
     * @return the hardware revision.
     */
    public native int getHardwareRevision();
    
    /**
     * Updates the seconds and micros variables with the current time.
     * <p>
     * If timetype is PI_TIME_ABSOLUTE updates seconds and micros with the number of seconds and microseconds since the epoch (1st January 1970)
     * <br>
     * If timetype is PI_TIME_RELATIVE updates seconds and micros with the number of seconds and microseconds since the library was initialised.
     * </p>
     * <p>
     * Example:
     * The number of seconds and microseconds since the library was started
     * <pre>{@code
     *      int[] arr = gpio.time(PI_TIME_RELATIVE);
     * }</pre>
     * </p>
     * 
     * @param type 0 (relative), 1 (absolute)
     * @return int[ returnType, secs, micros ] -- returnType: 0 if OK, otherwise PI_BAD_TIMETYPE.
     */
    public native int[] time( int type );

    /**
     * Sleeps for the number of seconds and microseconds specified by 
     * seconds and micros.
     *
     * Returns 0 if OK, otherwise PI_BAD_TIMETYPE, PI_BAD_SECONDS, or PI_BAD_MICROS.
     * If timetype is PI_TIME_ABSOLUTE the sleep ends when the number of 
     * seconds and microseconds since the epoch (1st January 1970) has elapsed.
     * System clock changes are taken into account.
     * 
     * If timetype is PI_TIME_RELATIVE the sleep is for the specified number of seconds and microseconds. System clock changes do not effect the sleep length.
     *
     * For short delays (say, 50 microseonds or less) use gpioDelay.
     *
     * Example
     * gpioSleep(PI_TIME_RELATIVE, 2, 500000); // sleep for 2.5 seconds
     * gpioSleep(PI_TIME_RELATIVE, 0, 100000); // sleep for 0.1 seconds
     * gpioSleep(PI_TIME_RELATIVE, 60, 0);     // sleep for one minute
     * 
     * @param type 0 (relative), 1 (absolute)
     * @param seconds seconds to sleep
     * @param micros microseconds to sleep
     * @return 
     */
    public native int sleep( int type, int seconds, int micros );

    /**
     * Delays for at least the number of microseconds specified by micros.
     * <p>
     * Note: Delays of 100 microseconds or less use busy waits.
     * </p>

     * @param micros the number of microseconds to sleep
     * @return the actual length of the delay in microseconds.
     */
    public native int delay( long micros );
        
    /**
     * 
     * Returns the current system tick.
     * 
     * [native C] uint32_t gpioTick(void)
     * 
     * Tick is the number of microseconds since system boot.
     * As tick is an unsigned 32 bit quantity it wraps around after 2^32 
     * microseconds, which is approximately 1 hour 12 minutes. 
     * You don't need to worry about the wrap around as long as you take a 
     * tick (uint32_t) from another tick, i.e. the following code will always 
     * provide the correct difference.
     * 
     * Example:
     *  long startTick, endTick;
     *  int diffTick;
     * 
     *  startTick = gpio.tick();
     * 
     *  //do some processing
     *
     *  endTick = gpio.tick();
     *  diffTick = endTick - startTick;
     * 
     * 
     * @return current tick
     */
    public native int tick();
    
}
