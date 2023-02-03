package operations.custom;

import entities.Counter;
import operations.Operation;

public class AreEqual extends Operation {
    public AreEqual(Counter counter) {
        super(counter);
    }

    @Override
    public void execute() {
        int newRes = counter.getResult() == counter.getOperand() ? 1 : 0;
        counter.setResult(newRes);
        counter.printResult();
        counter.setOperand(0);
        counter.printOperand();
    }
}
