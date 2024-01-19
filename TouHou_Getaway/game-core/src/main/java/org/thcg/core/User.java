package org.thcg.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.thcg.core.UserRecord;
public class User {
    public String userName;
    public File userRecord;
    public User(){
        this.userName = "Player";
    }
    public User(String Username){
        this.userName = Username;
    }
}
