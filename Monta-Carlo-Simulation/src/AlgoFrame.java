//MVC结果，视图层
import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class AlgoFrame extends JFrame{

    //记录窗口大小
    private int canvasWidth; //private是因为初始化以后不希望外部用户可以随意改变，只有初始化时能赋值
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title); //首先调用父亲构造函数

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas(); //创建一个画布相应的对象
        //个性化自己的窗口
        //canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));//调用画布的大小
        setContentPane(canvas);
        pack(); //自动调整窗口大小

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public AlgoFrame(String title){

        this(title, 1024, 768);
    }

    //提供相应接口可以让外部访问两个变量，所以创建两个相应get函数，只能访问，不能修改
    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // data
    private Circle circle;
    private LinkedList<Point> points;
    public void render(Circle circle, LinkedList<Point> points){ //设立一个render函数，收到从控制层AlgoVisualizer传进来的一个data数组
        this.circle = circle;
        this.points = points;
        repaint(); //调用repaint函数，自带函数，讲JFrame所有控件重新刷新一遍，会自动调用JPanel的子类AlgoCanvas（我们的画布）相应的paintComponent方法
    }

    private class AlgoCanvas extends JPanel{ //创建一个私有类，仅供AlgoFrame算法的窗口进行使用

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        @Override //表示是一个覆盖函数，因为JPanel 本身有paintComponent函数，如果变红，说明没覆盖
        public void paintComponent(Graphics g) { //paintComponent 函数, 是一个方法
            super.paintComponent(g); //因为paintComponent是覆盖父类的方法，所以第一行调用父类相应的super.paintComponent，把g传进去

            //使用graphics 2D，讲graphics对象转换为graphic 对象
            Graphics2D g2d = (Graphics2D)g; //强制转化

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            AlgoVisHelper.setStrokeWidth(g2d, 3); //绘制圆，设置线条宽度为3
            AlgoVisHelper.setColor(g2d, AlgoVisHelper.Blue);
            AlgoVisHelper.strokeCircle(g2d, circle.getX(), circle.getY(), circle.getR()); //绘制一个空心圆

            for(int i = 0 ; i < points.size() ; i ++){ //绘制所有的点
                Point p = points.get(i); //每次取出链表中一个point
                if(circle.contain(p))
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                else
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Green);

                AlgoVisHelper.fillCircle(g2d, p.x, p.y, 3); //绘制一个相对比较小的实心圆
            }
        }

        @Override
        public Dimension getPreferredSize(){ //返回画布的大小
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}