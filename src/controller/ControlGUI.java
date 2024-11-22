package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import boundary.*;
import entity.*;

public class ControlGUI {
	private InitGUI frontEnd;

	public ControlGUI(InitGUI frontEnd) {
		this.setFrontEnd(frontEnd);
	}

	public static void main(String[] args) throws IOException{
		InitGUI frontEnd = null;
		Login backEnd = new Login();
		frontEnd = new InitGUI(backEnd);

		// ReadDB dp = new ReadDB();
		// dp.loadDatabase();

		ControlGUI controller = new ControlGUI(frontEnd);
		controller.runClient();
	}

	public InitGUI getFrontEnd() {
		return frontEnd;
	}

	public void setFrontEnd(InitGUI frontEnd) {
		this.frontEnd = frontEnd;
	}

	public void runClient() {
		getFrontEnd().displayGUI();
	}

}
