package org.hackrva.telnet;

public class ArduinoProgramServer
{
    // This program runs on your computer as a telnet server. It looks for the
    // string "execute" and runs the program specified when it is received.

    private static final String PATH_TO_EXECUTABLE = "/runme.exe";
    
    public static void main(String[] args)
    {
        new TelnetProgramRunner(PATH_TO_EXECUTABLE);
    }
}

