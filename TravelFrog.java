import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import java.util.*;


public class TravelFrog {


	public static void main(String[] args){
		JFrame frame = new JFrame("旅かえる Travel Frog");
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
    JLabel title = new JLabel("<html><h1>旅かえる Travel Frog </h1></html>");
    title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		content.add(title,BorderLayout.PAGE_START);



		DragAndDrop mainpad = new DragAndDrop();

		content.add(mainpad,BorderLayout.CENTER);


		frame.setContentPane(content);
		frame.setSize(1100,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.show(true);
		playSound();
  }

	public static void playSound() {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("BGM.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch(Exception ex) {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
    }
	}



}
