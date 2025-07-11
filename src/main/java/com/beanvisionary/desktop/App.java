package com.beanvisionary.desktop;

import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        System.out.println(EnvironmentDetector.describe());

        try {
            var file = DesktopCapture.capture();
            System.out.println("Screenshot saved to " + file.toAbsolutePath());
        } catch (AWTException | IOException ex) {
            logger.log(Level.SEVERE, "Failed to capture desktop", ex);
            System.exit(1);
        }
    }
}
