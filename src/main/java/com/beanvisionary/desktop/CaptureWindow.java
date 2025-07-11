package com.beanvisionary.desktop;

import javax.swing.*;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.IOException;
import java.nio.file.Path;

public class CaptureWindow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CaptureWindow::new);
    }

    public CaptureWindow() {
        JFrame frame = new JFrame("Desktop Capture");
        JLabel preview = new JLabel();
        preview.setHorizontalAlignment(SwingConstants.CENTER);

        JButton button = new JButton("+Capture+");
        button.addActionListener(e -> capture(frame, preview));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(preview, BorderLayout.CENTER);
        frame.getContentPane().add(button, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void capture(JFrame frame, JLabel preview) {
        try {
            frame.setVisible(false);

            Rectangle area = RegionSelector.selectRegion(frame);
            frame.setVisible(true);
            if (area == null || area.width <= 0 || area.height <= 0) {
                JOptionPane.showMessageDialog(
                frame,
                "No valid region selected. Please draw a rectangle to capture.",
                "Capture Cancelled",
                JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            Path file = DesktopCapture.capture(area);
            preview.setIcon(new ImageIcon(file.toString()));
            frame.pack();
        } catch (AWTException | IOException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame,
                    "Failed to capture screenshot:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
