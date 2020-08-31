package com.looptrace.gadsleaderboard.helpers;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

public class SetStatusBarColor {
    private final Context mContext;

    public SetStatusBarColor(Context context) {
        mContext = context;
    }


//    public static void SetStatusColor(){
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
//            View decorView = get getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_IMMERSIVE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            );
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//            getWindow().setNavigationBarColor(Color.TRANSPARENT);
//        }
//    }
//    }
}
