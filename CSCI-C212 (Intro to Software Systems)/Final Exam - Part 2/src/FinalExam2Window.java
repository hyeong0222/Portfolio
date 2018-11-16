////////////////////////////////////////////////////////////////////////////////////
//
// C212 Spring 18
// Final Exam Part 2
//
//
//
// Author  Sang Hyeong Woo
// Username   sangwoo
// Last Edited:  May 1st, 2018
//
//////////////////////////////////////////////////////////////////////////////////
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FinalExam2Window extends JFrame {
	
	JPanel finalExam2Driver;
	
	public FinalExam2Window() {
	        super();
	        // TO-DO: set up the frame
	        finalExam2Driver = (JPanel) new FinalExam2Driver();
	        this.setTitle("Race");
	        this.getContentPane().add(finalExam2Driver);
	        this.pack();
	        this.setVisible(true);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // run main application 
        JFrame finalExam2Window = new FinalExam2Window();
    }		
}
