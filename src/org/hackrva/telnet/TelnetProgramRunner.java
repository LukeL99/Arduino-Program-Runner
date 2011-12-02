package org.hackrva.telnet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.wimpi.telnetd.BootException;
import net.wimpi.telnetd.TelnetD;
import net.wimpi.telnetd.io.BasicTerminalIO;
import net.wimpi.telnetd.io.TerminalIO;

public class TelnetProgramRunner
{
    public TelnetProgramRunner(String programToRun)
    {
        try
        {
            Properties properties = new Properties();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("telnet.properties");
            properties.load(is);
            TelnetD telnetD = TelnetD.createTelnetD(properties);
            telnetD.start();
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
