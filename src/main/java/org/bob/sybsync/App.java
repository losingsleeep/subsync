package org.bob.sybsync;

import org.bob.sybsync.ui.MainForm;

import java.io.*;

/**
 * @author Babak Eghbali (Bob)
 * @since 2019/05/07
 */
public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to SubSync!");
        //testShifter();
        new MainForm().start();
    }

    private static void testShifter() throws IOException {
        File inputFile = new File("C:\\_sub\\Redbad.2018.1080p.WEB-DL.H264.AC3-EVO.srt");
        SubtitleService service = new SubtitleServiceImpl();
        StringBuilder sb = service.shift(inputFile, 10);
        System.out.println(sb.toString());
        File outputFile = new File(inputFile.getAbsolutePath()+"(1)");
        FileOutputStream fout = new FileOutputStream(outputFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.append(sb);
        }
    }
}
