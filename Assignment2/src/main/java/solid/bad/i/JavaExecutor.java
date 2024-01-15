package solid.bad.i;

import solid.bad.i.dto.Program;

import java.io.File;
import java.io.IOException;
public class JavaExecutor implements Executor {
    @Override
    public int run(Program program) throws IOException, InterruptedException {
        saveToFile(program);
        if(!(compile()==0))
            return -1;
        String programPath = "Solution";
        ProcessBuilder processBuilder = new ProcessBuilder().command("java",programPath)
                .directory(new File("./program-execution-environment/"))
                .redirectError(new File("./program-execution-environment/Error.txt"))
                .redirectOutput(new File( "./program-execution-environment/Output-"+program.getLanguage()+".txt"));
        Process process = processBuilder.start();
        return process.waitFor();
    }

    @Override
    public int compile() throws IOException, InterruptedException {
        String programPath = "./program-execution-environment/Solution.java";
        ProcessBuilder processBuilder = new ProcessBuilder("javac", programPath)
                .directory(new File("./"))
                .redirectError(new File( "./Error.txt"));
        Process process = processBuilder.start();
        return process.waitFor();
    }
}
