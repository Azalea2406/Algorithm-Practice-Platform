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

        long startTime = System.nanoTime();
        Process process = processBuilder.start();

        // Send input to the program
        if (input != null && !input.isEmpty()) {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(process.getOutputStream()));
            writer.write(input);
            writer.newLine();
            writer.flush();
            writer.close();
        }

        // Time limit: 2 seconds
        boolean finished = process.waitFor(2, java.util.concurrent.TimeUnit.SECONDS);
        if (!finished) {
            process.destroy();
            return "TLE";
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        StringBuilder outputBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            outputBuilder.append(line).append("\n");
        }

        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;

        output = outputBuilder.toString().trim();
        System.out.println("Execution Time: " + durationMs + " ms");

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

        if (userOutput.equals("TLE")) {
            System.out.println("[TLE] Time Limit Exceeded for input: " + (input == null ? "No Input" : input));
            allTestsPassed = false;
        }
        // Check correctness
        else if (userOutput.equals(expectedOutput)) {
            System.out.println("[AC] Test passed for input: " + (input == null ? "No Input" : input));
        } else {
            System.out.println("[WA] Test failed for input: " + (input == null ? "No Input" : input));
            System.out.println("Expected: " + expectedOutput + " | Got: " + userOutput);
            allTestsPassed = false;
        }
    }

    if (allTestsPassed) {
        System.out.println("\nAll test cases passed successfully!");
    } else {
        System.out.println("\nSome test cases failed.");
    }
}
}
