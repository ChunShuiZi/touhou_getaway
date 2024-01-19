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


    public void saveUserScore(UserRecord userRecord) {
        try{
            FileWriter fw = new FileWriter(userRecord.recordFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(userRecord.newRecord);

            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            userRecord.recordTime = currentTime.format(formatter);

            bw.write("-----" + userRecord.recordTime);
            bw.close();
            fw.close();
            System.out.println("User new record appended to file successfully.");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
