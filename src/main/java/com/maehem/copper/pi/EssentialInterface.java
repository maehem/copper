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
 * Essential calls for PiGPIO
 * 
 * @author Mark J. Koch (GitHub @maehem)
 */
public interface EssentialInterface {
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
     * @return piGpio version number if OK, otherwise PI_INIT_FAILED.
     */
    public int initialise();
    
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
    public int terminate();
    
}
