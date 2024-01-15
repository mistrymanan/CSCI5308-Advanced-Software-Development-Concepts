package solid.good.s;

import solid.bad.i.Languages;
import solid.bad.i.dto.Program;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtility {

    String getEnvironmentFolder(){
        return "./program-execution-environment/" ;
    }
    String getOutputFileLocation(Program program){
        return "./program-execution-environment/Output-"+program.getLanguage()+".txt";
    }
    String getErrorFileLocation(){
        return "./program-execution-environment/Error.txt";
    }
    void saveToFile(Program program) throws IOException {
        File file = new File(getEnvironmentFolder());
        String extention=program.getLanguage()== Languages.JAVA ? ".java":".py";
        if(file.mkdir() || file.isDirectory()){
            Files.writeString(Paths.get(getEnvironmentFolder()+"Solution"+extention), program.getCode());
        }
        createOutputFile(program.getLanguage());
    }

    void createOutputFile(Languages language) throws IOException {
        new File(getEnvironmentFolder()+ "Output-"+language+".txt").createNewFile();
    }
}
