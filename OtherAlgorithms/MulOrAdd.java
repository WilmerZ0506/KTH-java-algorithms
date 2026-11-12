import java.util.Scanner;

public class MulOrAdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Initiate scanner
        String[] integerStrings = scanner.nextLine().split(" "); //Split input

        int[] numbers = new int[integerStrings.length]; //Initiate numbers array
        for (int i = 0; i < integerStrings.length; i++) { //For every number in numbers array
            numbers[i] = Integer.parseInt(integerStrings[i]); //Convert to int
        }

        long addFirst = evaluate(numbers, true); // + * + * ...
        long mulFirst = evaluate(numbers, false); // * + * + ...

        System.out.println(Math.max(addFirst, mulFirst)); //Print max
        scanner.close();
    }

    private static long evaluate(int[] numbers, boolean addFirst) {
        long sum = 0; //Initiate sum
        long currentProduct = numbers[0]; //Initiate current product

        for (int i = 0; i < numbers.length - 1; i++) { //Iterate through numbers
            char operator; //Initiate operator
            if (addFirst) { // Addition first
                operator = (i % 2 == 0) ? '+' : '*'; // Set operator based on position
            } else { // multiplication first
                operator = (i % 2 == 0) ? '*' : '+'; // Set operator based on position
            }

            int next = numbers[i + 1]; //Initiate next

            if (operator == '*') {
                currentProduct *= next; // Multiplication
            } else {
                sum += currentProduct; // Addition
                currentProduct = next; // Update current product
            }
        }

        sum += currentProduct; // Add last product to sum
        return sum;
    }
}
