package org.thcg.ui.window

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.thcg.core.Initializer
import org.thcg.page.MainPage
import org.thcg.util.UIConstant
import org.thcg.util.UIConstant.DEFAULT_TITLE
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import kotlin.system.exitProcess

/**
 * @author Severle
 * @date 2024-01-17 23:44:03
 * @description 主游戏窗口
 */
object MainWindow : JFrame(), UIConstant {
    private val log: Logger = LogManager.getLogger(MainWindow::class.java)
    private fun readResolve(): Any = MainWindow

    init {
        Initializer.initialize()
        initializeSettings()
        initializePages()
        addWindowListeners()
    }

    fun showIt() {
        isVisible = true
    }

    private fun initializeSettings() {
        title = DEFAULT_TITLE
        isResizable = false
        size = Dimension(1260, 840)
        defaultCloseOperation = DO_NOTHING_ON_CLOSE
        setLocationRelativeTo(null)
    }

    private fun initializePages() {
        val mainPage = MainPage()
        contentPane = mainPage
    }

    private fun closingWindow(): Boolean {
        return true
    }

    private fun beforeExit() {
        log.debug("Before exit")
        Initializer.terminal()
    }

    private fun addWindowListeners() {
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                if (closingWindow()) {
                    beforeExit()
                    exitProcess(0)
                }
            }
        })
    }
}