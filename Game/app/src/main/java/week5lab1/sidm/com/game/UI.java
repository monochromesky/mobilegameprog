package week5lab1.sidm.com.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

/**
 * Created by User on 4/12/2017.
 */

public class UI implements EntityBase {

    // TODO : ADD UI


    private Bitmap bottomui = null;

    private boolean isDone = false;

    private float xPos, yPos, offset;
    private SurfaceView view = null;

    @Override
    public boolean IsDone()
    {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone)
    {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view)
    {
        // bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.gamescene);
        bottomui = BitmapFactory.decodeResource(_view.getResources(), R.drawable.bottomui);

        offset = 0.0f;
        view = _view;
    }

    @Override
    public void Update(float _dt)
    {
        offset += _dt * 0.1f;
    }

    @Override
    public void Render(Canvas _canvas)
    {
        xPos = 0.5f * view.getWidth();
        yPos = 0.5f * view.getHeight();

        // Sky BG
        // _canvas.drawBitmap(skybg, xPos - view.getWidth() * 0.5f, yPos - view.getHeight() * 0.5f, null);
        _canvas.drawBitmap(bottomui, xPos - view.getWidth() * 0.5f, yPos - view.getHeight() * 0.5f, null);

    }

    public static UI Create()
    {
        UI result = new UI();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

}
