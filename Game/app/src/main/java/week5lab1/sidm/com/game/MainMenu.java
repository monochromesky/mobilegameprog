package week5lab1.sidm.com.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements View.OnClickListener{

    //define Buttons
    private Button btn_start;
    private Button btn_credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.mainmenu);

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this); //Set Listener to this button --> Start Button

        //btn_credits = (Button)findViewById(R.id.btn_credits);
        //btn_credits.setOnClickListener(this);
    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {
        //Intend - action to be performed.
        //Intend is an object that provides runtime bonding.
        // new instance of this object intent

        Intent intent = new Intent();

        if (v == btn_start)
        {
            // intent --> to set to another class which another page or screen that we are launching
            intent.setClass(this, Gamepage.class);
        }

        // else if (v == btn_credits)
        //{
           // intent.setClass(this, Credits.class);
        //}
        startActivity(intent);
    }
}

