package com.maehem.copper.examples.raspberrypi.sh1106spi;

/**
  * @filename       : SH1106SPIDemo.java
  * @date           : June 29 2017
  * @author         : soonuse from Github
  * @description:
  *   This demo is written in Java and tested with a Waveshare SH1106 
  *   1.3inch OLED module working on Raspberry Pi 3 Model B. Before running 
  *   this demo, you have to enable the SPI interface on the Raspberry Pi. 
  *   See sudo raspi-config
  *
  *   Expected result:
  *   The OLED displays strings and images.
  ***********************************************************************
  * This is a demo of SH1106 1.3inch OLED module working on Raspberry Pi.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public License
  * along with wiringPi.  If not, see <http://www.gnu.org/licenses/>.
  ***********************************************************************
 */

import java.io.*;

public class SH1106SPIDemo {
    public static void main(String[] args) {
        SH1106SPI oled = new SH1106SPI();
        if (oled.initialise()< 0) {
            System.out.println("WiringPi setup error");
            return;
        }
        oled.begin();
// Uncomment when SPI working
//        oled.mode(oled.RST_PIN, oled.OUTPUT);

        oled.clearFrameBuffer();
        oled.showImage(0, 0, new File("waveshare_logo.bmp"));
        oled.delay(2000);
        oled.clearFrameBuffer();
        oled.showString("Hello world!", 0, 36);
        oled.delay(2000);
        oled.clearFrameBuffer(true);
        oled.showMonoBitmap(0, 0, oled.WAVESHARE_LOGO, oled.OLED_WIDTH, oled.OLED_HEIGHT, false);
    }
}
