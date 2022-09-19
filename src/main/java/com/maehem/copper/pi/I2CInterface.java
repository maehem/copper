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
 * @author Mark J. Koch (GitHub @maehem)
 */
public interface I2CInterface {

    /**
     * Returns a handle for the device at the address on the I2C bus.
     *
     * <p>
     * Physically buses 0 and 1 are available on the Pi. <br>Higher numbered buses
     * will be available if a kernel supported bus multiplexer is being used.<br>
     * <br>
     * The GPIO used are given in the following table.
     * <pre>
     *              SDA SCL 
     *      I2C 0    0   1 
     *      I2C 1    2   3
     * </pre>
     *
     * For the SMBus commands the low level transactions are shown at the end of
     * the function description.<br>
     * The following abbreviations are used:
     * <pre>
     *      S       (1 bit) : Start bit 
     *      P       (1 bit) : Stop bit 
     *      Rd/Wr   (1 bit) : Read/Write bit. Rd equals 1, Wr equals 0. 
     *      A, NA   (1 bit) : Accept and not accept bit.
     * 
     *      Addr    (7 bits): I2C 7 bit address. 
     *      i2cReg  (8 bits): Command byte, a byte which often selects a register.
     *      Data    (8 bits): A data byte. 
     *      Count   (8 bits): A byte defining the length of a block operation.
     *
     *      [..]: Data sent by the device.
     * </pre>
     * </p>
     *
     * @param i2cBus  >=0
     * @param i2cAddr 0-0x7F
     * @param i2cFlags No flags are currently defined. This parameter should be set to zero.
     * @return a handle (>=0) if OK, otherwise PI_BAD_I2C_BUS, PI_BAD_I2C_ADDR,
     * PI_BAD_FLAGS, PI_NO_HANDLE, or PI_I2C_OPEN_FAILED.
     */
    public int i2cOpen(int i2cBus, int i2cAddr, int i2cFlags);

    /**
     * Closes the I2C device associated with the handle.
     * 
     * @param handle >=0, as returned by a call to i2cOpen
     * @return 0 if OK, otherwise PI_BAD_HANDLE.
     */
    public int i2cClose(int handle);

    /**
     * Sends a single bit (in the Rd/Wr bit) to the device associated with
     * handle.
     *
     * <p>
     * Quick command. SMBus 2.0 5.5.1
     * <pre>    S Addr bit [A] P</pre>
     * </p>
     *
     * @param handle >=0, as returned by a call to i2cOpen bit: 0-1, the value to write
     * @param bit 1 or 0
     * @return 0 if OK, otherwise PI_BAD_HANDLE, PI_BAD_PARAM, or PI_I2C_WRITE_FAILED.
     */
    public int i2cWriteQuick(int handle, int bit);

    /**
     * Sends a single byte to the device associated with handle.
     * <p>
     * Send byte. SMBus 2.0 5.5.2 
     * <pre>    S Addr Wr [A] bVal [A] P</pre>
     * </p>
     *
     * @param handle  >=0, as returned by a call to i2cOpen bVal: 0-0xFF, the value to write
     * @param bVal 1 or 0
     * @return 0 if OK, otherwise PI_BAD_HANDLE, PI_BAD_PARAM, or PI_I2C_WRITE_FAILED.
     */
    public int i2cWriteByte(int handle, int bVal);

    /**
     * Reads a single byte from the device associated with handle.
     *
     * <p>
     * Receive byte. SMBus 2.0 5.5.3 
     * <pre>    S Addr Rd [A] [Data] NA P</pre>
     * </p>
     *
     * @param handle >=0, as returned by a call to i2cOpen
     * @return the byte read (>=0) if OK, otherwise PI_BAD_HANDLE, or
     * PI_I2C_READ_FAILED.
     */
    public int i2cReadByte(int handle);

