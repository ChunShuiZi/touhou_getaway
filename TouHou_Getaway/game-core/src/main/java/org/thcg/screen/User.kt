package org.thcg.screen

import org.thcg.core.UserRecord
import org.thcg.util.GameConstant
import org.thcg.screen.GameScreen
import java.io.File

open class User {
    val userName: String
    var userRecordFile: File
    var gameMode: String

    constructor() {
        this.userName = GameConstant.DEFAULT_USER_NAME
        val userRecord1 = UserRecord()
        this.userRecordFile = userRecord1.recordFile
        this.gameMode = GameConstant.GAME_MODE_EASY
    }

    constructor(username: String) {
        this.userName = username
        val userRecord1 = UserRecord(username)
        this.userRecordFile = userRecord1.recordFile
        this.gameMode = GameConstant.GAME_MODE_EASY
    }


}