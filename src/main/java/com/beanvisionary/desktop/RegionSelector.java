package com.beanvisionary.desktop;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegionSelector extends JDialog {
    private Point start, end;
    private Rectangle region;

    public RegionSelector(Window owner) {
        super(owner, Dialog.ModalityType.APPLICATION_MODAL);

        setUndecorated(true);
        setAlwaysOnTop(true);

        setBackground(new Color(0, 0, 0, 0));

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen);
        setLocation(0, 0);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        JPanel overlay = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setComposite(AlphaComposite.SrcOver);
                g2.setColor(new Color(0, 0, 0, 50));
                g2.fillRect(0, 0, getWidth(), getHeight());

                if (start != null && end != null) {
                    Rectangle r = toRectangle(start, end);
                    g2.setColor(new Color(0, 0, 255, 50));
                    g2.fill(r);
                    g2.setColor(Color.BLUE);
                    g2.draw(r);
                }
                g2.dispose();
            }
        };
        overlay.setOpaque(false);
        setContentPane(overlay);

        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                start = e.getPoint();
                end = start;
                overlay.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                end = e.getPoint();
                overlay.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                end = e.getPoint();
                region = toRectangle(start, end);
                // This dispose() unblocks setVisible(true) below
                dispose();
            }
        };
        overlay.addMouseListener(ma);
        overlay.addMouseMotionListener(ma);
    }

    public static Rectangle selectRegion(Window owner) {
        RegionSelector rs = new RegionSelector(owner);
        rs.setVisible(true);   // blocks until dispose()
        return rs.region;
    }

    private static Rectangle toRectangle(Point a, Point b) {
        int x = Math.min(a.x, b.x);
        int y = Math.min(a.y, b.y);
        int w = Math.abs(a.x - b.x);
        int h = Math.abs(a.y - b.y);
        return new Rectangle(x, y, w, h);
    }
}