    /**
     * Writes a single byte to the specified register of the device
     * associated with handle.
     *
     * <p>
     * Write byte. SMBus 2.0 5.5.4 
     * <pre>    S Addr Wr [A] i2cReg [A] bVal [A] P</pre>
     * </p>
     * 
     * @param handle >=0, as returned by a call to i2cOpen i2cReg: 0-255, 
     *               the register to write bVal: 0-0xFF, the value to write
     * @param i2cReg
     * @param bVal
     * @return 0 if OK, otherwise PI_BAD_HANDLE, PI_BAD_PARAM, or
     *                  PI_I2C_WRITE_FAILED.
     */
    public int i2cWriteByteData(int handle, int i2cReg, int bVal);

    /**
     * Writes a single 16 bit word to the specified register of the device
     * associated with handle.
     *
     * <p>
     * Write word. SMBus 2.0 5.5.4 
     * <pre>    S Addr Wr [A] i2cReg [A] wValLow [A] wValHigh [A] P</pre>
     * </p>
     *
     * @param handle  >=0, as returned by a call to i2cOpen  
     * @param i2cReg  0-255, the register to write
     * @param wVal    0x0000-0xFFFF, the value to write
     * @return 0 if OK, otherwise PI_BAD_HANDLE, PI_BAD_PARAM, or
     * PI_I2C_WRITE_FAILED.
     */
    public int i2cWriteWordData(int handle, int i2cReg, int wVal);

    /**
     * Read a single byte from the specified register of the device
     * associated with handle.
     *
     * <p>
     * Read byte. SMBus 2.0 5.5.5 
     * <pre>     S Addr Wr [A] i2cReg [A] S Addr Rd [A] [Data] NA P</pre>
     * </p>
     * @param handle >=0, as returned by a call to i2cOpen 
     * @param i2cReg 0-255, the register to read
     * @return the byte read (>=0) if OK, otherwise PI_BAD_HANDLE, PI_BAD_PARAM,
     * or PI_I2C_READ_FAILED.
     */
    public int i2cReadByteData(int handle, int i2cReg);

    /**
     * Read a single 16 bit word from the specified register of the device
     * associated with handle.
     * 
     * <p>
     * Read word. SMBus 2.0 5.5.5
     * <pre>S Addr Wr [A] i2cReg [A] S Addr Rd [A] [DataLow] A [DataHigh] NA P</pre>
     * </p>
     * 
     * @param handle >=0, as returned by a call to i2cOpen 
     * @param i2cReg i2cReg: 0-255, the register to read
     * @return the word read (>=0) if OK, otherwise PI_BAD_HANDLE, PI_BAD_PARAM,
     * or PI_I2C_READ_FAILED.
     */
    public int i2cReadWordData(int handle, int i2cReg);

    /**
     * Writes 16 bits of data to the specified register of the device
     * associated with handle and reads 16 bits of data in return.
     *
     * <p>
     * Process call. SMBus 2.0 5.5.6
     * <pre>    S Addr Wr [A] i2cReg [A] wValLow [A] wValHigh [A] S Addr Rd [A] [DataLow] A [DataHigh] NA P</pre>
     * </p>
     * 
     * @param handle  >=0, as returned by a call to i2cOpen 
     * @param i2cReg i2cReg: 0-255, the register to write/read 
     * @param wVal wVal: 0-0xFFFF, the value to write
     * @return the word read (>=0) if OK, otherwise PI_BAD_HANDLE, 
     *              PI_BAD_PARAM, or PI_I2C_READ_FAILED.
     */
    public int i2cProcessCall(int handle, int i2cReg, int wVal);

    /**
     * Writes up to 32 bytes to the specified register of the device
     * associated with handle.
     * <p>
     * Block write. SMBus 2.0 5.5.7 
     * <pre>    S Addr Wr [A] i2cReg [A] count [A] buf0 [A] buf1 [A] ... [A] bufn [A] P</pre>
     * </p>
     *
     * @param handle >=0, as returned by a call to i2cOpen 
     * @param i2cReg 0-255, the register to write 
     * @param buf an array with the data to send 
     * @param count count: 1-32, the number of bytes to write
     * @return 0 if OK, otherwise PI_BAD_HANDLE, PI_BAD_PARAM, or
     *                  PI_I2C_WRITE_FAILED.
     */
    public int i2cWriteBlockData(int handle, int i2cReg, byte[] buf, int count);

