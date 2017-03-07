package square.com.avoidsquare;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by anahit on 3/7/17.
 */

public class GameView extends RelativeLayout {

    View globalView ;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initialize(Context context){
        globalView =  LayoutInflater.from(context).inflate(R.layout.game_layout, this, true);
    }


}
