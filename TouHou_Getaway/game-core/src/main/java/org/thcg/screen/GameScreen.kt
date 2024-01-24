package org.thcg.screen


import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.ScreenViewport
import de.eskalon.commons.screen.ManagedScreenAdapter
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.thcg.input.MyInputProcessor
import org.thcg.input.MyInputProcessor.Type
import org.thcg.util.GameConstant.*

/**
 * @author Severle
 * @date 2024-01-19 15:15:08
 * @description
 */
class GameScreen : ManagedScreenAdapter() {
    private companion object {
        private val log: Logger = LogManager.getLogger(GameScreen::class.java)// 创建Logger对象，用于日志记录

    }

    private var inputProcessor: MyInputProcessor = MyInputProcessor(this)// 创建MyInputProcessor对象，并传入当前实例
    private var bullets: MutableList<Bullet> = mutableListOf()  // 用于存储子弹对象的列表
    private var shot: Int = 0
    // 初始化人物位置
    private var playerPositionX: Int = 300// 人物横坐标
    private var playerPositionY: Int = 8// 人物纵坐标
    private var playerSpeedX: Int = 0// 人物横向速度
    private var playerSpeedY: Int = 0// 人物纵向速度
    private val scoreBoardPositionX: Int = 10//得分板横坐标
    private val scoreBoardPositionY: Int = 420//得分板纵坐标
    private var shape: ShapeRenderer = ShapeRenderer() // 创建ShapeRenderer对象，用于绘制形状
    private var viewport: ScreenViewport = ScreenViewport()// 创建ScreenViewport对象，用于处理屏幕显示区域
    private val userColor = Color.RED // 设置绘制颜色为红色
    private val bulletColor1 = Color.WHITE // 设置绘制颜色为红色
    private var slow = 0
    private var score = 1

    private var upF = -1
    private var downF = -1
    private var leftF = -1
    private var rightF = -1

    init {
        addInputProcessor(inputProcessor)// 添加输入处理器到当前屏幕
    }

    private fun userShapeInitialize(){
        shape.projectionMatrix = viewport.camera.combined // 设置ShapeRenderer的投影矩阵为视图口相机的组合矩阵
        shape.begin(ShapeRenderer.ShapeType.Filled)// 开始填充形状绘制
        shape.circle(playerPositionX.toFloat(), playerPositionY.toFloat(), RADIUS.toFloat())// 绘制一个圆形
        shape.color = userColor
        shape.end()// 结束形状绘制
    }

    private fun userPositionUpdate(){
        playerPositionX += playerSpeedX// 更新人物横坐标
        playerPositionY += playerSpeedY // 更新人物纵坐标
        if (playerPositionX >= viewport.worldWidth - RADIUS)
            playerPositionX = viewport.worldWidth.toInt() - RADIUS
        if (playerPositionY >= viewport.worldHeight - RADIUS - TOP_BLANK_HEIGHT)
            playerPositionY = viewport.worldHeight.toInt() - RADIUS - TOP_BLANK_HEIGHT
        if (playerPositionX <= RADIUS)
            playerPositionX = RADIUS
        if (playerPositionY <= RADIUS)
            playerPositionY = RADIUS
    }

    private fun userShotBullets(delta: Float){
        if(shot==1) { bullets.add(Bullet(playerPositionX, playerPositionY, 5, 1)) }
        // 移除已过期的子弹并更新和绘制子弹
        bullets.removeAll { bullet -> bullet.isExpired() }

        shape.begin(ShapeRenderer.ShapeType.Filled)

        for (bullet in bullets) {
            shape.color = bulletColor1
            bullet.update(delta)
            bullet.draw(shape)
            shape.color = userColor
        }
        shape.end()
    }

    private fun scoreBoardDraw(){
        val font = BitmapFont()
        val batch = SpriteBatch()
        val glyphLayout = GlyphLayout()
        val message = "Score:$score"
        glyphLayout.setText(font, message)
        batch.begin()
        font.draw(batch, glyphLayout, (scoreBoardPositionX).toFloat(),(scoreBoardPositionY).toFloat())
        score = updateScore(score)
        batch.end()
    }

    private fun updateScore(sc: Int) : Int{
        //更新得分，可以在以后改成击落敌机得分
        var sc = score + 1
        return sc
    }

    override fun render(delta: Float) {
        viewport.apply()// 应用视图口设置
        userShapeInitialize()//自机渲染初始化
        userPositionUpdate()//自机位置更新
        userShotBullets(delta)//自机射击子弹
        scoreBoardDraw()//渲染得分
    }

    fun handleFeedbackData(data: Int) {
        when (data) {
            UP or Type.DOWN -> {
                if (slow==0)
                {playerSpeedY += MOVE_STEP
                    upF=0}
                else
                {playerSpeedY += SLOW_STEP
                    upF=1}
            }

            DOWN or Type.DOWN -> {
                if (slow==0)
                {playerSpeedY -= MOVE_STEP
                    downF=0}
                else
                {playerSpeedY -= SLOW_STEP
                    downF=1}
            }

            LEFT or Type.DOWN -> {
                if (slow==0)
                {playerSpeedX -= MOVE_STEP
                    leftF=0}
                else
                {playerSpeedX -= SLOW_STEP
                     leftF=1}
            }

            RIGHT or Type.DOWN -> {
                if (slow==0)
                {playerSpeedX += MOVE_STEP
                    rightF=0}
                else
                {playerSpeedX += SLOW_STEP
                    rightF=1}
            }

            UP or Type.RELEASE -> {
                if (upF==0)
                playerSpeedY -= MOVE_STEP
                if (upF==1)
                playerSpeedY -= SLOW_STEP
                upF = -1
            }

            DOWN or Type.RELEASE -> {
                if (downF==0)
                playerSpeedY += MOVE_STEP
                if (downF==1)
                playerSpeedY += SLOW_STEP
                downF = -1
            }

            LEFT or Type.RELEASE -> {
                if (leftF==0)
                playerSpeedX += MOVE_STEP
                if (leftF==1)
                playerSpeedX += SLOW_STEP
                leftF = -1
            }

            RIGHT or Type.RELEASE -> {
                if (rightF==0)
                playerSpeedX -= MOVE_STEP
                if (rightF==1)
                playerSpeedX -= SLOW_STEP
                rightF = -1
            }

            SHOT or Type.DOWN -> {
                shot=1
            }

            SHOT or Type.RELEASE -> {
                shot=0
            }

            SLOW or Type.DOWN -> {
                slow=1
            }
            SLOW or Type.RELEASE -> {
                slow=0
            }
        }
    }

    fun debugLocation() {
        if (log.isDebugEnabled) {
            log.debug("Now Location: ({}, {})", playerPositionX, playerPositionY)
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        shape.dispose()
    }
}
