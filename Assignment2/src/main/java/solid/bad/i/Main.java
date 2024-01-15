package solid.bad.i;

import solid.bad.i.dto.Program;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Program program=new Program("print(\"d\")",Languages.PYTHON);
                PythonExecutor pythonExecutor=new PythonExecutor();
        System.out.println("Python Code Execution Status->"+pythonExecutor.run(program));

        Program program2=new Program("class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\"); \n" +
                "    }\n" +
                "}",Languages.JAVA);
        JavaExecutor executor=new JavaExecutor();
        System.out.println("Java Code Execution Status->"+executor.run(program2));

    }
}
