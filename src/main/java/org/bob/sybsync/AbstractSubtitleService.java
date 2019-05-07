package org.bob.sybsync;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Babak Eghbali (Bob)
 * @since 2019/05/07
 */
public abstract class AbstractSubtitleService implements SubtitleService {

    @Override
    public StringBuilder shift(File file, int milliseconds) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null){
            sb.append(process(line, milliseconds));
            sb.append(System.lineSeparator());
        }
        return sb;
    }

    protected abstract String process(String line, int milliseconds);
}
