package homework1.task3;

public class Main {
    public static void main(String[] args) {
        int[] numbers = new int[]{3, 7, 6, 13, 33, 9, -100, 25};
        int sizeNumbers = numbers.length;
        for (int i = 0; i < sizeNumbers - 1; i++) {

            int minNumber = i;

            for (int j = i + 1; j < sizeNumbers; j++) {

                if (numbers[j] < numbers[minNumber])
                    minNumber = j;
            }

            int tempNumber = numbers[minNumber];
            numbers[minNumber] = numbers[i];
            numbers[i] = tempNumber;
        }

        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }
}