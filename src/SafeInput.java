import java.util.Scanner;

public class SafeInput {

    public static int getRangedInt(Scanner console, String prompt,
                                   int low, int high) {
        while (true) {
            System.out.print(prompt + " [" + low + "-" + high + "]: ");

            if (console.hasNextInt()) {
                int value = console.nextInt();
                console.nextLine();

                if (value >= low && value <= high) {
                    return value;
                }

                System.out.println(
                        "Please enter a number between "
                                + low + " and " + high + ".");
            } else {
                System.out.println("Please enter a whole number.");
                console.nextLine();
            }
        }
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        while (true) {
            System.out.print(prompt + " [Y/N]: ");
            String response = console.nextLine().trim();

            if (response.equalsIgnoreCase("Y")) {
                return true;
            }

            if (response.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Please enter Y or N.");
        }
    }
}