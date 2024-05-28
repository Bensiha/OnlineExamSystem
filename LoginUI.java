package view;

import model.Question;
import model.User;
import service.AuthService;
import service.ExamService;

import java.util.List;
import java.util.Scanner;

public class LoginUI {
    private AuthService authService;
    private ExamService examService;

    public LoginUI(ExamService examService) {
        this.authService = new AuthService();
        this.examService = examService;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = authService.authenticate(username, password);

            if (user != null) {
                System.out.println("Login successful! Welcome " + user.getUsername());

                // Directly display questions for student
                int totalScore = displayQuestionsAndCalculateScore(scanner);

                // Display total score
                System.out.println("You have completed the exam.");
                System.out.println("Your total score is: " + totalScore + " out of " + examService.getQuestions().size());

                // Prompt to continue or exit
                System.out.print("Do you want to exit? (yes/no): ");
                String exitChoice = scanner.nextLine();
                if (exitChoice.equalsIgnoreCase("yes")) {
                    running = false;
                }
            } else {
                System.out.println("Invalid username or password.");
                System.out.print("Do you want to try again? (yes/no): ");
                String tryAgainChoice = scanner.nextLine();
                if (tryAgainChoice.equalsIgnoreCase("no")) {
                    running = false;
                }
            }
        }
        System.out.println("Exiting the program. Goodbye!");
    }

    private int displayQuestionsAndCalculateScore(Scanner scanner) {
        List<Question> questions = examService.getQuestions();
        int score = 0;

        if (questions.isEmpty()) {
            System.out.println("No questions available.");
        } else {
            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
                System.out.println("a) " + question.getOptions()[0]);
                System.out.println("b) " + question.getOptions()[1]);
                System.out.println("c) " + question.getOptions()[2]);
                System.out.println("d) " + question.getOptions()[3]);
                System.out.print("Your answer: ");
                String answer = scanner.nextLine();

                // Check if the answer is correct
                int answerIndex = -1;
                if (answer.equalsIgnoreCase("a")) {
                    answerIndex = 0;
                } else if (answer.equalsIgnoreCase("b")) {
                    answerIndex = 1;
                } else if (answer.equalsIgnoreCase("c")) {
                    answerIndex = 2;
                } else if (answer.equalsIgnoreCase("d")) {
                    answerIndex = 3;
                }

                if (answerIndex == question.getCorrectOptionIndex()) {
                    score++;
                }

                System.out.println();
            }
        }
        return score;
    }
}

