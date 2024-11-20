package database;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import Entity.*;

public class updatedatabase {
    ControlDB databaseController;

    public UpdateDB() {
        databaseController = ControlDB.getobject();
    }

    public void write_to_Database() throws IOException {
        writeRegisteredUser();
    }
   
    public void writeRegisteredUser() throws IOException {
        OutputStreamWriter Ostream = new OutputStreamWriter(new FileOutputStream("Data/RU.txt"), "UTF-8");
        BufferedWriter bw = new BufferedWriter(Ostream);
        for(int i = 0; i < databaseController.getlist_of_users().size(); i++){
            RegisteredUser r = databaseController.getlist_of_users().get(i);
            bw.write(r.getID_of_User() + ";" + r.getUsername() + ";" + r.getPassword() + ";" + r.getfirst_name() + ";" +
                    r.getlast_name() + ";" + r.getgmail_of_user() + ";" + r.getBankInfo().getID_for_bank() + ";" + r.getdate_of_feeDeposit().getDay() + ";" +
                    r.getdate_of_feeDeposit().getMonth() + ";" + r.getdate_of_feeDeposit().getYear() + "\n");
        }
        bw.close();
    }   
}
