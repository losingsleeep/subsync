package org.bob.sybsync;

import java.io.File;
import java.io.IOException;

/**
 * @author Babak Eghbali (Bob)
 * @since 2019/05/07
 */
public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello!");
        File file = new File("C:\\_sub\\Redbad.2018.1080p.WEB-DL.H264.AC3-EVO.srt");
        SubtitleService service = new SubtitleServiceImpl();
        StringBuilder sb = service.shift(file, 10);
        System.out.println(sb.toString());
    }
}
