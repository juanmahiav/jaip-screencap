package com.beanvisionary.desktop;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DesktopCapture {
    private static final DateTimeFormatter TS =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private DesktopCapture() { }

    public static Path capture() throws AWTException, IOException {
        Path dir = Path.of("captures");
        if (Files.notExists(dir)) {
            Files.createDirectories(dir);
        }

        Rectangle screen =
                new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage image = new Robot().createScreenCapture(screen);

        String fileName = "desktop_" + TS.format(LocalDateTime.now()) + ".png";
        Path out = dir.resolve(fileName);
        ImageIO.write(image, "png", out.toFile());
        return out;
    }

    public static Path capture(Rectangle area) throws AWTException, IOException {
        Path dir = Path.of("captures");
        if (Files.notExists(dir)) {
            Files.createDirectories(dir);
        }

        BufferedImage image = new Robot().createScreenCapture(area);

        String fileName = "desktop_" + TS.format(LocalDateTime.now()) + ".png";
        Path out = dir.resolve(fileName);
        ImageIO.write(image, "png", out.toFile());
        return out;
    }
}
