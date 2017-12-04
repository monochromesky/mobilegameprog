package week5lab1.sidm.com.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * Created by User on 25/11/2017.
 */

public class SGame
{
    public final static SGame Instance = new SGame();

    private Bitmap bmp;
    float offset = 0.f;
    float timer = 0.f;
    //Constructor does not allow anyone else to create another game
    private SGame()
    {

    }
    public void Update(float deltaTime)
    {
        timer += deltaTime;
        if (timer > 1.f)
        {
            HeartHP.Create();
            Hazards.Create();

            timer = 0.f;
        }
        EntityManager.Instance.Update(deltaTime);
    }
    public void Init(SurfaceView _view)
    {
        EntityManager.Instance.Init(_view);
        GameBackground.Create();
        platform.Create(1500, 600);
        platform.Create(-100,1100);
        platform.Create(200,1600);
        character.Create(200,1000);
        UI.Create();
        //Hazards.Create();
    }
    protected void Render (Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
    }

}

