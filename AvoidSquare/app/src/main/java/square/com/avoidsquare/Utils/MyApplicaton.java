package square.com.avoidsquare.Utils;

import android.app.Application;
import android.content.Context;


/**
 * Created by anahit on 10/21/16.
 */

public class MyApplicaton extends Application {

    static MyApplicaton staticMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        staticMyApplication = this;
    }

    public static MyApplicaton myApplicationinstance(){
        return staticMyApplication;
    }

    public static Context getMyApplicationContext(){
        return MyApplicaton.myApplicationinstance().getApplicationContext();
    }


//    public static int getNavigationBarHeight(){
//        Resources resources = getMyApplicationContext().getResources();
//        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            return resources.getDimensionPixelSize(resourceId);
//        }
//        return 0;
//    }
}
