package org.thcg.core;

import java.io.File;

import static org.thcg.util.GameConstant.*;

public class User {
    public String userName;
    public File userRecordFile;
    public String gameMode;
    public User(){
        this.userName = DEFAULT_USER_NAME;
        UserRecord userRecord1 = new UserRecord();
        this.userRecordFile = userRecord1.recordFile;
        this.gameMode = GAME_MODE_EASY;
    }
    public User(String username){
        this.userName = username;
        UserRecord userRecord1 = new UserRecord(username);
        this.userRecordFile = userRecord1.recordFile;
        this.gameMode = GAME_MODE_EASY;
    }
}
