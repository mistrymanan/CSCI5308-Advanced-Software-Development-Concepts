package solid.good.d;

import solid.bad.i.dto.Program;

public class LocalCodeExecutor implements Executor {
    public int run(Program program) {
        System.out.println("Running program Locally");
        return 0;
    }

}
