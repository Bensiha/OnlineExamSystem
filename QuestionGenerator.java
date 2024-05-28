package util;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionGenerator {
    private static final String[] questions = {
        "What is the capital city of France?",
        "Which planet is closest to the sun?",
        "Who is the author of 'To Kill a Mockingbird'?",
        "What is the chemical symbol for gold?",
        "What is the tallest mammal?",
        "What is the largest ocean on Earth?",
        "What is the powerhouse of the cell?",
        "Which famous scientist discovered gravity?",
        "What is the square root of 64?",
        "Who painted the famous artwork 'Starry Night'?"
    };

    private static final String[][] options = {
        {"Paris", "Berlin", "Madrid", "Rome"},
        {"Mercury", "Venus", "Earth", "Mars"},
        {"Harper Lee", "J.K. Rowling", "Stephen King", "Ernest Hemingway"},
        {"Au", "Ag", "Cu", "Fe"},
        {"Giraffe", "Elephant", "Rhino", "Hippo"},
        {"Pacific", "Atlantic", "Indian", "Arctic"},
        {"Mitochondria", "Nucleus", "Ribosome", "Lysosome"},
        {"Isaac Newton", "Albert Einstein", "Galileo Galilei", "Nikola Tesla"},
        {"8", "6", "4", "12"},
        {"Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo Buonarroti"}
    };

    public static List<String> generateUniqueQuestions(int numQuestions) {
        if (numQuestions > questions.length) {
            throw new IllegalArgumentException("Number of questions requested exceeds available questions.");
        }

        List<String> selectedQuestions = new ArrayList<>();
        Random random = new Random();

        // Select unique questions randomly
        while (selectedQuestions.size() < numQuestions) {
            int index = random.nextInt(questions.length);
            if (!selectedQuestions.contains(questions[index])) {
                selectedQuestions.add(questions[index]);
            }
        }

        return selectedQuestions;
    }

    public static void main(String[] args) {
        // Generate 10 unique questions
        List<String> uniqueQuestions = generateUniqueQuestions(10);
        for (String question : uniqueQuestions) {
            System.out.println(question);
        }
    }
}
