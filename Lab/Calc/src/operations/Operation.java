package operations;

import entities.Counter;

public abstract class Operation {
    protected Counter counter;

    protected Operation(Counter counter) {
        this.counter = counter;
    }

    public abstract void execute();
}
