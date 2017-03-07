package square.com.avoidsquare;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import java.util.Timer;
import java.util.TimerTask;

import square.com.avoidsquare.Utils.MyApplicaton;
import square.com.avoidsquare.Utils.Stopwatch;

/**
 * Created by Robert on 23.01.2017.
 */

public class EnemyViews extends View {

    private int moveX;
    private int moveY;
    private int screenHeight;
    private int screenWidth;
    private float enemyX;
    private float enemyY;
    int enemyHeight;
    int enemyWidth;
    private GameInterface gameOverInterface;
    private ValueAnimator animator;
    private Stopwatch stopwatch;
    Timer t = new Timer();

    public void setGameOverInterface(GameInterface gameOverInterface) {
        this.gameOverInterface = gameOverInterface;
    }
    //    int colorA;
//    int colorR;
//    int colorG;
//    int colorB;

    public EnemyViews(Context context) {
        super(context);
        initializing(context);
    }

    public EnemyViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializing(context);
    }

    public EnemyViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializing(context);
    }

    public void move() {
        stopwatch = new Stopwatch();
        animator = ValueAnimator.ofFloat(0.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animationUpdateActionPerformed();
            }
        });
        animator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void stop(){
        if(animator != null){
            animator.pause();
        }
    }

    private void initializing(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        moveX = 2;
        moveY = 2;
        screenWidth = point.x;
        screenHeight = point.y;
    }

    private void animationUpdateActionPerformed() {
        enemyX = this.getX();
        enemyY = this.getY();
        double currentTime = stopwatch.elapsedTime();
        enemyHeight = getLayoutParams().height;
        enemyWidth =  getLayoutParams().width ;
        if (enemyX < 0 || enemyX + enemyWidth > screenWidth) {
            moveX = -moveX;
        }
        if (enemyY < 0 || enemyY +enemyHeight > screenHeight/2 ) {
            moveY = -moveY;
        }
        this.setX(enemyX + moveX);
        this.setY(enemyY + moveY);
        gameOverInterface.gameOverBecauseOfEnemies(enemyX,enemyY,enemyWidth,enemyHeight);
        gameOverInterface.timerMethod(currentTime);

    }
}
