package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerB {

    @FXML
    private Label label, lblAge, lblCity, lblNic, lblVaccs, lblBooth, lblDate;
    //Constructor to change the Label Text
    public void name (String a, String b, String c, String d, String e, String f, String g){
        label.setText(a +" " + b);
        lblAge.setText(c);
        lblCity.setText(d);
        lblNic.setText(e);
        lblVaccs.setText(f);
        lblBooth.setText(g);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy     HH:mm:ss");
        lblDate.setText(formatter.format(date));
    }



}
