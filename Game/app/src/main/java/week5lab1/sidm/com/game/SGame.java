package week5lab1.sidm.com.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    //Constructor does not allow anyone else to create another game
    private SGame()
    {

    }
    public void Update(float deltaTime)
    {
        offset += deltaTime;
    }
    public void Init(SurfaceView _view)
    {
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ship2_1);

    }
    protected void Render (Canvas _canvas)
    {
        int currOffset = (int)(offset * 100.f);
        _canvas.drawBitmap(bmp, currOffset, 10, null);
    }

}
