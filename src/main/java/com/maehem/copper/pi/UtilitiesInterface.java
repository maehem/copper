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
public interface UtilitiesInterface {
    /** 
     * Get the PiGpio version.
     * 
     * @return the PiGpio version.
     */
    public int getVersion();
    
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
    public int getHardwareRevision();
    
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
    public int[] time( int type );

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
    public int sleep( int type, int seconds, int micros );

    /**
     * Delays for at least the number of microseconds specified by micros.
     * <p>
     * Note: Delays of 100 microseconds or less use busy waits.
     * </p>

     * @param micros the number of microseconds to sleep
     * @return the actual length of the delay in microseconds.
     */
    public int delay( long micros );
        
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
    public int tick();
    
}
