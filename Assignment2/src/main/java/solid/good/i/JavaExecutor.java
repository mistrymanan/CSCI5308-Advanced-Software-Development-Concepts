package solid.good.i;

import solid.good.i.dto.Program;
import solid.good.i.FileUtility;

import java.io.File;
import java.io.IOException;
public class JavaExecutor implements CompiledLanguageExecutor {
    @Override
    public int run(Program program,FileUtility fileUtility) throws IOException, InterruptedException {
        if(!(compile(fileUtility)==0))
            return -1;
        String programPath = "Solution";
        ProcessBuilder processBuilder = new ProcessBuilder().command("java",programPath)
                .directory(new File(fileUtility.getEnvironmentFolder()))
                .redirectError(new File(fileUtility.getErrorFileLocation()))
                .redirectOutput(new File( fileUtility.getOutputFileLocation(program)));
        Process process = processBuilder.start();
        return process.waitFor();
    }

    public int compile(FileUtility fileUtility) throws IOException, InterruptedException {
        String programPath = fileUtility.getEnvironmentFolder()+"Solution.java";
        ProcessBuilder processBuilder = new ProcessBuilder("javac", programPath)
                .directory(new File("./"))
                .redirectError(new File( fileUtility.getErrorFileLocation()));
        Process process = processBuilder.start();
        return process.waitFor();
    }
}
