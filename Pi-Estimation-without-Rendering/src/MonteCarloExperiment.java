import java.awt.*;

public class MonteCarloExperiment {

    private int squareSide; //正方向的边长
    private int N; //投入点的个数
    private int outputInterval = 100; //输出间隔

    public MonteCarloExperiment(int squareSide, int N){

        if(squareSide <= 0 || N <= 0)
            throw new IllegalArgumentException("squareSide and N must larger than zero");

        this.squareSide = squareSide;
        this.N = N;
    }

    public void setOutputInterval(int interval){

        if(interval <= 0)
            throw new IllegalArgumentException("Interval must be larger than zero");

        this.outputInterval = interval;
    }

    //模拟过程，用户一调用run，模拟就开始
    public void run(){

        Circle circle = new Circle(squareSide/2, squareSide/2, squareSide/2); //圆的圆心和半径
        MonteCarloPiData data = new MonteCarloPiData(circle);

        for(int i = 0; i < N; i++){

            if(i % outputInterval == 0)
                System.out.println(data.estimatePi());

            //随机生成一个x和y值形成一个point点加入到data中
            int x = (int)(Math.random() * squareSide);
            int y = (int)(Math.random() * squareSide);
            data.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args) {

        int squareSide = 800;
        int N = 1000000;

        MonteCarloExperiment exp = new MonteCarloExperiment(squareSide, N);
        exp.setOutputInterval(10000); //每投10000个点输出一次
        exp.run();
    }
}
