import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
	
	
	
public class LIPanel extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel, southPanel, northPanel;
	private JLabel label, correctLabel, wrongLabel, spaceKeeper, spaceKeeper2;
	private JButton start, right, wrong, reset;
	private JCheckBox lowercase, uppercase, randomCheckBox;
	private boolean lowercaseTrue, uppercaseTrue;
	ArrayList <Character> letters, rightLetters, wrongLetters;
	private Random random;
	private int randomIndex, maxIndex, correctCounter;
	private String correct, incorrect;
	
	LIPanel() {
			correct = "Correct: ";
			incorrect = "Incorrect: ";
			
			label = new JLabel("Ready");
	        label.setHorizontalAlignment(JLabel.CENTER);
	        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 200));
	        
	        randomCheckBox = new JCheckBox("random");
	        randomCheckBox.setSelected(true);
	        randomCheckBox.addActionListener(this);
	        spaceKeeper2 = new JLabel("           ");
	        lowercase = new JCheckBox("Lowercase");
	        lowercase.setSelected(true);
	        lowercase.addActionListener(this);
	        uppercase = new JCheckBox("Uppercase");
	        uppercase.setSelected(true);
	        uppercase.addActionListener(this);
	        start = new JButton("start");
	        start.addActionListener(this);
	        right = new JButton("right");
	        right.addActionListener(this);
	        wrong = new JButton("wrong");
	        wrong.addActionListener(this);
	        reset = new JButton("reset");
	        reset.addActionListener(this);
	        
	        panel = new JPanel();
	        panel.setPreferredSize(new Dimension(1000, 400));
	        //panel.setBounds(100, 100, 1000, 400);
			panel.setLayout(new BorderLayout(1, 1));
			panel.setBackground(Color.white);
			panel.add(label, BorderLayout.CENTER);
			
			southPanel = new JPanel();
			southPanel.setBackground(Color.orange);
			southPanel.add(randomCheckBox);
			southPanel.add(spaceKeeper2);
			southPanel.add(lowercase);
			southPanel.add(uppercase);
			southPanel.add(start);
			southPanel.add(right);
			southPanel.add(wrong);
			southPanel.add(reset);
			
			panel.add(southPanel, BorderLayout.SOUTH);
			
			correctLabel = new JLabel(correct);
			correctLabel.setHorizontalAlignment(JLabel.LEFT);
			correctLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			
			spaceKeeper = new JLabel("                                  ");
			
			wrongLabel = new JLabel(incorrect);
			wrongLabel.setHorizontalAlignment(JLabel.RIGHT);
			wrongLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			
			northPanel = new JPanel();
			northPanel.setBackground(Color.yellow);
			northPanel.add(correctLabel);
			northPanel.add(spaceKeeper);
			northPanel.add(wrongLabel);
			
			panel.add(northPanel,  BorderLayout.NORTH);
			
			getContentPane().add(panel);
			
			setTitle("Alphabet Check");
			
			lowercaseTrue = true;
			uppercaseTrue = true;
			letters = new ArrayList <Character>();
			rightLetters = new ArrayList <Character>();
			wrongLetters = new ArrayList <Character>();
			random = new Random();
			maxIndex = 0;
			correctCounter = 0;
			
			
			WindowListener l = new WindowAdapter() {
		         public void windowClosing(WindowEvent e) {
		           System.exit(0);
		         }
		      };
		        addWindowListener(l);
		        pack();
		        setVisible(true);
		        
		        //System.out.println("LIPanel did run");
		}
	
	//Will reset all to be able to start again
		private void resetApp() {
			correctCounter = 0;
			maxIndex = 0;
			correctLabel.setText("Correct: ");
			wrongLabel.setText("Incorrect: ");
			if(lowercase.isSelected()) {
				maxIndex = maxIndex + 26;
			}
			if(uppercase.isSelected()) {
				maxIndex = maxIndex + 26;
			}
			letters.clear();
			rightLetters.clear();
			wrongLetters.clear();
			label.setText("Ready");
			char low = 'a';
			char up = 'A';
			//int counter = 0;
			
			if(lowercaseTrue) {
				letters.add(low);
				//System.out.println("" + letters.get(0));
				for (int i=1; i<26;i++) {
					low++;
					letters.add(low);
					//System.out.println("" + letters.get(i));
				}
			}
			//counter = letters.size();
			
			if(uppercaseTrue) {
				letters.add(up);
				/*
				if (letters.size() > 10) {
					//System.out.println("" + letters.get(26));
				} else {
					//System.out.println("" + letters.get(0));
				}
				*/
				for (int i=1; i<26;i++) {
					up++;
					letters.add(up);
					//System.out.println("" + letters.get(counter+i));
				}
			}
			
		}
		
	//Will set up the first letter
	private void getStarted() {
		if (!lowercase.isSelected() && !uppercase.isSelected() ) {
			resetApp();
		} else {
			correctCounter++;
			if(randomCheckBox.isSelected()) {
				randomIndex = random.nextInt(maxIndex);
			} else {
				randomIndex = 0;
			}
			label.setText("" + letters.get(randomIndex));
			//System.out.println("getStarted done with label set at " + label.getText());
		}
	}
	
	//Will continue after correct or wrong is clicked each time
	private void correct() {
			correctCounter++;
			rightLetters.add(letters.get(randomIndex));
			correct = "Correct: ";
			for(int i=0; i< rightLetters.size(); i++) {
				correct = correct + rightLetters.get(i) + " ";
			}
			correctLabel.setText(correct);
			for (int i = 0; i < wrongLetters.size(); i++) {
				if(letters.get(randomIndex) == wrongLetters.get(i)) {
					wrongLetters.remove(i);
				}
			}
			incorrect = "Incorrect: ";
			for(int i=0; i<wrongLetters.size(); i++) {
				incorrect = incorrect + wrongLetters.get(i)+ " ";
			}
			wrongLabel.setText(incorrect);
			letters.remove(randomIndex);
			maxIndex--;
			if (maxIndex != 0) {
				if(randomCheckBox.isSelected()) {
					randomIndex = random.nextInt(maxIndex);
				} else {
					randomIndex = 0;
				}
				label.setText("" + letters.get(randomIndex));
			} else {
				resetApp();
			}
			//System.out.println("Correct: maxIndex = " + maxIndex + ", letters has " + letters.size() + " left in it. ");
		}
	
	private void wrong() {
		if(!lowercase.isSelected() && !uppercase.isSelected()) {
			resetApp();
		} else {
			boolean checkExistance = false;
			for(int i=0; i<wrongLetters.size();i++) {
				if (letters.get(randomIndex) == wrongLetters.get(i)) {
					checkExistance = true;
				}
			}
			if (!checkExistance) {
				wrongLetters.add(letters.get(randomIndex));
			}
			
			
			incorrect = "Incorrect: ";
			for(int i=0; i<wrongLetters.size(); i++) {
				incorrect = incorrect + wrongLetters.get(i)+ " ";
			}
			wrongLabel.setText(incorrect);
			randomIndex = random.nextInt(maxIndex);
			label.setText("" + letters.get(randomIndex));
			//System.out.println("Incorrect: maxIndex = " + maxIndex + ", letters has " + letters.size() + " left in it. ");
		}
	}
	
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//System.out.println("start clicked");
		
		
		if(e.getSource() == start) {
			getStarted();
			//System.out.println("start clicked gets recognized");
		} else if (e.getSource() == right) {
			if(correctCounter > 0) {
				correct();
			} else {
				getStarted();
			}
		} else if (e.getSource() == wrong) {
			wrong();
		} else if (e.getSource() == reset) {
			resetApp();
		} else if (e.getSource() == lowercase) {
			if(lowercase.isSelected()){
				lowercaseTrue = true;
				//maxIndex = maxIndex + 26;
				resetApp();
			} else {
				lowercaseTrue = false;
				//maxIndex = maxIndex - 26;
				//System.out.println("maxIndex just got 26 taken out");
				resetApp();
			}
		} else if (e.getSource() == uppercase) {
			if(uppercase.isSelected()) {
				uppercaseTrue = true;
				//maxIndex = maxIndex + 26;
				resetApp();
			} else {
				uppercaseTrue = false;
				//maxIndex = maxIndex - 26;
				resetApp();
			}
		} else if (e.getSource() == randomCheckBox) {
			resetApp();
		}
		
		
	}

	public static void main(String[] args) {
		
		LIPanel window = new LIPanel();
		window.resetApp();
		
	}
	
}
