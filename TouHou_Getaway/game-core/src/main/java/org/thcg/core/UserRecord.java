package org.thcg.core;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

import static org.lwjgl.system.linux.X11.False;
import static org.lwjgl.system.linux.X11.True;
import static org.thcg.util.GameConstant.*;

@Slf4j
public class UserRecord extends User{
    private File recordFileChecker;
    public File recordFile;
    private final String userFileName;
    private int newRecord;//用来写入文件，不要用来判断
    private String recordTime;
    private final int gameModeCount = GAME_MODE.length;

    public UserRecord(){
        this.recordTime = "";
        this.newRecord = 0;
        this.userFileName = DEFAULT_USER_NAME;
        //为每一个难度设置一个记录文件
        //记录文件地址宏定义了，请帮助完善实现地址自定义
        for(int i = 0; i < gameModeCount; i++){
            try{
                this.recordFileChecker = new File(USER_FILE_PATH + "/" + userFileName + "/" + GAME_MODE[i]);
                FileWriter fw = new FileWriter(recordFileChecker);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(DEFAULT_USER_NAME + "\n");

                bw.close();
                fw.close();
            }catch (IOException e){
                if (log.isErrorEnabled()) {
                    log.error("构造UserRecord类初始化成员发生异常: {}", e.getMessage(), e);
                }
            }
        }
        log.info("用户记录初始化完成");
    }

    public UserRecord(String username){
        this.recordTime = "";
        this.newRecord = 0;
        this.userFileName = DEFAULT_USER_NAME;
        for(int i = 0; i < gameModeCount; i++){
            try{
                this.recordFileChecker = new File(USER_FILE_PATH + "/" + userFileName + "/" + GAME_MODE[i]);
                FileWriter fw = new FileWriter(recordFileChecker);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(username + "\n");

                bw.close();
                fw.close();
            }catch (IOException e) {
                if (log.isErrorEnabled()) {
                    log.error("构造UserRecord类初始化成员发生异常: {}", e.getMessage(), e);
                }
            }
        }
        log.info("用户记录初始化完成");
    }

    public void saveUserScore(UserRecord userRecord, int gameMode) {
        recordFileChecker = new File(USER_FILE_PATH + "/" + userFileName + "/" + GAME_MODE[gameMode]);
        try(FileWriter fw = new FileWriter(userRecord.recordFileChecker, true);
            BufferedWriter bw = new BufferedWriter(fw)){
            //获取用户记录时间
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            userRecord.recordTime = currentTime.format(formatter);
            //记录格式“记录分数-----记录时间”
            bw.newLine();
            bw.write(userRecord.newRecord + "-----" + userRecord.recordTime);

        }catch (IOException e){
            if (log.isErrorEnabled()) {
                log.error("保存用户记录时构造FileWriter时发生异常: {}", e.getMessage(), e);
            }
        }finally {
            log.info("保存用户最新记录成功");
        }
    }

    public void getUserScore(UserRecord userRecord, int gameMode){
        recordFileChecker = new File(USER_FILE_PATH + "/" + userFileName + "/" + GAME_MODE[gameMode]);
        try(BufferedReader br = new BufferedReader(new FileReader(userRecord.recordFileChecker))){
            List<String> lines = new ArrayList<>();
            String lastRecord;
            while ((lastRecord = br.readLine()) != null) {
                lines.add(lastRecord);
            }
            if (!lines.isEmpty()) {
                String pattern = "(\\d+)";//正则表达式匹配每一行“-”前的数字
                Pattern r = Pattern.compile(pattern);
                String lastLine = lines.get(lines.size() - 1);
                Matcher m = r.matcher(lastLine);

                userRecord.newRecord = Integer.parseInt(m.group(0));//将匹配到的数字赋值给newRecord
            } else {
                log.info("用户记录文件为空，用户最新记录读取失败！");
            }
        }catch (IOException e){
            if (log.isErrorEnabled()) {
                log.error("读取用户记录时构造FileWriter时发生异常: {}", e.getMessage(), e);
            }
        }finally {
            log.info("读取用户最新记录成功");
        }
    }
    //每次游戏结束调用，判断游戏分数是否为新纪录
    public int isNewRecord(UserRecord userRecord, int gameMode){
        getUserScore(userRecord, gameMode);//读取最新的记录
        if(SCORE > userRecord.newRecord){
            return True;
        }
        else {
            return False;
        }
    }

    public void clearUserRecord(User user){
        for(int i = 0; i < gameModeCount; i++){
            try{
                recordFileChecker = new File(USER_FILE_PATH + "/" + userFileName + "/" + GAME_MODE[i]);
                FileWriter fw = new FileWriter(recordFileChecker);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(user.userName + "\n");

                bw.close();
                fw.close();
            }catch (IOException e) {
                if (log.isErrorEnabled()) {
                    log.error("清除用户记录时发生异常: {}", e.getMessage(), e);
                }
            }finally {
                log.info("清除用户记录成功");
            }
        }
    }


}