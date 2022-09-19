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
 * Interface for SPI functions of PiGPIO
 *
 * @author Mark J Koch
 */
public interface SPIInterface {

    /**
     * This function returns a handle for the SPI device on the channel. Data
     * will be transferred at baud bits per second. The flags may be used to
     * modify the default behaviour of 4-wire operation, mode 0, active low chip
     * select.
     *
     * <p>
     * The Pi has two SPI peripherals: main and auxiliary.
     * The main SPI has two chip selects (channels), the auxiliary has three.
     * The auxiliary SPI is available on all models but the A and B.
     * </p>
     * <p>
     * The GPIO used are given in the following table.
     *
     * <pre>
     *             MISO   MOSI   SCLK  CE0   CE1   CE2 
     * Main SPI      9     10     11    8      7     - 
     * Aux SPI      19     20     21    18    17    16
     *</pre>
     *</p>
     * 
     * <p>
     * spiFlags consists of the least significant 22 bits.
     * <pre>
     * 21 20 19 18 17 16 15 14 13 12 11 10  9  8  7  6  5  4  3  2  1  0 
     *  b  b  b  b  b  b  R  T  n  n  n  n  W  A u2 u1 u0 p2 p1 p0  m  m
     * </pre>
     * <ul>
     * <li>mm defines the SPI mode.
     * <br>  Warning: modes 1 and 3 do not appear to work on the auxiliary SPI.
     * <pre>
     *   Mode POL PHA 
     *     0   0   0 
     *     1   0   1 
     *     2   1   0 
     *     3   1   1<pre>
     * <li>px is 0 if CEx is active low (default) and 1 for active high.
     * <li>ux is 0 if the CEx GPIO is reserved for SPI (default) and 1 otherwise.
     * <li>A is 0 for the main SPI, 1 for the auxiliary SPI.
     * <li>W is 0 if the device is not 3-wire, 1 if the device is 3-wire. Main 
     *      SPI only.
     * <li>nnnn defines the number of bytes (0-15) to write before switching the
     * MOSI line to MISO to read data. This field is ignored if W is not set.
     * Main SPI only.
     *
     * <li>T is 1 if the least significant bit is transmitted on MOSI first, the
     * default (0) shifts the most significant bit out first. Auxiliary SPI
     * only.
     *
     * <li>R is 1 if the least significant bit is received on MISO first, the
     * default (0) receives the most significant bit first. Auxiliary SPI only.
     *
     * <li>bbbbbb defines the word size in bits (0-32). The default (0) sets 8 bits
     * per word. Auxiliary SPI only.
     *
     * </ul></p>
     * The spiRead, spiWrite, and spiXfer functions transfer data packed into 1,
     * 2, or 4 bytes according to the word size in bits.<br>
     *
     * For bits 1-8 there will be one byte per word. For bits 9-16 there will be
     * two bytes per word. For bits 17-32 there will be four bytes per word.<br>
     *
     * Multi-byte transfers are made in least significant byte first order.<br>
     *
     * E.g. to transfer 32 11-bit words buf should contain 64 bytes and count
     * should be 64.<br>
     *
     * E.g. to transfer the 14 bit value 0x1ABC send the bytes 0xBC followed by
     * 0x1A.<br>
     *
     * The other bits in flags should be set to zero.
     *
     *
     * @param spiChan 0-1 (0-2 for the auxiliary SPI)
     * @param baud 32K-125M (values above 30M are unlikely to work)
     * @param spiFlags see javadoc above
     * @return a handle (>=0) if OK, otherwise PI_BAD_SPI_CHANNEL,
     *   PI_BAD_SPI_SPEED, PI_BAD_FLAGS, PI_NO_AUX_SPI, or PI_SPI_OPEN_FAILED.
     */
    public int spiOpen(int spiChan, int baud, int spiFlags);

    /**
     * Closes the SPI device identified by the handle.
     *
     * @param handle >=0, as returned by a call to spiOpen
     * @return 0 if OK, otherwise PI_BAD_HANDLE.
     */
    public int spiClose(int handle);

    /**
     * Reads count bytes of data from the SPI device associated with the handle.
     *
     * handle: >=0, as returned by a call to spiOpen buf: an array to receive
     * the read data bytes count: the number of bytes to read
     *
     * Returns the number of bytes transferred if OK, otherwise PI_BAD_HANDLE,
     * PI_BAD_SPI_COUNT, or PI_SPI_XFER_FAILED. int spiWrite(unsigned handle,
     * char *buf, unsigned count) This function writes count bytes of data from
     * buf to the SPI device associated with the handle.
     *
     * handle: >=0, as returned by a call to spiOpen buf: the data bytes to
     *
     * @param handle >=0
     * @param buf array to receive bytes into
     * @param count the number of bytes to write
     * @return the number of bytes transferred if OK, otherwise PI_BAD_HANDLE,
     * PI_BAD_SPI_COUNT, or PI_SPI_XFER_FAILED.
     */
    public int spiRead(int handle, byte buf[], int count);

    /**
     * Transfers count bytes of data from txBuf to the SPI device associated
     * with the handle. Simultaneously count bytes of data are read from the
     * device and placed in rxBuf.
     *
     *
     * Returns 
     *
     * @param handle >=0, as returned by a call to spiOpen 
     * @param txBuf the data bytes to
     * @param rxBuf the received data bytes 
     * @param count the number of bytes to transfer
     * @return the number of bytes transferred if OK, otherwise PI_BAD_HANDLE,
     * PI_BAD_SPI_COUNT, or PI_SPI_XFER_FAILED.
     */
    public int spiXfer(int handle, byte[] txBuf, byte[] rxBuf, int count);

}
