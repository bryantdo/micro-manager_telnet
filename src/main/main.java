package main;

import mmcorej.CMMCore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class main {

  public static void main(String[] args) throws IOException {
    CMMCore core = new CMMCore();
    String info = core.getVersionInfo();
    System.out.println(info);

    Socket lightsSocket             = null;
    PrintWriter streamToLights      = null;
    BufferedReader streamFromLights = null;

    try {
      lightsSocket = new Socket("10.13.0.27", 50630);
      streamToLights = new PrintWriter(lightsSocket.getOutputStream(), true);
      streamFromLights = new BufferedReader(new InputStreamReader(lightsSocket.getInputStream()));
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
      return;
    }

    System.out.println(streamFromLights.readLine());

    streamToLights.println("help");
    String fromLightsLine;
    while((fromLightsLine = streamFromLights.readLine()) != null) {
      System.out.println(fromLightsLine);
    }


    //System.out.println(streamFromLights.readLine());

    //out.close();
    //in.close();
    //lightsSocket.close();
  }

}
