package view;
import service.ExamService;
import view.LoginUI;

public class Main {
    public static void main(String[] args) {
        // Initialize ExamService
        ExamService examService = new ExamService();

        // Load questions from file
        examService.loadQuestionsFromFile("D:\\OnlineExamSystem\\questions.txt");

        // Start LoginUI
        LoginUI loginUI = new LoginUI(examService);
        loginUI.display();
    }
}
