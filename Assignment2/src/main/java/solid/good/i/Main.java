package solid.good.i;

import solid.good.i.dto.Program;
import solid.good.i.FileUtility;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Program program=new Program("print(\"d\")", Languages.PYTHON);
                PythonExecutor pythonExecutor=new PythonExecutor();
        FileUtility fileUtility=new FileUtility();
        fileUtility.saveToFile(program);
        System.out.println("Python Code Execution Status->"+pythonExecutor.run(program,fileUtility));

        Program program2=new Program("class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\"); \n" +
                "    }\n" +
                "}", Languages.JAVA);
//        FileUtility fileUtility1=new FileUtility();
        fileUtility.saveToFile(program2);
        JavaExecutor executor=new JavaExecutor();
        System.out.println("Java Code Execution Status->"+executor.run(program2,fileUtility));

    }
}