    /**
     * Reads a block of up to 32 bytes from the specified register of the
     * device associated with handle.
     * <p>
     * The amount of returned data is set by the device.
     * <br><br>
     * Block read. SMBus 2.0 5.5.7 
     * <pre>S Addr Wr [A] i2cReg [A] S Addr Rd [A] [Count] A [buf0] A [buf1] A ... A [bufn] NA P</pre>
     * </p>
     * 
     * @param handle >=0, as returned by a call to i2cOpen 
     * @param i2cReg 0-255, the register to read 
     * @param buf an array to receive the read data
     * @return the number of bytes read (>=0) if OK, otherwise PI_BAD_HANDLE,
     * PI_BAD_PARAM, or PI_I2C_READ_FAILED.
     */
    public int i2cReadBlockData(int handle, int i2cReg, byte[] buf);

    /**
     * Writes data bytes to the specified register of the device associated
     * with handle and reads a device specified number of bytes of data in
     * return.
     * <p>
     * The SMBus 2.0 documentation states that a minimum of 1 byte may be sent
     * and a minimum of 1 byte may be received. The total number of bytes
     * sent/received must be 32 or less.<br>
     * <br>
     * Block write-block read. SMBus 2.0 5.5.8 
     * <pre>S Addr Wr [A] i2cReg [A] count [A] buf0 [A] ... bufn [A] S Addr Rd [A] [Count] A [buf0] A ... [bufn] A P</pre>
     * </p>
     *
     * @param handle >=0, as returned by a call to i2cOpen 
     * @param i2cReg i2cReg: 0-255, the register to write/read 
     * @param buf an array with the data to send and to receive the read data 
     * @param count count: 1-32, the number of bytes to write
     * @return  the number of bytes read (>=0) if OK, otherwise PI_BAD_HANDLE,
     *                  PI_BAD_PARAM, or PI_I2C_READ_FAILED.
     */
    public int i2cBlockProcessCall(int handle, int i2cReg, byte[] buf, int count);

    /**
     * Reads count bytes from the specified register of the device
     * associated with handle . The count may be 1-32.
     * <p>
     * Block read. SMBus 2.0 5.5.7
     * <pre>    S Addr Wr [A] i2cReg [A] S Addr Rd [A] [buf0] A [buf1] A ... A [bufn] NA P</pre>
     *
     * @param handle >=0, as returned by a call to i2cOpen 
     * @param i2cReg 0-255, the register to read 
     * @param buf an array to receive the read data 
     * @param count 1-32, the number of bytes to read
     * @return the number of bytes read (>0) if OK, otherwise PI_BAD_HANDLE,
     *                      PI_BAD_PARAM, or PI_I2C_READ_FAILED.
     */
    public int i2cReadI2CBlockData(int handle, int i2cReg, byte[] buf, int count);

    /**
     * Writes 1 to 32 bytes to the specified register of the device
     * associated with handle.
     *
     * <p>
     * Block write. SMBus 2.0 5.5.7
     * <pre>     S Addr Wr [A] i2cReg [A] buf0 [A] buf1 [A] ... [A] bufn [A] P</pre>
     * </p>
     * 
     * @param handle >=0, as returned by a call to i2cOpen 
     * @param i2cReg 0-255, the register to write  
     * @param buf the data to write 
     * @param count 1-32, the number of bytes to write
     * @return 0 if OK, otherwise PI_BAD_HANDLE, PI_BAD_PARAM, or
     *              PI_I2C_WRITE_FAILED.
     */
    public int i2cWriteI2CBlockData(int handle, int i2cReg, byte[] buf, int count);

    /**
     * Reads count bytes from the raw device into buf.
     * <p>
     * Block read. SMBus 2.0 5.5.7
     * <pre>     S Addr Rd [A] [buf0] A [buf1] A ... A [bufn] NA P</pre>
     * </p>
     *
     * @param handle >=0, as returned by a call to i2cOpen 
     * @param buf buf: an array to receive the read data bytes 
     * @param count count: >0, the number of bytes to read
     * @return count (>0) if OK, otherwise PI_BAD_HANDLE, PI_BAD_PARAM, or
     *                              PI_I2C_READ_FAILED.
     */
    public int i2cReadDevice(int handle, byte[] buf, int count);

