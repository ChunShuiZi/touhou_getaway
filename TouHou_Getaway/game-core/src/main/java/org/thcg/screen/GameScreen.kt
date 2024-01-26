package org.thcg.screen


import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
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

        private val ENEMY_COLOR: Color = Color.BLUE
        private val ENEMY_SPAWN_CHANCE: Float = 0.05f
        private val MIN_ENEMY_SPEED_Y: Float = 75f
        private val MAX_ENEMY_SPEED_Y: Float = 125f
        private val ENEMY_WIDTH: Float = 25f
        private val ENEMY_HEIGHT: Float = 25f
        private val ENEMY_SHOOT_DESIRE: Float = 100f
    }

    private var inputProcessor: MyInputProcessor = MyInputProcessor(this)// 创建MyInputProcessor对象，并传入当前实例
    private var userBullets: MutableList<Bullet> = mutableListOf()  // 用于存储用户子弹对象的列表
    private var enemyBullets: MutableList<Bullet> = mutableListOf() // 用于存储敌机子弹对象的列表
    private var enemies: MutableList<Enemy> = mutableListOf() // 用于存储敌机对象的列表

    private var userShooting: Int = 0
    // 初始化人物位置
    private var userPositionX: Int = 300// 人物横坐标
    private var userPositionY: Int = 8// 人物纵坐标
    private var userWidth: Int = RADIUS
    private var userHeight: Int = RADIUS
    private var userSpeedX: Int = 0// 人物横向速度
    private var userSpeedY: Int = 0// 人物纵向速度
    private val scoreBoardPositionX: Int = 10//得分板横坐标
    private val scoreBoardPositionY: Int = 420//得分板纵坐标
    private var shape: ShapeRenderer = ShapeRenderer() // 创建ShapeRenderer对象，用于绘制形状
    private var viewport: ScreenViewport = ScreenViewport()// 创建ScreenViewport对象，用于处理屏幕显示区域
    private val userColor = Color.RED // 设置绘制颜色为红色
    private val bulletColor1 = Color.WHITE // 设置绘制颜色为白色
    private var slow = 0
    private var score = 0
    private val bonusPoint = 1

    private var upF = -1
    private var downF = -1
    private var leftF = -1
    private var rightF = -1

    init {
        addInputProcessor(inputProcessor)// 添加输入处理器到当前屏幕
    }

    override fun render(delta: Float) {
        viewport.apply()// 应用视图口设置
        userShapeInitialize()//自机渲染初始化
        userPositionUpdate()//自机位置更新
        userShotBullets(delta)//自机射击子弹
        scoreBoardDraw()//渲染得分
        enemyRandomGenerate() // 生成随机敌机
        enemyRemove(delta) // 移除已经飞出屏幕的敌机
        enemies.forEach { enemy -> enemyRandomShotBullets(enemy)}
        shape.color = Color.RED
        shape.end()
    }

    fun handleFeedbackData(data: Int) {
        when (data) {
            UP or Type.DOWN -> {
                if (slow==0)
                {userSpeedY += MOVE_STEP
                    upF=0}
                else
                {userSpeedY += SLOW_STEP
                    upF=1}
            }

            DOWN or Type.DOWN -> {
                if (slow==0)
                {userSpeedY -= MOVE_STEP
                    downF=0}
                else
                {userSpeedY -= SLOW_STEP
                    downF=1}
            }

            LEFT or Type.DOWN -> {
                if (slow==0)
                {userSpeedX -= MOVE_STEP
                    leftF=0}
                else
                {userSpeedX -= SLOW_STEP
                     leftF=1}
            }

            RIGHT or Type.DOWN -> {
                if (slow==0)
                {userSpeedX += MOVE_STEP
                    rightF=0}
                else
                {userSpeedX += SLOW_STEP
                    rightF=1}
            }

            UP or Type.RELEASE -> {
                if (upF==0)
                userSpeedY -= MOVE_STEP
                if (upF==1)
                userSpeedY -= SLOW_STEP
                upF = -1
            }

            DOWN or Type.RELEASE -> {
                if (downF==0)
                userSpeedY += MOVE_STEP
                if (downF==1)
                userSpeedY += SLOW_STEP
                downF = -1
            }

            LEFT or Type.RELEASE -> {
                if (leftF==0)
                userSpeedX += MOVE_STEP
                if (leftF==1)
                userSpeedX += SLOW_STEP
                leftF = -1
            }

            RIGHT or Type.RELEASE -> {
                if (rightF==0)
                userSpeedX -= MOVE_STEP
                if (rightF==1)
                userSpeedX -= SLOW_STEP
                rightF = -1
            }

            SHOT or Type.DOWN -> {
                userShooting = 1
            }

            SHOT or Type.RELEASE -> {
                userShooting = 0
            }

            SLOW or Type.DOWN -> {
                slow = 1
            }
            SLOW or Type.RELEASE -> {
                slow = 0
            }
        }
    }

    fun debugLocation() {
        if (log.isDebugEnabled) {
            log.debug("Now Location: ({}, {})", userPositionX, userPositionY)
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        shape.dispose()
    }

    private fun userShapeInitialize(){
        shape.projectionMatrix = viewport.camera.combined // 设置ShapeRenderer的投影矩阵为视图口相机的组合矩阵
        shape.begin(ShapeRenderer.ShapeType.Filled)// 开始填充形状绘制
        shape.circle(userPositionX.toFloat(), userPositionY.toFloat(), userWidth.toFloat())// 绘制一个圆形
        shape.color = userColor
        shape.end()// 结束形状绘制
    }

    private fun userPositionUpdate(){
        userPositionX += userSpeedX// 更新人物横坐标
        userPositionY += userSpeedY // 更新人物纵坐标
        if (userPositionX >= viewport.worldWidth - RADIUS)
            userPositionX = viewport.worldWidth.toInt() - RADIUS
        if (userPositionY >= viewport.worldHeight - RADIUS - TOP_BLANK_HEIGHT)
            userPositionY = viewport.worldHeight.toInt() - RADIUS - TOP_BLANK_HEIGHT
        if (userPositionX <= RADIUS)
            userPositionX = RADIUS
        if (userPositionY <= RADIUS)
            userPositionY = RADIUS
    }

    private fun userShotBullets(delta: Float){
        if(userShooting==1) { userBullets.add(Bullet(userPositionX, userPositionY, 5, 1)) }
        // 移除已过期的子弹并更新和绘制子弹
        userBullets.removeAll { bullet ->
            val hitEnemy = enemies.find { enemy -> enemy.enemyIsHitByBullet(bullet) }
            if (hitEnemy != null) {
                enemies.remove(hitEnemy)
                score = updateScore(score)
                true
            } else {
                bullet.isExpired()
            }
        }

        shape.begin(ShapeRenderer.ShapeType.Filled)

        for (bullet in userBullets) {
            shape.color = bulletColor1
            bullet.userBulletUpdate(delta)
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
        batch.end()
    }

    private fun updateScore(sc: Int) : Int{
        return sc + bonusPoint
    }

    //三角函数取值双驼峰分布在（-1,1）之间，驼峰分别为-√2/2和√2/2, 使得敌机主要分布在屏幕中央的附近, 调整输出分布在（0，1）之间
    private fun randomSineDistributionPattern(): Float{
        return MathUtils.sin(MathUtils.random(-1000000f,1000000f)) + 1
    }

    private fun randomCosineDistributionPattern(): Float{
        return MathUtils.cos(MathUtils.random(-1000000f,1000000f)) + 1
    }

    private fun enemyRandomGenerate(){
        if (MathUtils.randomBoolean(ENEMY_SPAWN_CHANCE)) {
            val enemyX = randomSineDistributionPattern() / 2 * (viewport.worldWidth- ENEMY_WIDTH)
            val enemyY = viewport.worldHeight
            val enemySpeedY = MathUtils.random(MIN_ENEMY_SPEED_Y, MAX_ENEMY_SPEED_Y)
            enemies.add(Enemy(enemyX, enemyY, ENEMY_WIDTH, ENEMY_HEIGHT, enemySpeedY))
        }
    }

    private fun enemyRandomShotBullets(enemy: Enemy){
        if(ENEMY_SHOOT_DESIRE > MathUtils.random(0,1000) && enemy.enemyPositionY > viewport.worldHeight * 3 / 4){
            enemyBullets.add(Bullet(enemy.enemyPositionX.toInt(), enemy.enemyPositionY.toInt(), 5, 1))
        }
        enemyBullets.removeAll { bullet ->
            val hitUser = enemyBullets.find{ bullet -> userIsHitByBullet(bullet)}
            if (hitUser != null) {
                score -= 50//中弹惩罚
                true
            } else {
                bullet.isExpired()
            }
        }

        shape.end()
        shape.begin(ShapeRenderer.ShapeType.Filled)

        for (bullet in enemyBullets){
            shape.color = bulletColor1
            bullet.enemyBulletUpdate(1.5f)
            bullet.draw(shape)
            shape.color = userColor
        }
        shape.end()
    }
    private fun userIsHitByBullet(bullet: Bullet): Boolean {
        return bullet.x >= userPositionX && bullet.x <= userPositionX + userWidth && bullet.y >= userPositionY && bullet.y <= userPositionY + userHeight
    }

    private fun enemyRemove(delta: Float){
        enemies.removeAll { enemy -> enemy.enemyIsOutOfScreen(viewport.worldHeight) }

        shape.begin(ShapeRenderer.ShapeType.Filled)
        shape.color = ENEMY_COLOR

        for (enemy in enemies) {
            enemy.update(delta)
            enemy.draw(shape)
        }
    }
}

