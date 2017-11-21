package week5lab1.sidm.com.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by User on 21/11/2017.
 */

public class Creditspage extends Activity{



    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        //Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.creditspage);

    }

}
