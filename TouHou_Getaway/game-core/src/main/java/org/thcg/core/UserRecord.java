package org.thcg.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.thcg.util.GameConstant.USER_FILE_PATH;

public class UserRecord extends User {
    public File recordFile;
    public String userFileName;
    public int newRecord;
    public String recordTime;
    public UserRecord(){
        this.recordTime = "";
        this.newRecord = 0;
        this.userFileName = super.userName;
        try{
            this.recordFile = new File(USER_FILE_PATH + "/" + userFileName);
            FileWriter fw = new FileWriter(recordFile);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(super.userName);
            bw.write("\n");

            bw.close();
            fw.close();
            System.out.println("User data appended to file successfully.");
        }catch (IOException e){
            e.printStackTrace();
        }

    }


    public void saveUserScore(User user) {
        try{
            FileWriter fw = new FileWriter(this.recordFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(this.newRecord);

            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            this.recordTime = currentTime.format(formatter);

            bw.write("-----" + this.recordTime);
            bw.close();
            fw.close();
            System.out.println("User new record appended to file successfully.");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
