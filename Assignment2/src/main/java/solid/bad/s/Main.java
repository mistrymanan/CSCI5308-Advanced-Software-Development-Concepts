package solid.bad.s;

import solid.bad.s.JavaCodeExecutor;
import solid.bad.i.Languages;
import solid.bad.i.dto.Program;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Program program=new Program("class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\"); \n" +
                "    }\n" +
                "}", Languages.JAVA);
        JavaCodeExecutor executor=new JavaCodeExecutor();
        System.out.println("Java Code Execution Status->"+executor.run(program));
    }
}
