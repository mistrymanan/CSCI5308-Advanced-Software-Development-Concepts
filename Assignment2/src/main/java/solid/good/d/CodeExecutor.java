package solid.good.d;

import lombok.AllArgsConstructor;
import lombok.Data;
import solid.bad.i.dto.Program;

@AllArgsConstructor
@Data
public class CodeExecutor {
    Executor executor;

    public int runProgram(Program program) {
       return executor.run(program);
    }
}
