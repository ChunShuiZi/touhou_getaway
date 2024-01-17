package org.thcg.page

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.*
import javax.swing.JPanel

/**
 * @author Severle
 * @date 2024-01-18 00:06:34
 * @description 主页面类,其他页面可以继承此页面来实现绘制内容
 */
open class Page : JPanel() {
    private val mouseListener = object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent?) {
            onMouseClicked(e)
        }

        override fun mousePressed(e: MouseEvent?) {
            onMousePressed(e)
        }

        override fun mouseReleased(e: MouseEvent?) {
            onMouseReleased(e)
        }

        override fun mouseEntered(e: MouseEvent?) {
            onMouseEntered(e)
        }

        override fun mouseExited(e: MouseEvent?) {
            onMouseExited(e)
        }

        override fun mouseWheelMoved(e: MouseWheelEvent?) {
            onMouseWheelMoved(e)
        }

        override fun mouseDragged(e: MouseEvent?) {
            onMouseDragged(e)
        }

        override fun mouseMoved(e: MouseEvent?) {
            onMouseMoved(e)
        }
    }

    private val keyListener = object : KeyListener {
        override fun keyTyped(e: KeyEvent?) {
            onKeyTyped(e)
        }

        override fun keyPressed(e: KeyEvent?) {
            onKeyPressed(e)
        }

        override fun keyReleased(e: KeyEvent?) {
            onKeyReleased(e)
        }

    }

    private val focusListener = object : FocusListener {
        override fun focusGained(e: FocusEvent?) {
            onFocusGained(e)
        }

        override fun focusLost(e: FocusEvent?) {
            onFocusLost(e)
        }
    }

    private val componentListener = object : ComponentListener {
        override fun componentResized(e: ComponentEvent?) {
            onComponentResized(e)
        }

        override fun componentMoved(e: ComponentEvent?) {
            onComponentMoved(e)
        }

        override fun componentShown(e: ComponentEvent?) {
            onComponentShown(e)
        }

        override fun componentHidden(e: ComponentEvent?) {
            onComponentHidden(e)
        }
    }

    private val containerListener = object : ContainerListener {
        override fun componentAdded(e: ContainerEvent?) {
            onComponentAdded(e)
        }

        override fun componentRemoved(e: ContainerEvent?) {
            onComponentRemoved(e)
        }

    }

    private val inputMethodListener = object : InputMethodListener {
        override fun inputMethodTextChanged(e: InputMethodEvent?) {
            onInputMethodTextChanged(e)
        }

        override fun caretPositionChanged(e: InputMethodEvent?) {
            onCaretPositionChanged(e)
        }

    }

    init {
        super.addMouseListener(this.mouseListener)
        super.addMouseWheelListener(this.mouseListener)
        super.addMouseMotionListener(this.mouseListener)
        super.addKeyListener(this.keyListener)
        super.addFocusListener(this.focusListener)
        super.addComponentListener(this.componentListener)
        super.addContainerListener(this.containerListener)
        super.addInputMethodListener(this.inputMethodListener)
    }

    override fun paintComponent(g: Graphics?) {
        onPaint(g as Graphics2D)
        g.dispose()
    }

    open fun onPaint(g: Graphics2D) {}
    open fun onMouseClicked(e: MouseEvent?) {}
    open fun onMousePressed(e: MouseEvent?) {}
    open fun onMouseReleased(e: MouseEvent?) {}
    open fun onMouseEntered(e: MouseEvent?) {}
    open fun onMouseExited(e: MouseEvent?) {}
    open fun onMouseWheelMoved(e: MouseWheelEvent?) {}
    open fun onMouseDragged(e: MouseEvent?) {}
    open fun onMouseMoved(e: MouseEvent?) {}
    open fun onKeyTyped(e: KeyEvent?) {}
    open fun onKeyPressed(e: KeyEvent?) {}
    open fun onKeyReleased(e: KeyEvent?) {}
    open fun onFocusGained(e: FocusEvent?) {}
    open fun onFocusLost(e: FocusEvent?) {}
    open fun onComponentResized(e: ComponentEvent?) {}
    open fun onComponentMoved(e: ComponentEvent?) {}
    open fun onComponentShown(e: ComponentEvent?) {}
    open fun onComponentHidden(e: ComponentEvent?) {}
    open fun onComponentAdded(e: ContainerEvent?) {}
    open fun onComponentRemoved(e: ContainerEvent?) {}
    open fun onInputMethodTextChanged(e: InputMethodEvent?) {}
    open fun onCaretPositionChanged(e: InputMethodEvent?) {}
}