package service;

import model.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExamService {
    private List<Question> questions;

    public ExamService() {
        questions = new ArrayList<>();
    }

    // Load questions from file
    public void loadQuestionsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String questionText = line;
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options[i] = br.readLine();
                }
                int correctOptionIndex = Integer.parseInt(br.readLine());
                questions.add(new Question(questionText, options, correctOptionIndex));
                br.readLine(); // Read empty line separating questions
            }
        } catch (IOException e) {
            System.err.println("Error reading the questions file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error in the format of the correct option index: " + e.getMessage());
        }
    }

    // Method to get questions
    public List<Question> getQuestions() {
        return questions;
    }
}
