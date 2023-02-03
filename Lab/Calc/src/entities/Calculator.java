package entities;

import operations.*;
import operations.arithmetic.*;

import java.util.ArrayList;

public class Calculator {
    private final Counter counter;
    private final Button[] numericButtons;
    private final Button[] arithmeticButtons;
    private final ArrayList<Button> customButtons;

    public Calculator() {
         counter = new Counter();

        numericButtons = new Button[10];
        for (int i = 0; i < 10; ++i) {
            numericButtons[i] = new Button((char) ('0' + i), new NumericOperation(counter, i));
        }

        arithmeticButtons = new Button[5];
        arithmeticButtons[0] = new Button('+', new Plus(counter));
        arithmeticButtons[1] = new Button('-', new Minus(counter));
        arithmeticButtons[2] = new Button('*', new Multiplication(counter));
        arithmeticButtons[3] = new Button('/', new Division(counter));
        arithmeticButtons[4] = new Button('=', new Equals(counter));

        customButtons = new ArrayList<>();
    }

    public void assign(char key) {
        if (key - '0' >= 0 && key - '0' <= 9 ||
            key == '+' || key == '-' || key == '*' || key == '/' || key == '=') {
            System.out.println("This button can't be reassigned!");
            return;
        }
        for (int i = 0; i < customButtons.size(); ++i) {
            if (customButtons.get(i).getKey() == key) {
                customButtons.set(i, Button.assign(key, counter));
                break;
            }
        }
        customButtons.add(Button.assign(key, counter));
    }

    public void run(char key) {
        if (key - '0' >= 0 && key - '0' <= 9) {
            numericButtons[key - '0'].run();
            return;
        }

        switch (key) {
            case '+' -> arithmeticButtons[0].run();
            case '-' -> arithmeticButtons[1].run();
            case '*' -> arithmeticButtons[2].run();
            case '/' -> arithmeticButtons[3].run();
            case '=' -> arithmeticButtons[4].run();
            default -> {
                for (var button : customButtons) {
                    if (button.getKey() == key) {
                        button.run();
                        break;
                    }
                }
                System.out.println("No such operation :(");
            }
        }

    }
}
