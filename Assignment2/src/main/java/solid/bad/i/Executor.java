package solid.bad.i;

import solid.bad.i.dto.Program;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface Executor {
    int run(Program program) throws IOException, InterruptedException;
    int compile() throws IOException, InterruptedException;
    default void saveToFile(Program program) throws IOException {
        String path="./program-execution-environment/";
        File file = new File(path);
        String extention=program.getLanguage()==Languages.JAVA ? ".java":".py";
        if(file.mkdir() || file.isDirectory()){
            Files.writeString(Paths.get(path+"Solution"+extention), program.getCode());
        }
        createOutputFile(program.getLanguage());
    }

    default void createOutputFile(Languages language) throws IOException {
        String path="./program-execution-environment/";
        new File(path+ "Output-"+language+".txt").createNewFile();
    }
}
