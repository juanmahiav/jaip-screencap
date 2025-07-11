package com.beanvisionary.desktop;

public class EnvironmentDetector {
    private EnvironmentDetector() { }

    public static String describe() {
        return "OS name: "     + System.getProperty("os.name")    + "\n" +
                "OS version: "  + System.getProperty("os.version") + "\n" +
                "Architecture: "+ System.getProperty("os.arch")    + "\n" +
                "Java version: "+ System.getProperty("java.version");
    }
}
