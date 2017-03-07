package square.com.avoidsquare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import square.com.avoidsquare.Utils.PreferenceUtil;

public class GameOverActivity extends AppCompatActivity {


    private TextView title;
    private TextView message;
    private TextView highestScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        configureViews();
    }

    private void configureViews() {
        title = (TextView) findViewById(R.id.title_text_view);
        message = (TextView) findViewById(R.id.message_text_view);
        highestScoreTextView = (TextView) findViewById(R.id.highScoreTextView);
        String newScore = (String) getIntent().getExtras().get(MainActivity.NEW_SCORE);
        String highestScore = (String) PreferenceUtil.readPreference(this,MainActivity.HIGH_SCORE,"");
        message.setText("Your score is : " + newScore);

        String string = "The Highest score is :  " + highestScore;
        highestScoreTextView.setText(string);

    }

    public void newGameButtonOnClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }


}
