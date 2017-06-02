package com.common.utils.request;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * SocketUtils
 *
 * @author Administrator
 */
public class SocketUtils {

  /**
   * send
   *
   * @param ip
   *        ip
   * @param port
   *        port
   * @param message
   *        message
   * @param flgReceive
   *        flgReceive
   * @param encoding
   *        encoding
   * @return return
   */
  public static String send(String ip, int port, String message, boolean flgReceive, String encoding) {
    try {
      String result = "";
      Socket handler = new Socket(ip, port);
      handler.setSendBufferSize(Integer.MAX_VALUE);
      handler.setSoTimeout(0);
      handler.setReceiveBufferSize(Integer.MAX_VALUE);
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(handler.getOutputStream(),
          encoding);
      outputStreamWriter.write(message);
      outputStreamWriter.flush();
      if (!flgReceive) {
        handler.shutdownInput();
        handler.shutdownOutput();
        handler.close();
        return result;
      }
      InputStreamReader inputStreamReader = new InputStreamReader(handler.getInputStream());
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      int current = -1;
      while ((current = inputStreamReader.read()) != -1) {
        byteArrayOutputStream.write(current);
        if (current == 0) {
          break;
        }
      }
      result = byteArrayOutputStream.toString(encoding);
      handler.shutdownInput();
      handler.shutdownOutput();
      handler.close();
      return result;
    } catch (Exception err) {
      throw new RuntimeException(err.getMessage());
    }
  }

  /**
   * send
   *
   * @param ip
   *        ip
   * @param port
   *        port
   * @param message
   *        message
   * @param flgReceive
   *        flgReceive
   * @return return
   */
  public static String send(String ip, int port, String message, boolean flgReceive) {
    return send(ip, port, message, flgReceive, "utf8");
  }

  /**
   * send
   *
   * @param ip
   *        ip
   * @param port
   *        port
   * @param message
   *        message
   * @return return
   */
  public static String send(String ip, int port, String message) {
    return send(ip, port, message, false, "utf8");
  }
}
