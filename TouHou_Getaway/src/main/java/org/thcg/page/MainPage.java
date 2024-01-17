package org.thcg.page;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Severle
 * @date 2024-01-18 00:19:56
 * @description
 */
public class MainPage extends Page {
    @Override
    public void onPaint(@NotNull Graphics2D g) {
        g.fillRect(0, 0, 200, 200);
    }
}
