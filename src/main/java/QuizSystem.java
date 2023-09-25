import java.util.*;

public class QuizSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("System:> Enter your username");
            String username = scanner.nextLine();
            System.out.println("System:> Enter password");
            String password = scanner.nextLine();

            if ("admin".equals(username) && "1234".equals(password)) {
                adminActions(scanner);
            } else if ("student".equals(username) && "1234".equals(password)) {
                studentActions(scanner);
            } else {
                System.out.println("System:> Invalid username or password. ");
            }
        }
    }

    private static void adminActions(Scanner scanner) {
        List<Question> quizBank = new ArrayList<>();
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("System:> Welcome admin! Please create new questions.");

        while (true) {
            System.out.print("Admin:> Input your question\nSystem:>");
            String question = inputScanner.nextLine();

            String[] options = new String[4];
            for (int i = 0; i < 4; i++) {
                System.out.print("System: Input option " + (i + 1) + ":\nAdmin:>");
                options[i] = inputScanner.nextLine();
            }

            System.out.print("System: What is the answer key?\nAdmin:>");
            int answerKey = Integer.parseInt(inputScanner.nextLine());

            Question newQuestion = new Question(question, options, answerKey);
            quizBank.add(newQuestion);

            System.out.println("System:> Saved successfully! Do you want to add more questions? (press 's' for start and 'q' for quit)");
            String action = inputScanner.nextLine();

            if ("q".equalsIgnoreCase(action)) {
                break;
            }
        }
    }

    private static void studentActions(Scanner scanner) {
        List<Question> quizBank = createQuizBank();

        System.out.println("System:> Welcome to the quiz! There are 10 questions. Each MCQ mark is 1 . \nAre you ready? Press 's' to start.");
        String start = scanner.nextLine();

        if ("s".equalsIgnoreCase(start)) {
            List<Question> questionsToAsk = new ArrayList<>(quizBank);
            Collections.shuffle(questionsToAsk);

            int score = 0;

            for (int i = 0; i < 10 && !questionsToAsk.isEmpty(); i++) {
                Question question = questionsToAsk.remove(0);

                System.out.println("\n[Question " + (i + 1) + "] " + question.question);
                for (int j = 0; j < 4; j++) {
                    System.out.println((j + 1) + ". " + question.options[j]);
                }

                System.out.print("\nStudent:>");
                try {
                    int userAnswer = Integer.parseInt(scanner.nextLine());
                    if (userAnswer == question.answerKey) {
                        score++;
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid input
                }
            }

            System.out.println("\nQuiz completed! Your score is " + score + " out of 10.");

            if (score >= 8) {
                System.out.println("Excellent! You have done well.");
            } else if (score >= 5) {
                System.out.println("Good. Keep it up.");
            } else if (score >= 2) {
                System.out.println("Very poor! You need to improve.");
            } else {
                System.out.println("Very sorry, you failed. Better luck next time.");
            }
        }
    }

    private static List<Question> createQuizBank() {
        List<Question> quizBank = new ArrayList<>();

        // Add sample questions to the quiz bank
        quizBank.add(new Question("Which is not part of system testing?", new String[]
                {"Regression Testing", "Sanity Testing", "Load Testing", "Unit Testing"}, 4));
        quizBank.add(new Question("Which is whitebox testing technique?", new String[]
                {"Equivalent Partitioning", "Boundary value testing", "Decision table testing", "Adhoc Testing"}, 3));
        quizBank.add(new Question("What is the purpose of a Test Case in software testing??", new String[]
                {"To document requirements", "To track defects", "To specify test inputs and expected outcomes", "To manage project schedules"}, 2));
        quizBank.add(new Question("Which testing technique is primarily focused on assessing how well a software system handles a high volume of data?", new String[]
                {"Functional testing", "Performance testing", "Usability testing", "Security testing"}, 1));
        quizBank.add(new Question("Which type of testing is used to validate that the individual components of a software system work correctly together as a whole?", new String[]
                {"Unit testing", "Integration testing", "System testing", "Regression testing"}, 1));
        quizBank.add(new Question("What is the primary goal of usability testing in software quality assurance?", new String[]
                {"To ensure the software is free of defects", "To evaluate the software's user interface and user experience", "To check for security vulnerabilities", "To verify compliance with industry standards"}, 2));
        quizBank.add(new Question("Which type of testing is typically performed by external testers who have no prior knowledge of the software's design or implementation?", new String[]
                {"Alpha testing", "Beta testing", "White-box testing", "Black-box testing"}, 3));
        quizBank.add(new Question("Which phase of the Software Development Life Cycle (SDLC) is the most appropriate for performing code reviews and static analysis??", new String[]
                {"Requirements gathering", "Design", "Implementation", "Maintenance"}, 2));
        quizBank.add(new Question("What is the primary purpose of a Test Plan in software testing?", new String[]
                {"To document test cases", "To manage project risks", "To provide a high-level overview of the testing process", "To define the scope and objectives of testing"}, 3));
        quizBank.add(new Question("Which type of testing focuses on verifying that the software behaves correctly after a change or enhancement has been made?", new String[]
                {"Smoke testing", "Regression testing", "Acceptance testing", "Alpha testing"}, 1));

        return quizBank;
    }
}

class Question {
    String question;
    String[] options;
    int answerKey;

    public Question(String question, String[] options, int answerKey) {
        this.question = question;
        this.options = options;
        this.answerKey = answerKey;
    }
}
