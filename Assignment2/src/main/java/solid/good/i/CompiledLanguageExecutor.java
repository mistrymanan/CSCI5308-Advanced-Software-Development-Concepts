package solid.good.i;

import solid.good.i.FileUtility;

import java.io.IOException;

public interface CompiledLanguageExecutor extends Executor {
    int compile(FileUtility fileUtility) throws IOException, InterruptedException;
}