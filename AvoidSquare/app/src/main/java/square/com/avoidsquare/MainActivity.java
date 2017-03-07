package square.com.avoidsquare;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import square.com.avoidsquare.Request.BaseActivity;
import square.com.avoidsquare.Utils.PreferenceUtil;

public class MainActivity extends BaseActivity implements GameInterface {
    public static final String ALL_SCORES = "ALL_SCORES";
    public static final String NEW_SCORE = "NEW_SCORE";
    public static final String HIGH_SCORE = "HIGH_SCORE";

    private EnemyViews enemyViewsFirst;
    private EnemyViews enemyViewsSecond;
    private EnemyViews enemyViewsThird;
    private EnemyViews enemyViewsFourth;
    private Gamer gamer;
    private ImageView joyStick;
    private Point screenSizes;
    private int colorOfEnemies;
    private float borderLength;
    private TextView timerTextView;
    private double endTime;
    private Double score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        screenSizes = point;
        borderLength = getResources().getDimension(R.dimen.borderLength);
        configViews(screenSizes);
    }


    private void screenBarsConfigurations() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void configViews(Point screenSizes) {
        enemyViewsFirst = (EnemyViews) findViewById(R.id.first);
        enemyViewsSecond = (EnemyViews) findViewById(R.id.second);
        enemyViewsThird = (EnemyViews) findViewById(R.id.third);
        enemyViewsFourth = (EnemyViews) findViewById(R.id.fourth);
        timerTextView = (TextView) findViewById(R.id.top_view);
        gamer = (Gamer) findViewById(R.id.gamer);
        joyStick = (ImageView) findViewById(R.id.joy_stick);
        joyStick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                onTouchJoyStickActionPerformed(motionEvent);
                return true;
            }
        });
        enemyViewsFirst.setGameOverInterface(this);
        enemyViewsSecond.setGameOverInterface(this);
        enemyViewsThird.setGameOverInterface(this);
        enemyViewsFourth.setGameOverInterface(this);
        gamer.setGameInterface(this);
    }

    private void onTouchJoyStickActionPerformed(MotionEvent motionEvent) {
        gamer.move(motionEvent.getX(),motionEvent.getY() );
        joyStick.setX(joyStick.getX() - joyStick.getLayoutParams().width / 2 + motionEvent.getX());
        joyStick.setY(joyStick.getY() - joyStick.getLayoutParams().height / 2 + motionEvent.getY());
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void gameOverBecauseOfEnemies(float enemX, float enemyY, int enemyWidth, int enemyHeight) {
        if (gamer.isBelongToGamer(enemX, enemyY) || gamer.isBelongToGamer(enemX, enemyY + enemyHeight)
                || gamer.isBelongToGamer(enemX + enemyWidth, enemyY) || gamer.isBelongToGamer(enemX + enemyWidth, enemyY + enemyHeight)) {
            stop();
        }
    }

    @Override
    public void startGame() {
        enemyViewsFirst.move();
        enemyViewsSecond.move();
        enemyViewsThird.move();
        enemyViewsFourth.move();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void gameOverBecauseOfOutOfBounds(float gamerX, float gamerY, int gamerWidth, int gamerHeight) {
        if (gamerX <= 0 || gamerX >= screenSizes.x - gamerWidth
                || gamerY <= 0 || gamerY + gamerHeight + borderLength >= screenSizes.y / 2) {
            stop();
        }
    }

    @Override
    public void timerMethod(double milliseconds) {
        timerTextView.setText(String.valueOf(milliseconds));
        endTime = milliseconds;
    }

    @Override
    public Point getScreenSizes() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void stop() {
        gamer.setImageResource(R.drawable.boom);
        joyStick.setOnTouchListener(null);
        gamer.setGameIsPlaying(false);
        checkingHighestScore();
        enemyViewsFirst.stop();
        enemyViewsSecond.stop();
        enemyViewsThird.stop();
        enemyViewsFourth.stop();
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra(NEW_SCORE, timerTextView.getText().toString());
        startActivity(intent);
        finish();
    }

    private void checkingHighestScore() {
        String highestScore = (String) PreferenceUtil.readPreference(this, MainActivity.HIGH_SCORE, "0.0");
        Double newScoreDouble = endTime;
        Double highScoreDouble = Double.valueOf(highestScore);
        if (newScoreDouble > highScoreDouble) {
            highScoreDouble = newScoreDouble;
        }
        PreferenceUtil.saveInSharedPreference(this, MainActivity.HIGH_SCORE, String.valueOf(highScoreDouble));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}
