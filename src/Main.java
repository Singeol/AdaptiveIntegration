import java.util.Scanner;

public class Main {
    // Функция для вычисления интеграла функции с помощью метода прямоугольников на заданном интервале
    public double rectangleIntegration(Calculator calculator, double bottomEdge, double topEdge, int stepCount) {
        // Шаг разбиения интервала на подотрезки
        double step = (topEdge - bottomEdge) / stepCount;
        // Переменная для хранения значения интеграла на текущем шаге
        double square = 0;
        // Цикл, выполняющий вычисления на каждом подотрезке и суммирующий значения
        for (int i = 0; i < stepCount - 1; i++){
            // Вычисление значения аргумента на текущем подотрезке
            double x = bottomEdge + step * i;
            // Вычисление площади прямоугольника на текущем подотрезке
            double Si = step*calculator.func(x);
            // Добавление площади прямоугольника на текущем подотрезке к общей площади
            square += Si;
        }
        // Возврат значения интеграла на заданном интервале
        return square;
    }

    // Функция для вычисления интеграла функции с помощью метода прямоугольников с заданной точностью
    public double adaptiveIntegration(Calculator calculator, double bottomEdge, double topEdge, double accuracy) {
        // Переменная для хранения значения интеграла на предыдущей итерации
        double temp;
        // Начальное количество подотрезков
        int stepCount = 2;
        // Переменная для хранения значения интеграла на текущем шаге
        double square = 0;
        // Цикл, выполняющий вычисления на каждой итерации с увеличением количества подотрезков в два раза
        do{
            // Сохранение значения интеграла на предыдущей итерации
            temp = square;
            // Вычисление значения интеграла на текущей итерации с помощью функции rectangleIntegration
            square = rectangleIntegration(calculator, bottomEdge, topEdge, stepCount);
            // Увеличение количества подотрезков в два раза для следующей итерации
            stepCount*=2;
        }
        // Повторение цикла, пока разница между значением интеграла на текущей и предыдущей итерациях больше заданной точности
        while (Math.abs(temp - square) > accuracy);
        // Возврат значения интеграла с заданной точностью
        return square;
    }

    public static void main(String[] args) {
        // Создание объектов сканнера, калькулятора и главного класса
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        Main integral = new Main();
        // Получение границы нижнего интеграла, верхнего интеграла и требуемую точность
        System.out.print("Введите нижнюю границу интегрирования: ");
        double bottomEdge = scanner.nextDouble();
        System.out.print("Введите верхнюю границу интегрирования: ");
        double topEdge = scanner.nextDouble();
        System.out.print("Введите требуемую точность: ");
        double accuracy = scanner.nextDouble();
        // Вывод результата интегрирования на экран
        System.out.println("Результат интегрирования: " + integral.adaptiveIntegration(calculator, bottomEdge, topEdge, accuracy));
    }
}
