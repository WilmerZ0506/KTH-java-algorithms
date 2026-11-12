import java.util.Scanner;

public class Morris {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Initiate scanner
        int N = scanner.nextInt(); //Input
        scanner.close();

        String current = "1"; //Iteration 1

        for (int i = 1; i < N; i++) {
            current = nextMorris(current); //Next iteration
        }

        System.out.println(current);
    }

    private static String nextMorris(String s) {
        StringBuilder result = new StringBuilder(); // StringBuilder to hold the next iteration

        int count = 1; //Count of current digit
        for (int i = 0; i < s.length(); i++) { //Iterate through string
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) { //Check next digit
                count++; //Increment count
            } else {
                result.append(count).append(s.charAt(i)); //Append count and digit
                count = 1; //Reset count
            }
        }

        return result.toString();
    }
}
