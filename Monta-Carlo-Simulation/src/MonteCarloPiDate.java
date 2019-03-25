import java.awt.*;
import java.util.LinkedList;

public class MonteCarloPiDate { //这个类包含数据层中的所有内容

    private Circle circle; //数据
    private int insideCircle = 0; //在圆中点的数量，初始化为0
    private LinkedList<Point> points;

    public MonteCarloPiDate(Circle circle){
        this.circle = circle;
        points = new LinkedList<Point>();
    }

    public Circle getCircle() {
        return circle;
    }

    public Point getPoint(int i){
        if(i < 0 || i >= points.size()) //越界判断
            throw new IllegalArgumentException("out of bound in getPoint!");

        return points.get(i);
    }

    public int getPointNumber(){ //用来估算正方形的面积
        return points.size();
    }

    public void addPoint(Point p){
        points.add(p);
        if(circle.contain(p))
            insideCircle++;
    }

    public double estimatePi(){

        if(points.size() == 0) //如果没有投任何点，Pi的估计值为0
            return 0.0;

        int circleArea = insideCircle;
        int squareArea = points.size();
        return (double)circleArea * 4 / squareArea;
    }
}
