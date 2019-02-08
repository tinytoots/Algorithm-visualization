//MVC结果，视图层
package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class AlgoFrame extends JFrame {

    //记录窗口大小
    private int canvasWidth; //private是因为初始化以后不希望外部用户可以随意改变，只有初始化时能赋值
    private int canvasHeight;
    //先写构造函数, 创建自己的窗口
    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title); //首先调用父亲构造函数

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;


        AlgoCanvas canvas = new AlgoCanvas(); //创建一个画布相应的对象
        //个性化自己的窗口
        //canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));//调用画布的大小
        setContentPane(canvas);
        pack(); //自动调整窗口大小

        //this是可以省略的
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public AlgoFrame(String title){
        this(title, 1024, 768);
    }

    //提供相应接口可以让外部访问两个变量，所以创建两个相应get函数，只能访问，不能修改
    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    //TODO:设置自己的数据
    private int[] money; //表示一个人手里有多少钱 render函数的作用是就data数据进行相应的渲染
    public void render(int[] money){   //设立一个render函数，收到从控制层AlgoVisualizer传进来的一个money数组
           this.money = money;
           repaint(); //调用repaint函数，自带函数，讲JFrame所有控件重新刷新一遍，会自动调用JPanel的子类AlgoCanvas（我们的画布）相应的paintComponent方法
    }

    private class AlgoCanvas extends JPanel{   //创建一个私有类，仅供AlgoFrame算法的窗口进行使用

        public AlgoCanvas(){
            super(true);
        }

        @Override  //表示是一个覆盖函数，因为JPanel 本身有paintComponent函数，如果变红，说明没覆盖

        public void paintComponent(Graphics g) {   //paintComponent 函数, 是一个方法

            super.paintComponent(g); //因为paintComponent是覆盖父类的方法，所以第一行调用父类相应的super.paintComponent，把g传进去

            //使用graphics 2D，讲graphics对象转换为graphic 对象
            Graphics2D g2d = (Graphics2D) g; //强制转化


            //抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
            g2d.addRenderingHints(hints);


            //具体绘制
            //Todo: 绘制自己的数据data
            int w = canvasWidth / money.length; //每一个小矩形的宽度
            for(int i =0; i < money.length; i++){ //对于每一个money数组的数都绘制一个矩形柱状图表示每个人有多少钱

                if(money[i] > 0) {
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Blue);
                    AlgoVisHelper.fillRectangle(g2d, i * w + 1, canvasHeight/2 - money[i], w - 1, money[i]);
                }
                else if(money[i] < 0) {
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                    AlgoVisHelper.fillRectangle(g2d, i * w + 1, canvasHeight / 2, w - 1, -money[i]);
                }

            }
        }





        @Override
        public Dimension getPreferredSize(){   //返回画布的大小
            return new Dimension(canvasWidth,canvasHeight);
        }

    }

}
