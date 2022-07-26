import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Input:");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        try {
            String output = Main.calc(str);
            System.out.println("Output:\n" + output);
        } catch (Exception err) {
            System.out.println("Output:\n" + err.getMessage());
            System.out.println("Завершение программы");
        }
    }
}
