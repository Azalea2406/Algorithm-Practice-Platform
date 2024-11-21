import java.io.*;
import java.util.*;

public class ProblemLoader {

    public static List<Problem> loadProblems() {
        List<Problem> problems = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Programs.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int problemId = Integer.parseInt(values[0].trim());
                String problemName = values[1].trim();
                String description = values[2].trim();
                String inputFormat = values[3].trim();
                String outputFormat = values[4].trim();
                String testCases = values[5].trim();
                problems.add(new Problem(problemId, problemName, description, inputFormat, outputFormat, testCases));
            }
        } catch (IOException e) {
            System.out.println("Error reading problems file.");
        }
        return problems;
    }
}
