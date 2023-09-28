import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElizaMimic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eliza: Hello! How can I assist you today?");

        while (true) {
            String userMessage = scanner.nextLine();
            if (userMessage.equalsIgnoreCase("quit")) {
                System.out.println("Eliza: Goodbye!");
                break;
            }

            String elizaResponse = generateResponse(userMessage);
            System.out.println("Eliza: " + elizaResponse);
        }

        scanner.close();
    }

    public static String generateResponse(String userMessage) {
        String[] greetingPatterns = { ".*\\bhello\\b.*", ".*\\bhi\\b.*", ".*\\bhey\\b.*" };
        String[] farewellPatterns = { ".*\\bbye\\b.*", ".*\\bgoodbye\\b.*" };
        String[] responseGreetings = { "Hello!", "Hi there!", "How can I help you?" };
        String[] responseFarewells = { "Goodbye!", "Have a great day!", "See you later!" };

        Random random = new Random();

        // Check for farewell first, as it takes precedence.
        for (String pattern : farewellPatterns) {
            if (matchesPattern(userMessage, pattern)) {
                return responseFarewells[random.nextInt(responseFarewells.length)];
            }
        }

        // Check for greetings.
        for (String pattern : greetingPatterns) {
            if (matchesPattern(userMessage, pattern)) {
                return responseGreetings[random.nextInt(responseGreetings.length)];
            }
        }

        // If no special pattern is matched, generate a generic response.
        return generateGenericResponse();
    }

    public static boolean matchesPattern(String text, String pattern) {
        Pattern regexPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = regexPattern.matcher(text);
        return matcher.matches();
    }

    public static String generateGenericResponse() {
        String[] genericResponses = {
                "Interesting, tell me more.",
                "I see. Please go on.",
                "How does that make you feel?",
                "Can you elaborate on that?",
                "I'm here to listen. Please continue."
        };

        Random random = new Random();
        return genericResponses[random.nextInt(genericResponses.length)];
    }
}
