package solid.good.d;

import solid.bad.i.Languages;
import solid.bad.i.dto.Program;

public class ExecutorFactory {
    public Executor createExecutor(String type){
        if(type== "Local")
            return new LocalCodeExecutor();
        else if (type=="Remote") {
            return new RemoteCodeExecutor();
        }
        return null;
    }
}
