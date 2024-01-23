package org.thcg

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application// 导入Lwjgl3Application类
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration// 导入Lwjgl3ApplicationConfiguration类
import org.thcg.util.DesktopConfigConstant.DEFAULT_TITLE // 导入DEFAULT_TITLE常量

/**
 * @author Severle
 * @date 2024-01-18 15:43:43
 * @description
 */
fun main() {
    val config = Lwjgl3ApplicationConfiguration()// 创建Lwjgl3ApplicationConfiguration对象并赋值给config变量
    config.setForegroundFPS(60)// 设置前台FPS为60
    config.setTitle(DEFAULT_TITLE)// 设置窗口标题为DEFAULT_TITLE的值
    Lwjgl3Application(GameCore(), config)// 创建Lwjgl3Application对象，并传入GameCore实例和config配置对象
}
