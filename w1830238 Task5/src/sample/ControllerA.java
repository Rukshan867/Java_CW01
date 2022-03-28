package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class ControllerA {

    @FXML
    private TextField txtFirst, txtLast, txtAge, txtCity, txtNic;

    @FXML
    private RadioButton r1, r2, r3;

    @FXML
    private RadioButton s0, s1, s2, s3, s4, s5;

    @FXML
    private Label lblVali;

    @FXML
    private CheckBox b0, b1, b2, b3, b4, b5;

    //This Method will give the selected Vaccination type
    public String radio(){
        String r ="";
        if (r1.isSelected()){
            r += r1.getText();
        }
        else if (r2.isSelected()){
            r += r2.getText();
        }
        else if (r3.isSelected()){
            r += r3.getText();
        }
        else {
            r = "Vaccination has not been Selected";
        }
        return r;

    }
    //This method will give the Selected Booth Number
    public String SelectBooth(){
        String select = "";
        if (s0.isSelected()){
            select += s0.getText();
        }
        else if (s1.isSelected()){
            select += s1.getText();
        }
        else if (s2.isSelected()){
            select += s2.getText();
        }
        else if (s3.isSelected()){
            select += s3.getText();
        }
        else if (s4.isSelected()){
            select += s4.getText();
        }
        else if (s5.isSelected()){
            select += s5.getText();
        }
        else {
            select = "Booth has not been Selected";
        }
        return select;

    }




    @FXML
    void change(ActionEvent event) {
        //Validating the Text Fields and Radio buttons
        if (txtFirst.getLength() == 0){
            lblVali.setText("Please Enter your Name ");
        }
        else if (txtLast.getLength() == 0){
            lblVali.setText("Please Enter your Surname ");
        }
        else if (txtAge.getLength() == 0){
            lblVali.setText("Please Enter your Age ");
        }
        else if (txtCity.getLength() == 0){
            lblVali.setText("Please Enter your City ");
        }
        else if (txtNic.getLength() == 0){
            lblVali.setText("Please Enter your NIC or Passport ");
        }
        else if (radio().equals("Vaccination has not been Selected")){
            lblVali.setText("Vaccination type has not been Selected");
        }
        else if (SelectBooth().equals("Booth has not been Selected")){
            lblVali.setText("Booth number has not been Selected");
        }
        else {


            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScreenB.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                ControllerB scene = fxmlLoader.getController();
                scene.name(txtFirst.getText(), txtLast.getText(), txtAge.getText(), txtCity.getText(), txtNic.getText(), radio(), SelectBooth());
                lblVali.setText("");

                Stage stage = new Stage();

                stage.setTitle("Receipt");
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
