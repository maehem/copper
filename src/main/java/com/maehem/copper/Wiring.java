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
package com.maehem.copper;

import com.maehem.copper.exceptions.WiringSetupException;
import com.maehem.copper.pi.PiGPIOInterface;
import com.maehem.copper.pi.NativeControllerImpl;
import com.maehem.copper.pi.ProxyController;

/**
 *
 * @author Mark J. Koch (GitHub @maehem)
 */
public class Wiring {

    protected PiGPIOInterface piGPIO;
    //private String proxy = null; // native

    public enum PinStyle {
        WIRING, BCOM, PHYS, SYS
    }

    public enum Mode {
        NATIVE, PROXY, EMULATED
    }

    /**
     * New wiringPi instance.
     * Looks for proxy configuration file otherwise assumes local native GPIO.
     * 
     * TODO:
     *    - file:   ~/.copper/config
     *    - java:   -Dcopper.proxy=<URL>
     *    - java:   Sytem.setProperty("copper.proxy=<URL>")
     * 
     * @throws WiringSetupException
     */
    public Wiring() throws WiringSetupException {
        // Attempt to load ~/.copper/<ClassName>/config as Properties
        String proxy = System.getProperty("copper.proxy");
        if ( proxy != null) {
            piGPIO = new ProxyController(proxy);
            // A running proxy would already have been setup, so here we
            // would get the gpio pin mapping and be able to report them.
            // Or we have a class that translates pin mapping for us.
        } else {
            piGPIO = new NativeControllerImpl();
            if ( piGPIO.initialise() < 0 ) {
                throw new WiringSetupException();
            }
        }
    }

    /**
     * Proxy only
     * 
     * @param proxy as hostname:port or ipAddress:port 
     */
    public Wiring( String proxy ) {
        piGPIO = new ProxyController(proxy);
    }
    


    public PiGPIOInterface getController() {
        return piGPIO;
    }
}
