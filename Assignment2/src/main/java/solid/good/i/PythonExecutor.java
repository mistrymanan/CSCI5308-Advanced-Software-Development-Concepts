package solid.good.i;

import solid.good.i.dto.Program;

import java.io.File;
import java.io.IOException;

public class PythonExecutor implements Interpretable {
    @Override
    public int run(Program program,FileUtility fileUtility) throws IOException, InterruptedException {
//        saveToFile(program);
        String programPath = fileUtility.getEnvironmentFolder()+ "Solution.py";
        ProcessBuilder processBuilder = new ProcessBuilder("python3", programPath)
                .redirectOutput(new File( fileUtility.getOutputFileLocation(program)));
        Process process = processBuilder.start();
        return process.waitFor();
    }

    // here as we don't need to implement the compile method with dummy code even if we don't need it, as
    // we have removed the Compile method which was only for CompiledLanguageExecutor specific method

//    @Override
//    public int compile() {
//        // method doesn't do anything as it just executes the code
//        return 0;
//    }
}
