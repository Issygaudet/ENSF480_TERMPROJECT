package boundary;

import entity.*;
import java.awt.event.WindowEvent;

import controller.InstanceController;

import java.awt.event.WindowAdapter;



public class LoginView {
  private InstanceController instance = InstanceController.getInstance();

    
  private String username;
  private String pwd;

  instance.login(username, pwd);
    
}