    /**
     * Writes count bytes from buf to the raw device.
     *
     * <p>
     * Block write. SMBus 2.0 5.5.7
     * <pre>     S Addr Wr [A] buf0 [A] buf1 [A] ... [A] bufn [A] P</pre>
     * </p>
     * 
     * @param handle handle: >=0, as returned by a call to i2cOpen 
     * @param buf buf: an array containing the data bytes to write 
     * @param count count: >0, the number of bytes to write
     * @return  0 if OK, otherwise PI_BAD_HANDLE, PI_BAD_PARAM, or
     *                      PI_I2C_WRITE_FAILED.
     */
    public int i2cWriteDevice(int handle, byte[] buf, int count);

    /**
     * Sets the I2C (i2c-bcm2708) module "use combined transactions"
     * parameter on or off.
     * 
     * <p>
     * <b>NOTE:</b> when the flag is on a write followed by a read to the same slave
     * address will use a repeated start (rather than a stop/start).
     * </p>
     * 
     * @param setting 0 to set the parameter off, non-zero to set it on
     */
    public void i2cSwitchCombined(int setting);

    // i2cSegments not implemented
    /**
     * Executes multiple I2C segments in one transaction by
     * calling the I2C_RDWR ioctl.
     * 
     * <p>
     * Object[] pi_i2c_msg_t =  []segs
     * </p>
     * 
     * @param handle  >=0, as returned by a call to i2cOpen 
     * @param segs an array of I2C segments 
     * @param numSegs >0, the number of I2C segments
     * @return the number of segments if OK, otherwise PI_BAD_I2C_SEG.
     */
    //public int i2cSegments(int handle, Object[] segs, int numSegs);

    // i2cZip not implemented
    /**
     * Executes a sequence of I2C operations. The operations to be
     * performed are specified by the contents of inBuf which contains the
     * concatenated command codes and associated data.
     * <p>
     * The following command codes are supported:
     * <pre>      Name    Cmd &amp; Data      Meaning
     *      =====   =========       ==================================
     *      End         0           No more commands 
     *      Escape      1           Next P is two bytes 
     *      On          2           Switch combined flag on 
     *      Off         3           Switch combined flag off 
     *      Address     4           P Set I2C address to P 
     *      Flags       5 <i>lsb msb</i>   Set I2C flags to lsb + (msb << 8) 
     *      Read        6           P Read P bytes of data 
     *      Write       7           P ... Write P bytes of data
     *</pre><br>
     * </p>
     * <p>
     * The address, read, and write commands take a parameter P. Normally P is
     * one byte (0-255). If the command is preceded by the Escape command then P
     * is two bytes (0-65535, least significant byte first).
     *</p><p>
     * The address defaults to that associated with the handle. The flags
     * default to 0. The address and flags maintain their previous value until
     * updated.
     * </p>
     * <p>
     * The returned I2C data is stored in consecutive locations of outBuf.
     * </p>
     * <p>
     * Example:
     * <pre>
     *      Set address 0x53, write 0x32, read 6 bytes 
     *      Set address 0x1E, write 0x03, read 6 bytes 
     *      Set address 0x68, write 0x1B, read 8 bytes 
     *      End
     *
     *      0x04 0x53     0x07 0x01 0x32     0x06 0x06 
     *      0x04 0x1E     0x07 0x01 0x03     0x06 0x06
     *      0x04 0x68     0x07 0x01 0x1B     0x06 0x08 
     *      0x00
     * </pre>
     * </p>
     *
     * @param handle >=0, as returned by a call to i2cOpen 
     * @param inBuf pointer to the concatenated I2C commands, see above
     * @param inLen size of command buffer
     * @param outBuf pointer to buffer to hold returned data 
     * @param outLen size of output buffer
     * @return >= 0 if OK (the number of bytes read), otherwise PI_BAD_HANDLE,
     *                  PI_BAD_POINTER, PI_BAD_I2C_CMD, PI_BAD_I2C_RLEN. 
     *                  PI_BAD_I2C_WLEN, or PI_BAD_I2C_SEG.
     */
    //public int i2cZip(int handle, byte[] inBuf, int inLen, byte[] outBuf, int outLen);

}
