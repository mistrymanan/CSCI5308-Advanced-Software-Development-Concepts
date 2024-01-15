package solid.bad.i;

import solid.bad.i.dto.Program;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PythonExecutor implements Executor {
    @Override
    public int run(Program program) throws IOException, InterruptedException {
        saveToFile(program);
        String programPath = "./program-execution-environment/Solution.py";
        ProcessBuilder processBuilder = new ProcessBuilder("python3", programPath)
                .redirectOutput(new File( "./program-execution-environment/Output-"+program.getLanguage()+".txt"));
        Process process = processBuilder.start();
        return process.waitFor();
    }

    @Override
    public int compile() {
        // method doesn't do anything as it just executes the code
        return 0;
    }
}
