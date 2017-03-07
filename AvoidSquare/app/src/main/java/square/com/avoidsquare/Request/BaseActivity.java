package square.com.avoidsquare.Request;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import square.com.avoidsquare.Utils.MyApplicaton;


public class BaseActivity extends AppCompatActivity implements RequestLoadingMontionInterface {
    RequestLoadingMontion requestLoadingMontion;
    public static  int navBarHeight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");


        navBarHeight = resources.getDimensionPixelSize(resourceId);
    }

    @Override
    public RequestLoadingMontion getRequestLoadingMontion() {
        if (null == requestLoadingMontion){
            if (findViewById(android.R.id.content) != null){
                requestLoadingMontion = new RequestLoadingMontion((ViewGroup)findViewById(android.R.id.content),getApplicationContext());
            }
        }
        return requestLoadingMontion;
    }

}