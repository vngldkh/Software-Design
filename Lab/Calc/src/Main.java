import entities.Calculator;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Calculator calc = new Calculator();
        Scanner scn = new Scanner(System.in);
        System.out.println("Greetings to weird programmable calculator!");
        boolean flag;
        do {
            System.out.println("Write \"conf\" to configure custom buttons or anything else to start calculation.");
            String input = scn.nextLine();
            flag = "conf".equals(input);
            if (flag) {
                System.out.println("Write the character you want to reassign:");
                char ch = scn.nextLine().charAt(0);
                calc.assign(ch);
            }
        } while (flag);

        while (true) {
            System.out.println("Next operation:");
            char ch = scn.nextLine().charAt(0);
            calc.run(ch);
        }
    }
}