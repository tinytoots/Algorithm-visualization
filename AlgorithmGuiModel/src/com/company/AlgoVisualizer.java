//MVC结构，控制层
package com.company;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class AlgoVisualizer {

    //TODO: 创建自己的数据
    private static int DELAY = 40;
    private int[] money;  //数据
    private AlgoFrame frame;   //视图


    //构造函数对数据和视图进行初始化，传入三个参数

    //初始化circle
    public AlgoVisualizer(int sceneWidth, int sceneHeight) {

        //初始化数据
        //TODO：初始化数据
        money = new int[100];
        for(int i = 0; i < money.length; i++)
            money[i] = 100; //初始的时候每个人手里都有100块钱


        //初始化视图
        EventQueue.invokeLater(() -> {
            //使用自己创建的窗口
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start(); //启动线程执行
        });
    }

    //封装动画逻辑
    private void run(){

        //TODO： 编写自己的动画逻辑
        while ( true ){

            Arrays.sort(money); //内置排序
            frame.render(money);
            AlgoVisHelper.pause(DELAY); //每次绘制之后让画面停留一定的时间 40ms

            for(int k = 0; k < 50; k++){//将动画速度提高50倍
            for(int i =0; i < money.length; i++) {
                //if (money[i] > 0) {  //每个人不能欠钱
                    int j = (int) (Math.random() * money.length); //随机另外一个人
                    //让第i个人给第j个人一块钱
                    money[i] -= 1;
                    money[j] += 1;
               // }
              }
            }
        }
    }


    public static void main(String[] args){ //指定窗口大小

        int sceneWidth = 1000;
        int sceneHeight = 800;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}
