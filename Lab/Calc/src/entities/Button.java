package entities;

import operations.Operation;
import operations.custom.AreEqual;
import operations.custom.Decrement;
import operations.custom.Increment;
import operations.custom.Mod;

import java.util.Scanner;

public class Button {
    private final char key;
    private final Operation operation;

    public Button(char key, Operation operation) {
        this.key = key;
        this.operation = operation;
    }

    public char getKey() {
        return key;
    }

    public void run() {
        operation.execute();
    }

    public static Button assign(char key, Counter counter) {
        System.out.println("Choose a custom operation to assign it to the key:");
        System.out.println("1. Mod (the remainder of the division).");
        System.out.println("2. Increment (increases the result by 1).");
        System.out.println("3. Decrement (decreases the result by 1).");
        System.out.println("4. Are equal (change the result to 1 if it's equal to the operand and to 0 otherwise).");
        Scanner scn = new Scanner(System.in);
        boolean flag;
        int input;
        do {
            System.out.print(">> ");
            input = scn.nextInt();
            flag = input >= 1 && input <= 4;
        } while (!flag);
        Operation operation = switch (input) {
            case 1 -> new Mod(counter);
            case 2 -> new Increment(counter);
            case 3 -> new Decrement(counter);
            case 4 -> new AreEqual(counter);
            default -> null;
        };
        return new Button(key, operation);
    }
}
