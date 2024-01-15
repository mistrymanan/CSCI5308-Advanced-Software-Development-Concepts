package solid.bad.s;

import solid.bad.i.Languages;
import solid.bad.i.dto.Program;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JavaCodeExecutor {
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

    public int compile() throws IOException, InterruptedException {
        String programPath = "./program-execution-environment/Solution.java";
        ProcessBuilder processBuilder = new ProcessBuilder("javac", programPath)
                .directory(new File("./"))
                .redirectError(new File( "./Error.txt"));
        Process process = processBuilder.start();
        return process.waitFor();
    }
    void saveToFile(Program program) throws IOException {
        String path="./program-execution-environment/";
        File file = new File(path);
        String extention=program.getLanguage()== Languages.JAVA ? ".java":".py";
        if(file.mkdir() || file.isDirectory()){
            Files.writeString(Paths.get(path+"Solution"+extention), program.getCode());
        }
        createOutputFile(program.getLanguage());
    }

    void createOutputFile(Languages language) throws IOException {
        String path="./program-execution-environment/";
        new File(path+ "Output-"+language+".txt").createNewFile();
    }
}
