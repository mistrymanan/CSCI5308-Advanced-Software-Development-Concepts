package solid.good.d;

import solid.bad.i.dto.Program;

public class RemoteCodeExecutor implements Executor {
    @Override
    public int run(Program program) {
        System.out.println("Executing Program on Remote Isolated Server!");
        return 0;
    }

}
