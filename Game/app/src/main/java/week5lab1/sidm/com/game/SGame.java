package week5lab1.sidm.com.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

/**
 * Created by User on 25/11/2017.
 */

public class SGame
{
    public final static SGame Instance = new SGame();
    boolean _active = true;
    // Initialize score
    Paint score;

    private Bitmap bmp;
    float offset = 0.f;
    float timer = 0.f;
    int points, hp;

    //pause
    private boolean isPaused=false;

    //Constructor does not allow anyone else to create another game
    private SGame()
    {

    }
    public void Update(float deltaTime)
    {
        EntityManager.Instance.updatepausebutton(deltaTime);

        if(SGame.Instance.GetIsPaused())
        {
            return;
        }

        timer += deltaTime;
        if (timer > 1.f)
        {
            HeartHP.Create(LayerConstants.GAMEOBJECTS_LAYER);
            Hazards.Create(LayerConstants.GAMEOBJECTS_LAYER);

            timer = 0.f;
            points += 1;
           // hp -= 1;
        }
        EntityManager.Instance.Update(deltaTime);

        //Highscore

        if (points > 9999) //Limit
            points = 9999;
        else if (points <= 0)
        {
          //TODO: If timer reaches 0/health reaches 0, redirect to highscore page
        }
        Score.Instance.setScore(points);


    }
    public void Init(SurfaceView _view)
    {
        //Setting
        score = new Paint(Paint.ANTI_ALIAS_FLAG);
        score.setColor(Color.rgb(255,255,255));
        score.setTextSize(80);

        EntityManager.Instance.Init(_view);
        GameBackground.Create();
        //platform.Create(LayerConstants.GAMEOBJECTS_LAYER);
        platform.Create(100, 500);
        platform.Create(300, 500);
        platform.Create(500, 500);
        platform.Create(800, 500);
        platform.Create(700, 800);
        platform.Create(100,1100);
        platform.Create(550,1400);
        character.Create(500,50);
        UI.Create(LayerConstants.UI_LAYER);
        //Hazards.Create();

        //Highscore
        points = 0;
        //hp = 3;
       // HealthSystem.Instance.setHealth(hp);
        //AudioManager.Instance.PlayAudio(R.raw.background_music);
        AudioManager.Instance.PlayAudio(R.raw.nlctown);

        //Create (and set render layer)
       // character.Create(LayerConstants.GAMEOBJECTS_LAYER);
        //character.Create(1000,0);

        //create pause here
       SamplePauseButton.Create();

    }
    protected void Render (Canvas _canvas)
    {
        // Renders Entities
        EntityManager.Instance.Render(_canvas);

        // Renders Highscore on the HUD
        _canvas.drawText(Integer.toString(Score.Instance.getScore()), 140, 1720, score);
    }

    //Pause
    public boolean GetIsPaused()
    {
        return isPaused;
    }

    public void SetIsPaused(boolean _newPause)
    {
        isPaused = _newPause;
    }
}

