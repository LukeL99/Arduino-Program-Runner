package org.hackrva.telnet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.wimpi.telnetd.BootException;
import net.wimpi.telnetd.TelnetD;

public class ArduinoProgramServer
{
    // This program runs on your computer as a telnet server. It looks for the
    // string "execute" and runs the program specified when it is received.

    public static void main(String[] args)
    {
        try
        {
            ProgramLauncherSingleton launcher = ProgramLauncherSingleton.getInstance();
            if (args[0] != null && !args[0].isEmpty())
            {
                launcher.setPath(args[0]);

                Properties properties = new Properties();
                InputStream is = ArduinoProgramServer.class.getClassLoader().getResourceAsStream("telnet.properties");
                properties.load(is);
                TelnetD telnetD = TelnetD.createTelnetD(properties);
                telnetD.start();
            }
            else
            {
                System.out.println("Specify program to run as command line argument.");
            }
        }
        catch (BootException e)
        {
            System.out.println("Error creating Telnet Daemon. Check configuration");
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
