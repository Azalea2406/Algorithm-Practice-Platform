import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Problem> problems = ProblemLoader.loadProblems();

        System.out.println("Welcome to the Algorithm Practice Platform!");
        System.out.println("Follow the given Instructions carefully:");
        System.out.println("While writing the program give the main class name as UserSolution");
        System.out.println("You won't be able to make any changes once you enter a line, so check for any errors beforehand");

        // Main menu loop
        while (true) {
            System.out.println("\nChoose a problem to solve:");

            // List problems
            for (Problem problem : problems) {
                System.out.println(problem.getProblemId() + ". " + problem.getProblemName());
            }

            System.out.print("Enter the problem ID to solve (or 0 to exit): ");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }

            // Get the selected problem
            Problem selectedProblem = problems.stream()
                .filter(problem -> problem.getProblemId() == choice)
                .findFirst()
                .orElse(null);

            if (selectedProblem != null) {
                System.out.println("\nProblem: " + selectedProblem.getProblemName());
                System.out.println("Description: " + selectedProblem.getDescription());
                System.out.println("Input Format: " + selectedProblem.getInputFormat());
                System.out.println("Output Format: " + selectedProblem.getOutputFormat());

                // Allow the user to input their solution
                System.out.println("\nEnter your solution (type 'EOD' to end input):");
                StringBuilder userCode = new StringBuilder();

                // Reading user code until 'EOD' is encountered
                scanner.nextLine();  // Consume newline left by nextInt
                while (true) {
                    String line = scanner.nextLine();
                    if (line.equalsIgnoreCase("EOD")) {
                        break;
                    }
                    userCode.append(line).append("\n");
                }

                // Check if the user has left the file empty
                if (userCode.toString().trim().isEmpty()) {
                    System.out.println("Error: Your solution is empty. Please provide your code.");
                    continue;  // Ask the user to try again
                }

                // Write the user's solution to a file
                String fileName = "UserSolution.java";
                TestCaseEvaluator.writeUserSolutionToFile(userCode.toString(), fileName);

                // Compile and run the test cases
                if (TestCaseEvaluator.compileSolution(fileName)) {
                    String className = fileName.substring(0, fileName.lastIndexOf("."));
                    // Evaluate the solution by running the test cases
                    TestCaseEvaluator.runTestCases(className, selectedProblem.getTestCases());
                }

            } else {
                System.out.println("Problem not found.");
            }
            
            // After completing the problem, the loop continues to the next iteration
            // This will show the menu again and ask the user to select another problem.
        }

        scanner.close();
    }
}
