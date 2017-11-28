package week5lab1.sidm.com.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.MotionEvent;
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

        setContentView(new Gameview(this));
        // TODO : NEEDS TO UNCOMMENT THIS
        //setContentView(R.layout.gamepage);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // Hijacking the touch event into our own system
        int x = (int) event.getX();
        int y = (int) event.getY();

        TouchManager.Instance.Update(x, y, event.getAction());

        return true;
    }

}


