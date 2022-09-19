package com.maehem.copper.examples.raspberrypi.gpio;


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
import static com.maehem.copper.pi.BasicInterface.*;
import com.maehem.copper.pi.NativeControllerImpl;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GPIODemo {
    public static final Logger logger = Logger.getLogger("Copper - PiGPIO Java Demo");
    
    private static final int PIN = 25;
    private static final int DELAY = 1000000;

    public static void main(String[] args) {
        NativeControllerImpl gpio = new NativeControllerImpl();
        if (gpio.initialise()< 0) {
            logger.log( Level.SEVERE, "PiGPIO setup error");
            return;
        }
        gpio.setMode(PIN, OUTPUT);
        while(true) {
            gpio.write(PIN, HIGH);
            gpio.delay(DELAY);
            gpio.write(PIN, LOW);
            gpio.delay(DELAY);
        }
    }
}
