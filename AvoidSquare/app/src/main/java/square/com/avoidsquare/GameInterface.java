package square.com.avoidsquare;

import android.graphics.Point;

/**
 * Created by Robert on 23.01.2017.
 */

public interface GameInterface {
    void gameOverBecauseOfEnemies(float enemX, float enemyY, int enemyWidth, int enemyHeight);
    void startGame();
    void gameOverBecauseOfOutOfBounds(float gamerX, float gamerY, int gamerWidth, int gamerHeight);
    void timerMethod(double milliseconds);
    Point getScreenSizes();
}
