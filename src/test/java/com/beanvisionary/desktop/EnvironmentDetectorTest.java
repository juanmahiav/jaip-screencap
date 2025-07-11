package com.beanvisionary.desktop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnvironmentDetectorTest {
    @Test
    void describeContainsSystemProperties() {
        String desc = EnvironmentDetector.describe();
        assertNotNull(desc, "describe() should not return null");
        assertTrue(desc.contains(System.getProperty("os.name")), "OS name missing");
        assertTrue(desc.contains(System.getProperty("os.version")), "OS version missing");
        assertTrue(desc.contains(System.getProperty("os.arch")), "Architecture missing");
        assertTrue(desc.contains(System.getProperty("java.version")), "Java version missing");
    }
}
