package square.com.avoidsquare;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import square.com.avoidsquare.Utils.MyApplicaton;

/**
 * Created by Robert on 23.01.2017.
 */

public class Gamer extends ImageView {

    private float gamerX;
    private float gamerY;
    private GameInterface gameInterface;
    private Point screenSizes;
    public volatile boolean gameIsPlaying = false;


    public void setGameIsPlaying(boolean gameIsPlaying) {
        this.gameIsPlaying = gameIsPlaying;
    }

    public boolean isGameIsPlaying() {
        return gameIsPlaying;
    }

    public void setGameInterface(GameInterface gameInterface) {
        this.gameInterface = gameInterface;
    }

    public Gamer(Context context) {
        super(context);
        initializing(context);
    }

    public Gamer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializing(context);
    }

    public Gamer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializing(context);
    }

    private void initializing(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        screenSizes = point;
        setOnTouchListener(gamerOnTouchListener);
        setFocusableInTouchMode(true);
    }


    public void move(float mX, float mY) {
        if (!gameIsPlaying) {
            gameInterface.startGame();
            gameIsPlaying = true;
        }
        gamerX = this.getX();
        gamerY = this.getY();
        setX(gamerX - getLayoutParams().width / 2 + mX);
        setY(gamerY - getLayoutParams().height / 2 + mY);
        gameInterface.gameOverBecauseOfOutOfBounds(gamerX, gamerY, getLayoutParams().width, getLayoutParams().height);
    }

    private void onTouchActionPerformed(MotionEvent event) {
//        if (!gameIsPlaying) {
//            gameInterface.startGame();
//            gameIsPlaying = true;
//        }
//            gamerX = this.getX() ;
//            gamerY = this.getY() ;
//            setX(gamerX - getLayoutParams().width/2+ event.getX());
//            setY(gamerY - getLayoutParams().height/2 + event.getY());
//            gameInterface.gameOverBecauseOfOutOfBounds(gamerX, gamerY ,getLayoutParams().width,getLayoutParams().height);

    }


    OnTouchListener gamerOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            onTouchActionPerformed(motionEvent);
            return true;
        }
    };

//    public boolean isBelongToGamer(float x, float y) {
//        if (gamerY == 0 && gamerX == 0) {
//            return false;
//        }
//        return x > gamerX && x < gamerX + getLayoutParams().width && y > gamerY && y < gamerY + getLayoutParams().height;
//    }

    public boolean isBelongToGamer(float x, float y) {
        return Math.pow(getWidth() / 2, 2) >= Math.pow(x - (getX() + getWidth() / 2), 2) + Math.pow(y - (getY() + getHeight() / 2), 2);
    }
}
