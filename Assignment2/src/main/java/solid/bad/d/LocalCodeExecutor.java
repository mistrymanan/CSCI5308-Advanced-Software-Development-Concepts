package solid.bad.d;

import solid.bad.i.dto.Program;

public class LocalCodeExecutor {

    public int run(Program program){
        System.out.println("Executing given Program in Local environment");
        return 0;
    }

}
