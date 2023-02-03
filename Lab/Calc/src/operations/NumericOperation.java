package operations;

import entities.Counter;

public class NumericOperation extends Operation {
    int digit;
    public NumericOperation(Counter counter, int digit) {
        super(counter);
        this.digit = digit;
    }

    @Override
    public void execute() {
        int newOperand = counter.getOperand() * 10 + digit;
        counter.setOperand(newOperand);
        counter.printResult();
        counter.printOperand();
    }
}
