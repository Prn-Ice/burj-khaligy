package com.storm.securityForm.dataModel;

import com.storm.securityForm.dataModel.FormInput;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FormData {
    private static FormData instance = new FormData();
    private static String filename = "userdata.txt";

    private List<FormInput> dataItems;

    public static FormData getInstance() {
        return instance;
    }

    private FormData() {

    }

    public List<FormInput> getDataItems() {
        return dataItems;
    }

    public void setDataItems(List<FormInput> dataItems) {
        this.dataItems = dataItems;
    }
    public void loadUserData() throws IOException {
        dataItems = new ArrayList<>();

        Path dir = Paths.get(System.getProperty("user.home"),"Documents", "Havoc Tech");
        if (!(Files.exists(dir))) {
            Files.createDirectories(dir);
        }

        Path path = Paths.get(dir+"\\"+filename);

        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] strings = input.split("\t");
                String uname = strings[0];
                String pword = strings[1];

                FormInput data = new FormInput(uname, pword);
                dataItems.add(data);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void saveUserData() throws IOException {
//        Path path = Paths.get(filename);
        Path dir = Paths.get(System.getProperty("user.home"),"Documents", "Havoc Tech");
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }

        Path path = Paths.get(dir+"\\"+filename);

        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<FormInput> iter = dataItems.iterator();
            while (iter.hasNext()) {
                FormInput item = iter.next();
                bw.write(String.format("%s\t%s",
                        item.getUserName(),
                        item.getPassword()));
                bw.newLine();
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }
}
