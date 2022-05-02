import java.util.Scanner;

public class Main {
    public double rectangleIntegration(Calculator calculator, double bottomEdge, double topEdge, int stepCount) {
        double step = (topEdge - bottomEdge) / stepCount;
        double square = 0;
        for (int i = 0; i < stepCount - 1; i++){
            double x = bottomEdge + step * i;
            double Si = step*calculator.func(x);
            square += Si;
        }
        return square;
    }

    public double adaptiveIntegration(Calculator calculator, double bottomEdge, double topEdge, double accuracy) {
        double square = 0;
        double temp;
        int stepCount = 2;
        do{
            temp = square;
            square = rectangleIntegration(calculator, bottomEdge, topEdge, stepCount);
            stepCount*=2;
        }
        while (Math.abs(temp - square) > accuracy);
        return square;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        Main integral = new Main();
        double bottomEdge = scanner.nextDouble();
        double topEdge = scanner.nextDouble();
        double accuracy = scanner.nextDouble();
        System.out.println(integral.adaptiveIntegration(calculator, bottomEdge, topEdge, accuracy));
    }
}
