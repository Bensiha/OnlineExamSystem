package view;

import model.Question;
import service.ExamService;

import java.util.List;
import java.util.Scanner;

public class ExamUI {
    private ExamService examService;

    public ExamUI(ExamService examService) {
        this.examService = examService;
    }

    public void startExam() {
        List<Question> questions = examService.getQuestions();
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            int answer = getValidAnswer(scanner, options.length);
            if (answer - 1 == question.getCorrectOptionIndex()) {
                score++;
            }
        }

        System.out.println("Your score: " + score + "/" + questions.size());
    }

    private int getValidAnswer(Scanner scanner, int numberOfOptions) {
        int answer = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print("Your answer: ");
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                if (answer > 0 && answer <= numberOfOptions) {
                    valid = true;
                } else {
                    System.out.println("Please enter a number between 1 and " + numberOfOptions);
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear the invalid input
            }
        }

        return answer;
    }
}

