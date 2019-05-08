package org.bob.sybsync.ui;

import org.bob.sybsync.SubtitleService;
import org.bob.sybsync.SubtitleServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author Babak Eghbali (Bob)
 * @since 2019/05/08
 */
public class MainForm extends JFrame {

    private JTextField sourceFileTextBox;
    private JTextField millisecondsTextBox;

    public MainForm(){
        setSize(700,300);
        setLayout(null);//using no layout managers
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start(){
        addSyncButton();//adding button in JFrame
        addSourceFileTextBox();
        addMillisecondsTextBox();
        //
        setVisible(true);//making the frame visible
    }

    private void addSyncButton(){
        JButton b=new JButton("Sync!");//creating instance of JButton
        b.setBounds(50,200,100, 35);//x axis, y axis, width, height
        b.addActionListener(syncButtonActionListener);
        add(b);
    }

    private ActionListener syncButtonActionListener = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            File inputFile = new File(sourceFileTextBox.getText());
            SubtitleService service = new SubtitleServiceImpl();
            StringBuilder sb = null;
            try {
                sb = service.shift(inputFile, getMilliseconds());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "IO Error occurred during the sync action: "
                + ex.getMessage());
                return;
            } catch (Exception ex1) {
                JOptionPane.showMessageDialog(null, "Invalid number for milliseconds: "
                        + ex1.getMessage());
                return;
            }
//            System.out.println(sb.toString());
            File outputFile = new File(inputFile.getAbsolutePath()+"(1)");
            try {
                FileOutputStream fout = new FileOutputStream(outputFile);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                    writer.append(sb);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        null, "IO Error occurred while trying to write to the new file: "
                        + ex.getMessage());
                return;
            }
            JOptionPane.showMessageDialog(null, "Sub Sync Successful!");
        }
    };

    private void addSourceFileTextBox(){
        sourceFileTextBox = new JTextField();
        sourceFileTextBox.setBounds(50,50, getWidth() - 100,30);
        add(sourceFileTextBox);
    }

    private void addMillisecondsTextBox(){
        millisecondsTextBox = new JTextField();
        millisecondsTextBox.setBounds(50,100, getWidth() - 100,30);
        add(millisecondsTextBox);
    }

    public int getMilliseconds() throws Exception {
        return Integer.parseInt(millisecondsTextBox.getText());
    }
}
