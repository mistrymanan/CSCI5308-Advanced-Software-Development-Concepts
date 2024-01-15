package solid.bad.d;

import solid.bad.i.dto.Program;

public class CodeExecutorDemo {

    public int runProgram(Program program){
        LocalCodeExecutor localCodeExecutor=new LocalCodeExecutor();
        return localCodeExecutor.run(program);
    }
}
