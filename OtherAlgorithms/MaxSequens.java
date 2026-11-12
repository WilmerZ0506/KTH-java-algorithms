import java.util.HashSet;
import java.util.Scanner;

public class MaxSequens {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Initiate scanner
        String input = scanner.nextLine(); //Input
        scanner.close();

        String[] numbers = input.split(" "); //Numbers
        HashSet<String> seenCharacters = new HashSet<>(); //SeenCharacters
        int maxLength = 0; //Initiate maxLength
        int a = 0; //Initiate pointer a

        for (int b = 0; b < numbers.length; b++) { //Iterate pointer b trough numbers
            while (seenCharacters.contains(numbers[b])) { //Check if character is seen
                seenCharacters.remove(numbers[a]); //Remove character from seenCharacters (sliding window)
                a++; //Move pointer a
            }
            seenCharacters.add(numbers[b]); //Add character to seenCharacters
            int currentLength = b - a + 1; //Current length
            if (currentLength > maxLength) {
                maxLength = currentLength; //Update maxLength
            }
        }
        System.out.println(maxLength);
    }
}