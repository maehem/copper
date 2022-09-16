/**
  * @filename       : Wiring.java
  * @project        : Copper (evolved from JWiringPi)
  * @date           : September 18 2022
  * @description: 
  *      Java wrapper of Arduino like Wiring library for the Raspberry Pi.
  *      The implements are based on WiringPi library.
  *      WiringPi Library Copyright (c) 2012-2017 Gordon Henderson
  *      JWiringPi project Copyright (c) 2017 soonuse from GitHub
  ***********************************************************************
  *
  * The purpose of Copper project is to create a convenient IO control 
  * interface (containing the implements of class) for Raspberry Pi Java 
  * programming. Copper also enables remote development and operation
  * of GPIO related RaspberryPi operations.
  *
  * Copper is free software: you can redistribute it and/or modify
  * it under the terms of the General Public License (GPL). 
  *
  * Copper is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public License
  * along with Copper.  If not, see <http://www.gnu.org/licenses/>.
  ***********************************************************************
 */
package com.maehem.copper;

import com.maehem.copper.exceptions.WiringSetupException;
import com.maehem.copper.pi.Controller;
import com.maehem.copper.pi.NativeController;
import com.maehem.copper.pi.ProxyController;

/**
 *
 * @author mark
 */
public class Wiring {

    protected Controller controller;
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
            controller = new ProxyController(proxy);
            // A running proxy would already have been setup, so here we
            // would get the gpio pin mapping and be able to report them.
            // Or we have a class that translates pin mapping for us.
        } else {
            controller = new NativeController();
            if ( setup( PinStyle.WIRING ) < 0 ) {
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
        controller = new ProxyController(proxy);
    }
    
    /**
     *
     * @param mode
     * @throws WiringSetupException
     */
    public Wiring(Mode mode) throws WiringSetupException {
        switch (mode) {
            case PROXY:
                // Attach to URL:PORT
                break;
            default:
            case NATIVE:
                if ( setup( PinStyle.WIRING ) < 0 ) {
                    throw new WiringSetupException();
                }
                break;
        }
    }

    public final int setup(PinStyle style) {
        int retVal;
        switch (style) {
            case BCOM:
                retVal = controller.wiringPiSetupGpio();
                break;
            case PHYS:
                retVal = controller.wiringPiSetupPhys();
                break;
            case SYS:
                retVal = controller.wiringPiSetupSys();
                break;
            default:
            case WIRING:
                retVal = controller.wiringPiSetup();
                break;
        }
        return retVal;
    }

    public Controller getController() {
        return controller;
    }
}
