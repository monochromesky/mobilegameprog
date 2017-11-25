package week5lab1.sidm.com.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

/**
 * Created by User on 19/11/2017.
 */

public class Gamepage extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);

        //Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setContentView(new Gameview(this));

        setContentView(R.layout.gamepage);
    }

}


