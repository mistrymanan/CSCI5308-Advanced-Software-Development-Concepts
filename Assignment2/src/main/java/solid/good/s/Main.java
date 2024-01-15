package solid.good.s;

import solid.bad.i.Languages;
import solid.bad.i.dto.Program;
import solid.good.s.JavaCodeExecutor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Program program=new Program("class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\"); \n" +
                "    }\n" +
                "}", Languages.JAVA);
        FileUtility fileUtility=new FileUtility();
        fileUtility.saveToFile(program);
        JavaCodeExecutor executor=new JavaCodeExecutor();
        System.out.println("Java Code Execution Status->"+executor.run(program,fileUtility));
    }
}
