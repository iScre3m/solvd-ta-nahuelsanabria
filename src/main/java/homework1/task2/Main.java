package homework1.task2;

public class Main {
    public static void main(String[] args) {
        int[] numbers = new int[]{3, 7, 6, 13, 33, 9, -100, 25};
        int maxNumber = Integer.MIN_VALUE;
        int minNumber = Integer.MAX_VALUE;
        int i = 0;
        while (i < numbers.length) {
            System.out.println(numbers[i]);
            maxNumber = (numbers[i] > maxNumber) ? numbers[i] : maxNumber;
            minNumber = (numbers[i] < minNumber) ? numbers[i] : minNumber;
            i++;
        }
        System.out.println("maxNumber = " + maxNumber);
        System.out.println("minNumber = " + minNumber);
    }
}
