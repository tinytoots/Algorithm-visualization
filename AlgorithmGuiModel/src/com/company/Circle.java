package com.company;
import java.awt.*;

public class Circle {

    private int x, y, r;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public int getR() {

        return r;
    }

    //contain方法判断点是否落在圆内
    public boolean contain(Point p){
        return Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2) <= r*r;
    }
    //Math.pow(a, 3) 等于求a的3次方
}
