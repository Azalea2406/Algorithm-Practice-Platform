public class Problem {
    private int problemId;
    private String problemName;
    private String description;
    private String inputFormat;
    private String outputFormat;
    private String testCases;

    //Constructor
    public Problem(int problemId, String problemName, String description, String inputFormat, String outputFormat, String testCases){
    this.problemId = problemId;
    this.problemName = problemName;
    this.description = description;
    this.inputFormat = inputFormat;
    this.outputFormat = outputFormat;
    this.testCases = testCases;
    }

    //Getters and Setters
    public int getProblemId() { return problemId; }
    public String getProblemName() { return problemName; }
    public String getDescription() { return description; }
    public String getInputFormat() { return inputFormat; }
    public String getOutputFormat() { return outputFormat; }
    public String getTestCases() { return testCases; }
}

