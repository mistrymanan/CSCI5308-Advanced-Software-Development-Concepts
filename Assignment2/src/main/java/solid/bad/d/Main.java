package solid.bad.d;

import solid.bad.i.Languages;
import solid.bad.i.dto.Program;

public class Main {
    public static void main(String[] args) {
        Program program=new Program("print(\"Hello World!\")", Languages.PYTHON);
        CodeExecutorDemo codeExecutorDemo=new CodeExecutorDemo();
        codeExecutorDemo.runProgram(program);
    }
}
