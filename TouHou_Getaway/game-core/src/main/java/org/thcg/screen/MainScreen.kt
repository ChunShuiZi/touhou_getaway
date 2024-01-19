package org.thcg.screen

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.kotcrab.vis.ui.widget.VisTextButton
import de.eskalon.commons.screen.ManagedScreenAdapter
import ktx.scene2d.vis.visTextTooltip
import org.thcg.util.game

/**
 * @author Severle
 * @date 2024-01-19 15:31:00
 * @description
 */
class MainScreen : ManagedScreenAdapter() {
    private val viewport: Viewport = ScreenViewport()
    private val stage: Stage = Stage(viewport, game.batch)
    init {
        val startBtn = VisTextButton("Start")
        startBtn.visTextTooltip("Start Game")
        stage.addActor(startBtn)
    }
    override fun render(delta: Float) {
        stage.draw()
    }
}