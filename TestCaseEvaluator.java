import java.io.*;

public class TestCaseEvaluator {

    //Write the user's solution to a file "UserSolution.java"
    public static void writeUserSolutionToFile(String userCode, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(userCode);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
 
    // Compile the user's solution
    public static boolean compileSolution(String fileName) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("javac", fileName);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            
            if (exitCode == 0) {
                System.out.println("Compilation successful.");
                return true;
            } else {
                System.out.println("Compilation failed.");
                return false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Run the user's solution
    public static String runSolution(String className, String input) {
        String output = "";
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("java", className);
            processBuilder.directory(new File(".")); 

            // Start the process
            Process process = processBuilder.start();
            
            if (input != null && !input.isEmpty()) {
                OutputStream os = process.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
                writer.write(input);
                writer.flush();
                writer.close();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line).append("\n");
            }
            output = outputBuilder.toString().trim();

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }

    // run test cases
    public static void runTestCases(String className, String testCases) {
        String[] testCasesArray = testCases.split(";");
        boolean allTestsPassed = true;

        for (String testCase : testCasesArray) {
            String[] testCaseParts = testCase.split("->");
            String input = testCaseParts.length == 2 ? testCaseParts[0].trim() : null;
            String expectedOutput = testCaseParts.length == 2 ? testCaseParts[1].trim() : testCaseParts[0].trim();

            // Run the user solution 
            String userOutput = runSolution(className, input);

            // Check if the output matches the expected output
            if (userOutput.equals(expectedOutput)) {
                System.out.println("Test passed for the given input " + (input == null ? "No Input" : input));
            } else {
                System.out.println("Test failed for the given input " + (input == null ? "No Input" : input));
                System.out.println("Expected: " + expectedOutput + " | Got: " + userOutput);
                allTestsPassed = false;
            }
        }

        if (allTestsPassed) {
            System.out.println("All test cases passed!");
        } else {
            System.out.println("Some test cases failed.");
        }
    }
}
