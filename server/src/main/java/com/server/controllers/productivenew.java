package com.server.controllers;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
 
public class productivenew {
 
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
 
            while (true) {
                Point location = MouseInfo.getPointerInfo().getLocation();
                int x = (int) location.getX();
                int y = (int) location.getY();
 
                // Move mouse slightly to the right and back
                robot.mouseMove(x + 80, y);
                Thread.sleep(500); // 0.5 seconds
                robot.mouseMove(x, y);
                Thread.sleep(5000); // Wait 5 seconds
            }
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
 
 