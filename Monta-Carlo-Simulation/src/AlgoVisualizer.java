import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class AlgoVisualizer {

    //TODO: 创建自己的数据
    private static int DELAY = 40;

    private Circle circle; //数据
    private LinkedList<Point> points;
    private AlgoFrame frame; //视图
    private int N;

    //构造函数对数据和视图进行初始化，传入三个参数

    //初始化circle
    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){ //N代表用户希望往屏幕中打入的点数

        //创建一个正方向窗口
        if(sceneWidth != sceneHeight)
            throw new IllegalArgumentException("This demo must be run in a square window!");

        //初始化数据
        this.N = N;

        circle = new Circle(sceneWidth/2, sceneHeight/2, sceneWidth/2);
        points = new LinkedList<Point>();

        // 初始化视图
        EventQueue.invokeLater(() -> {
            //使用自己创建的窗口
            frame = new AlgoFrame("Get Pi with Monte Carlo", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start(); //启动线程执行
        });
    }

    //封装动画逻辑
    public void run(){

        for(int i = 0 ; i < N ; i ++){

            frame.render(circle, points); //根据圆圈circle以及points里存在的点进行绘制
            AlgoVisHelper.pause(DELAY); //停顿delay毫秒

            //模拟向屏幕中打点

            //首先随机一个x和一个y值，横坐标是0到窗口的宽度，纵坐标是0到窗口的高度
            int x = (int)(Math.random() * frame.getCanvasWidth());
            int y = (int)(Math.random() * frame.getCanvasHeight());

            Point p = new Point(x, y);
            points.add(p); //把新创建的p值放入points列表
        }

    }

    public static void main(String[] args) { //指定窗口大小

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10000;

        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}

