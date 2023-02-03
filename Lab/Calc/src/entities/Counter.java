package entities;

public class Counter {
    private int result;
    private int operand;

    public int getResult() {
        return result;
    }

    public void setResult(int value) {
        result = value;
    }

    public int getOperand() {
        return operand;
    }

    public void setOperand(int value) {
        operand = value;
    }

    public void printResult() {
        System.out.println("Current result = " + result);
    }

    public void printOperand() {
        System.out.println("Current operand value = " + operand);
    }
}
