package week5lab1.sidm.com.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by User on 19/11/2017.
 */

public class Splashpage extends Activity {

    boolean _active = true;
    int _splashTime = 5000; //time for the splash screen to be displayed in this seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splashpage);

        //thread for displaying the Splash Screen
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(200);
                        if(_active) {
                            waited += 200;
                        }
                    }
                } catch(InterruptedException e) {
                    //do nothing
                } finally {
                    finish();
//Create new activity based on and intent with CurrentActivity
                    Intent intent = new Intent(Splashpage.this, MainMenu.class);
                    startActivity(intent);
                }
            }
        };
        splashTread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            _active = false;

        }
        return true;
    }
}
