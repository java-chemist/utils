/**
 * 
 */
package com.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

/**
 * @author lugupta
 *
 */
public class SocketAliveUtil {

	public static void main(String[] args) {
		log(isSocketAlive("localhost", 8080));
	}

	public static boolean isSocketAlive(String hostName, int port) {
		boolean isAlive = false;

		SocketAddress socketAddress = new InetSocketAddress(hostName, port);
		Socket socket = new Socket();

		// Timeout required - in milliseconds
		int timeout = 4000;

		log("hostName: " + hostName + ", port: " + port);

		try {
			socket.connect(socketAddress, timeout);
			socket.close();
			isAlive = true;

		} catch (SocketTimeoutException exception) {
			System.out.println("SocketTimeoutException " + hostName + ":" + port + ". " + exception.getMessage());
		} catch (IOException exception) {
			System.out.println(
					"IOException - Unable to connect to " + hostName + ":" + port + ". " + exception.getMessage());
		}

		return isAlive;
	}

	private static void log(String string) {
		System.out.println(string);
	}

	private static void log(boolean isAlive) {
		System.out.println("isAlive result: " + isAlive + "\n");
	}

}
