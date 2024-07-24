import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
public class Chatbot {
    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm your chatbot. How can I assist you today?");
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().toLowerCase();

            if (input.contains("open application")) {
                openApplication(input);
            } else if (input.contains("search for")) {
                searchWeb(input);
            } else if (input.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Sorry, I didn't understand that command.");
            }
        }
        
        scanner.close();
    }

    private static void openApplication(String input) {
        String application = input.replace("open application ", "").trim();
        
        try {
            if (application.equals("notepad")) {
                Runtime.getRuntime().exec("notepad");
            } else if (application.equals("calculator")) {
                Runtime.getRuntime().exec("calc");
            } else if(application.equals("mspaint")){
              	Runtime.getRuntime().exec("mspaint");
              	
            } else {
                System.out.println("Application not recognized. Please try again.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchWeb(String input) {
        String query = input.replace("search for ", "").trim();
        
        try {
            Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + query.replace(" ", "+")));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}