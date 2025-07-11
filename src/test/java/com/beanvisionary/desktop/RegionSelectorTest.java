package com.beanvisionary.desktop;

import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.awt.Rectangle;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class RegionSelectorTest {
    @Test
    void toRectangleWorksRegardlessOfPointOrder() throws Exception {
        Method m = RegionSelector.class.getDeclaredMethod("toRectangle", Point.class, Point.class);
        m.setAccessible(true);

        Point a = new Point(5, 10);
        Point b = new Point(20, 30);
        Rectangle expected = new Rectangle(5, 10, 15, 20);

        Rectangle r1 = (Rectangle) m.invoke(null, a, b);
        Rectangle r2 = (Rectangle) m.invoke(null, b, a);

        assertEquals(expected, r1, "toRectangle should compute correct region");
        assertEquals(expected, r2, "toRectangle should be order independent");
    }
}
