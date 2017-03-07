package square.com.avoidsquare.Request;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import square.com.avoidsquare.R;

public class RequestLoadingMontion extends ProgressBar {
    private RelativeLayout relativeLayout;

    public RequestLoadingMontion(Context context) {
        super(context);
    }

    public RequestLoadingMontion(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RequestLoadingMontion(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RequestLoadingMontion(ViewGroup topView, Context context) {
        super(context);
        relativeLayout = new RelativeLayout(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(layoutParams);

        relativeLayout.addView(this);

        topView.addView(relativeLayout);
        relativeLayout.setClickable(true);
        relativeLayout.setVisibility(INVISIBLE);
        relativeLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.loadingBackground));
    }

    public void show()
    {
        relativeLayout.setVisibility(VISIBLE);
    }

    public void hide()
    {
        relativeLayout.setVisibility(INVISIBLE);
    }
}
