import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    private static String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "What is the largest ocean on Earth?",
            "Who wrote 'To Kill a Mockingbird'?",
            "What is the chemical symbol for gold?"
    };

    private static String[][] options = {
            {"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"},
            {"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"},
            {"1. Atlantic", "2. Indian", "3. Arctic", "4. Pacific"},
            {"1. Harper Lee", "2. J.K. Rowling", "3. Ernest Hemingway", "4. Mark Twain"},
            {"1. Au", "2. Ag", "3. Pt", "4. Pb"}
    };

    private static int[] correctAnswers = {3, 2, 4, 1, 1};
    private static int[] userAnswers = new int[questions.length];
    private static int score = 0;
    private static int timeLimit = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            int finalI = i;
            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("\nTime's up for question " + (finalI + 1) + "!");
                    if (userAnswers[finalI] == 0) {
                        userAnswers[finalI] = -1;
                        System.out.println("Moving to the next question...\n");
                        askNextQuestion(finalI + 1);
                    }
                    timer.cancel();
                }
            };

            timer.schedule(task, timeLimit * 1000);


            System.out.print("Your answer (1-4): ");
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < timeLimit * 1000) {
                if (scanner.hasNextInt()) {
                    int answer = scanner.nextInt();
                    if (answer >= 1 && answer <= 4) {
                        userAnswers[i] = answer;
                        break;
                    } else {
                        System.out.print("Invalid input. Please enter a number between 1 and 4: ");
                    }
                }
            }

            if (userAnswers[i] == 0) {
                System.out.println("\nNo valid answer recorded for question " + (i + 1) + ".");
                userAnswers[i] = -1;
                askNextQuestion(i + 1);
            }


            timer.cancel();


            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }


            System.out.println("\nMoving to the next question...\n");
        }


        System.out.println("\nQuiz finished!");
        System.out.println("Your score: " + score + "/" + questions.length);


        System.out.println("\nSummary of Correct Answers:");
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.println("Correct Answer: " + options[i][correctAnswers[i] - 1]);
            System.out.println("Your Answer: " + (userAnswers[i] == -1 ? "No answer" : options[i][userAnswers[i] - 1]));
        }

        scanner.close();
    }


    private static void askNextQuestion(int questionIndex) {
        if (questionIndex < questions.length) {
            System.out.println("Next question:");
            System.out.println(questions[questionIndex]);
            for (String option : options[questionIndex]) {
                System.out.println(option);
            }
 }
}
}
