package org.thcg.core;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author Severle
 * @date 2024-01-17 23:42:50
 * @description
 */
@Slf4j
public class Initializer {
    /**
     * 程序初始化启动方法
     */
    public static void initialize() {
        CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            } catch (ClassNotFoundException e) {
                log.error("Cannot found the laf, {}", e.getMessage(), e.fillInStackTrace());
            } catch (InstantiationException e) {
                log.error("Creating laf error, {}", e.getMessage(), e.fillInStackTrace());
            } catch (IllegalAccessException e) {
                log.error("Cannot access when changing laf, {}", e.getMessage(), e.fillInStackTrace());
            } catch (UnsupportedLookAndFeelException e) {
                log.error("Unsupported laf, {}", e.getMessage(), e.fillInStackTrace());
            }
            latch.countDown();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error("Initializing LAF waiting error, {}", e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * 程序退出释放资源方法
     */
    public static void terminal() {

    }
}
