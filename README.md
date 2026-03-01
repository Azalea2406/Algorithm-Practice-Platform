# Algorithm Practice Platform (Java)

A command-line based mini online judge system built in Java that allows users to solve algorithmic problems, compile their solutions dynamically, and evaluate them against predefined test cases.

---

## 🚀 Features

- Load problems dynamically from a CSV file  
- Accept user-written Java solutions at runtime  
- Compile and execute user code automatically  
- Evaluate solutions against multiple test cases  
- Generate verdicts:
  - AC (Accepted)  
  - WA (Wrong Answer)  
  - TLE (Time Limit Exceeded)  
- Measure and display execution time for performance analysis  
- Modular design for easy extension of new problems  

---

## 🛠 Tech Stack

- Java  
- File Handling (CSV)  
- ProcessBuilder for dynamic compilation and execution  

---

## 📂 Project Structure

Main.java               -> Entry point and user interface  
Problem.java            -> Problem data model  
ProblemLoader.java      -> Loads problems from CSV file  
TestCaseEvaluator.java  -> Compiles, runs, and evaluates solutions  
Programs.csv            -> Problem bank with test cases  
UserSolution.java       -> User-submitted solution (generated dynamically)  

---

## 📥 Test Case Format

Test cases are stored in the following format in the CSV file:

input -> output ; input -> output  

Example:  
3 5 -> 8 ; 10 20 -> 30  

---

## ▶ How to Run

1. Clone the repository: git clone https://github.com/Aalea2406/algorithm-practice-platform.git
2. Compile the project: javac Main.java
3. Run the application: java Main
4. Select a problem and enter your Java solution.  
   Type `EOD` to finish entering your code.

---

## 🎯 Learning Outcomes

- Improved understanding of Data Structures and Algorithms  
- Experience building an execution and validation engine  
- Practical exposure to dynamic code compilation  
- Strengthened object-oriented design skills  
- Learned performance measurement and constraint handling  

---

## 📌 Future Enhancements

- Support for multiple programming languages  
- Web-based interface  
- Memory usage tracking  
- Problem difficulty classification  
- User performance analytics  

