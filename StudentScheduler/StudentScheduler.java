import java.util.Scanner;
import java.util.ArrayList;

public class StudentScheduler {

    static class StudentData implements Comparable<StudentData> { //StudentData class implementing Comparable
        String finishedDate; //Students finishdate
        String lastName; //Students lastname
        ArrayList<String> firstNames; //Array of students firstnames

        public StudentData(String line) {
            String[] parts = line.split(" "); //Split line at space
            this.finishedDate = parts[parts.length - 1]; //Finnishdate at index -1
            this.lastName = parts[parts.length -2]; //Lastname at index -2
            this.firstNames = new ArrayList<>(); //Initiate firstname array
            for (int i = 0; i < parts.length - 2; i++) { //For number of firstnames
                this.firstNames.add(parts[i]); //Add to name array
            }
        }

        @Override
        public int compareTo(StudentData other) {
            int comparison = this.finishedDate.compareTo(other.finishedDate); //Compare date
            if (comparison != 0) return comparison; //Return if date different

            comparison = this.lastName.compareTo(other.lastName); //Compare lastname
            if (comparison != 0) return comparison; //Return if lastname different

            int minimumSize = Math.min(this.firstNames.size(), other.firstNames.size()); //Calculate lenght of shortest name array
            for (int i = 0; i < minimumSize; i++) { //For number in shortest name array
                comparison = this.firstNames.get(i).compareTo(other.firstNames.get(i)); //Compare first names
                if (comparison != 0) return comparison; //Return if first names different
            }
            return Integer.compare(this.firstNames.size(), other.firstNames.size()); //Compare and return the difference in number of firtst names
        }
    }

    public static ArrayList<StudentData> readStudentData(Scanner scanner) {
        ArrayList<StudentData> students = new ArrayList<>(); //Initiate array of students
        while (scanner.hasNextLine()) { //While nextline
            String line = scanner.nextLine().trim(); //Read and trim line
            if (line.isEmpty()) break; //Do not add empty lines
            students.add(new StudentData(line)); //Add line
        }
        students.sort(null); //Sort student array
        return students; //Return student array
    }

    public static String getPresentationDate(String currentDate) {
        int year = Integer.parseInt(currentDate.substring(0, 4)); //Get year as integer
        int month = Integer.parseInt(currentDate.substring(4, 6)); //Get month as integer
        int day = Integer.parseInt(currentDate.substring(6, 8)); //Get day as integer

        day++; //Add one day (earliest is day after completion)

        if (1 < day && day < 15) { //If day between 1 and 15
            day = 15; //Set day to 15
        }

        if (day > 15) { //If day over 15
            day = 1; //Set day to 1
            month++; //Increment month
            if (month > 12) { //If month over 12
                month = 1; //Set month to 1
                year++; //Increment year
            }
        }
        return String.format("%04d%02d%02d", year, month, day); //Return date string (formatted YYYYMMDD)
    }
    
    public static void printStudent(StudentData student) {
        System.out.println(String.join(" ", student.firstNames) + " " + student.lastName); //Print student as <firstname> <name2 - nameN> <lastname>
    }

    public static void printStudentData(ArrayList<StudentData> students) {
        String currentDate = ""; //Initiate current date
        int groupCount = 0; //Initiate counter for group members
        StudentData previousStudent = null; //Initiate previous student

        for (StudentData currentStudent : students) {
            String presentationDate = getPresentationDate(currentStudent.finishedDate); //Get student's presentation date

            if (currentDate.equals("") || presentationDate.compareTo(currentDate) > 0) { //If start or sudent's presentation date later than current date
                currentDate = presentationDate; //Update current date to student's presentation date
                groupCount = 0; //Reset counter
                System.out.println(currentDate); //Print current date
            }

            if (groupCount >= 5) { //If group has 5 or more members
                boolean sameGroup = (previousStudent != null && currentStudent.finishedDate.equals(previousStudent.finishedDate) && currentStudent.lastName.equals(previousStudent.lastName)); //Boolean for same finish date and lastname

                if (!sameGroup) { //If not same finish date and last name
                    currentDate = getPresentationDate(currentDate); //Get next available presentation date
                    groupCount = 0; //Reset counter
                    System.out.println(currentDate); //Print current date
                }
            }
            printStudent(currentStudent); //Print student
            groupCount++; //Increment group members
            previousStudent = currentStudent; //Update previous student
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Initiate scanner
        ArrayList<StudentData> students = readStudentData(scanner); //Read input
        printStudentData(students); //Print output
        scanner.close(); //Close scanner
    }

}
