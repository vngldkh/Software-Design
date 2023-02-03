package operations.arithmetic;

import entities.Counter;
import operations.Operation;

public class Division extends Operation {
    public Division(Counter counter) {
        super(counter);
    }

    @Override
    public void execute() {
        int newRes = counter.getResult() / counter.getOperand();
        counter.setResult(newRes);
        counter.printResult();
        counter.setOperand(0);
        counter.printOperand();
    }
}
