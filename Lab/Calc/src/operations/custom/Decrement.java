package operations.custom;

import entities.Counter;
import operations.Operation;

public class Decrement extends Operation {
    public Decrement(Counter counter) {
        super(counter);
    }

    @Override
    public void execute() {
        int newRes = counter.getResult() - 1;
        counter.setResult(newRes);
        counter.printResult();
        counter.setOperand(0);
        counter.printOperand();
    }
}