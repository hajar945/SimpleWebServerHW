package simplewebclient;

import java.io.*;
import java.net.*;

import java.util.Scanner;

public class SimpleWebClient {
    private static final String hostName = "localhost";
    private static final int PORT = 8080;

	public static void main(String[] args) throws IOException {
        try (
            Socket serverSocket = new Socket(hostName, PORT);
            PrintWriter out =
                new PrintWriter(serverSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(serverSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
            String userInput;
            if ((userInput = stdIn.readLine()) != null) {
            	
            	String parts[] = userInput.split(" ");
                if(parts[0].equals("PUT")) {
                	Scanner scan = new Scanner(new File(parts[1]));
                	String file = "";
                	while(scan.hasNextLine()) {
                		file += scan.nextLine() + "\n";
                	}
                	userInput = userInput + "\n" + file;
                	scan.close();
                }
                
                out.println(userInput);
                String response=in.readLine();               
                
                if (response!=null) {
                	System.out.println("Response from Server: ");
                	System.out.println(response);
                	while ((response=in.readLine())!=null) {
                		System.out.println(response);
                	}
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +  hostName);
            System.exit(1);
        } 
    }
	
}
