package org.hackrva.telnet.shell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hackrva.telnet.ProgramLauncherSingleton;

import net.wimpi.telnetd.io.BasicTerminalIO;
import net.wimpi.telnetd.net.Connection;
import net.wimpi.telnetd.net.ConnectionEvent;
import net.wimpi.telnetd.shell.Shell;

public class SimpleShell implements Shell
{
        
    private Connection m_Connection;
    private BasicTerminalIO m_IO;

    public void run(Connection con)
    {
        m_Connection = con;
        m_IO = m_Connection.getTerminalIO();
        // register the connection listener
        m_Connection.addConnectionListener(this);

        try
        {
            m_IO.eraseScreen();

            List<Character> chars = new ArrayList<Character>();
            while (true)
            {
                int i = m_IO.read();
                if (i == BasicTerminalIO.ENTER)
                {
                    m_IO.write("\r\n"); // some output
                    m_IO.flush(); // flush the output to ensure it is sent

                    char[] charArray = new char[chars.size()];
                    for (int j = 0; j < chars.size(); j++)
                    {
                        charArray[j] = chars.get(j);
                    }

                    if (new String(charArray).equals("execute"))
                    {
                        m_IO.write("Running program.\r\n"); // some output
                        m_IO.flush(); // flush the output to ensure it is sent
                        ProgramLauncherSingleton.getInstance().launchProgram();
                    }
                    else
                    {
                        m_IO.write("Invalid Input.\r\n"); // some output
                        m_IO.flush(); // flush the output to ensure it is sent
                    }
                    
                    chars = new ArrayList<Character>();
                }
                else
                {
                    char c = (char) i;
                    chars.add(c);
                    m_IO.write(c);
                    m_IO.flush();
                }
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // erase the screen
    }// run

    public void connectionTimedOut(ConnectionEvent ce)
    {
        try
        {
            m_IO.write("CONNECTION_TIMEDOUT");
            m_IO.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // close connection
        m_Connection.close();
    }// connectionTimedOut

    public void connectionIdle(ConnectionEvent ce)
    {
        try
        {
            m_IO.write("CONNECTION_IDLE");
            m_IO.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }// connectionIdle

    public void connectionLogoutRequest(ConnectionEvent ce)
    {
        try
        {
            m_IO.write("CONNECTION_LOGOUTREQUEST");
            m_IO.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }// connectionLogout

    public void connectionSentBreak(ConnectionEvent ce)
    {
        try
        {
            m_IO.write("CONNECTION_BREAK");
            m_IO.flush();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }// connectionSentBreak

    public static Shell createShell()
    {
        return new SimpleShell();
    }// createShell
}// class SimpleShell