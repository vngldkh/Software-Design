package operations.arithmetic;

import entities.Counter;
import operations.Operation;

public class Multiplication extends Operation {
    public Multiplication(Counter counter) {
        super(counter);
    }

    @Override
    public void execute() {
        int newRes = counter.getResult() * counter.getOperand();
        counter.setResult(newRes);
        counter.printResult();
        counter.setOperand(0);
        counter.printOperand();
    }
}
