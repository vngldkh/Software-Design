package operations.arithmetic;

import entities.Counter;
import operations.Operation;

public class Equals extends Operation {
    public Equals(Counter counter) {
        super(counter);
    }

    @Override
    public void execute() {
        counter.setResult(counter.getOperand());
        counter.printResult();
        counter.setOperand(0);
        counter.printOperand();
    }
}
