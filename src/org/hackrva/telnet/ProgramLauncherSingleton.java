package org.hackrva.telnet;

import java.io.IOException;

public class ProgramLauncherSingleton
{

    private String path;

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }
    
    public void launchProgram() throws IOException
    {
        Runtime.getRuntime().exec(path);
    }

    private static ProgramLauncherSingleton instance = null;

    protected ProgramLauncherSingleton()
    {
        // Exists only to defeat instantiation.
    }

    public static ProgramLauncherSingleton getInstance()
    {
        if (instance == null)
        {
            instance = new ProgramLauncherSingleton();
        }
        return instance;
    }
}
