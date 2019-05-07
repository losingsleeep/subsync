package org.bob.sybsync;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Babak Eghbali (Bob)
 * @since 2019/05/07
 */
public interface SubtitleService {
    StringBuilder shift(File file, int milliseconds) throws IOException;
}
