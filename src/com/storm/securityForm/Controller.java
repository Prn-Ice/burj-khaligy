package com.storm.securityForm;

import com.storm.securityForm.dataModel.FormData;
import com.storm.securityForm.dataModel.FormInput;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TextField name;
    @FXML
    private PasswordField pwd;

    private List<FormInput> fields = new ArrayList<>();



    public void onButtonClicked(){
        System.out.println("Login Successful Mr. " + name.getText());
        System.out.println("Password Is " + pwd.getText());
        fields.addAll(FormData.getInstance().getDataItems());
        fields.add(new FormInput(name.getText(), pwd.getText()));
        FormData.getInstance().setDataItems(fields);
    }
}
