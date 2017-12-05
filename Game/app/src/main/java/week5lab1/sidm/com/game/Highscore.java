package week5lab1.sidm.com.game;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Highscore extends Activity implements View.OnClickListener{

    //Defining the buttons
    private Button btn_goback;
    private TextView text;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Hide Top Bar (make it full screen)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Give context
        setContentView(R.layout.highscorepage);

        //Back Button
        btn_goback = (Button)findViewById(R.id.btn_goback);
        btn_goback.setOnClickListener(this); //Set Listener to this button
        text = (TextView)findViewById(R.id.btn_highscore);
        text.setText("Score: " + Integer.toString(Score.Instance.getScore()));
    }

    @Override
    public void onClick(View v) {
        //Intend - action to be performed.
        //Intend is an object that provides runtime bonding.
        // new instance of this object intent

        Intent intent = new Intent();

        if (v == btn_goback)
        {
            // intent --> to set to another class which another page or screen that we are launching
            intent.setClass(this, MainMenu.class);
        }

        startActivity(intent);
    }
}

