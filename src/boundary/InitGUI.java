package boundary;

import entity.*;
// import database.UpdateDB;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import java.io.IOException;

// When program is run InitGUI displays starting page
public class InitGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JFrame MainFrame;
	private LoginView loginPanel;

//	public InitGUI(Login backend) {
//		setMainFrame(new JFrame());
//		loginPanel = new LoginView(getMainFrame(), backend);
//		getMainFrame().setTitle("Ticket Reservation System");
//		getMainFrame().setResizable(false);
//		getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		getMainFrame().addWindowListener(new WindowAdapter(){
//            public void windowClosing(WindowEvent e){
//				UpdateDB dw = new UpdateDB();
//				try {
//					dw.write_to_Database();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//            }
//        });
//		getMainFrame().setSize(1366, 768);
//		getMainFrame().setContentPane(loginPanel);
//		getMainFrame().revalidate();
//		getMainFrame().setLocationRelativeTo(null);
//		getMainFrame().setLayout(null);
//	}

//	public void addSubmitLoginMouseClicked(MouseListener e) {
//		loginPanel.submitLoginButton.addMouseListener(e);
//	}

	public JFrame getMainFrame() {
		return MainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		MainFrame = mainFrame;
	}

	public void displayGUI() {
		getMainFrame().setVisible(true);
	}

}
