package org.thcg

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Interpolation
import de.eskalon.commons.core.ManagedGame
import de.eskalon.commons.screen.ManagedScreen
import de.eskalon.commons.screen.transition.ScreenTransition
import de.eskalon.commons.screen.transition.impl.BlendingTransition
import org.thcg.core.Disposer
import org.thcg.core.Initializer
import org.thcg.screen.GameScreen


class GameCore : ManagedGame<ManagedScreen, ScreenTransition>() {

    private lateinit var _batch: SpriteBatch
    val batch: SpriteBatch get() = _batch
    override fun create() {
        super.create()
        Initializer.initialize()
        this._batch = SpriteBatch()
        this.screenManager.setAutoDispose(true, true)
        this.screenManager.pushScreen(GameScreen(), BlendingTransition(_batch, 1f, Interpolation.pow2In))
    }

    override fun dispose() {
        Disposer.dispose()
    }
}

