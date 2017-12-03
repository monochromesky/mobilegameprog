package week5lab1.sidm.com.game;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

public class GameBackground implements EntityBase
{

    private Bitmap skybg = null;
    private Bitmap taintskybg = null;
    private Bitmap clouds = null;
   // private Bitmap clouds2 = null;

    private boolean isDone = false;

    private float xPos, yPos, offset;
    private SurfaceView view = null;

    // For fading animation
    private Paint alphaPaint = new Paint();
    private int currentAlpha;

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
        taintskybg = BitmapFactory.decodeResource(_view.getResources(), R.drawable.taintskybg);
        skybg = BitmapFactory.decodeResource(_view.getResources(), R.drawable.skybg);
        clouds = BitmapFactory.decodeResource(_view.getResources(), R.drawable.clouds);
        //clouds2 = BitmapFactory.decodeResource(_view.getResources(), R.drawable.clouds);


        offset = 0.0f;
        currentAlpha = 255;
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
        _canvas.drawBitmap(taintskybg, xPos - view.getWidth() * 0.5f, yPos - view.getHeight() * 0.5f, null);

        // Fading Animation
        if (currentAlpha > 0) {

            // TODO: If the player is going to die/Hazard reaching more than halfway, background slowly fades to TAINTEDSKY
            _canvas.drawBitmap(skybg, xPos - view.getWidth() * 0.5f, yPos - view.getHeight() * 0.5f, alphaPaint);
            currentAlpha -= 1;
            alphaPaint.setAlpha(currentAlpha);
        }

        // Clouds rendering
        float xOffsetcloud = (float)Math.sin(offset) * clouds.getWidth() * 0.3f;
        //_canvas.drawBitmap(clouds, xPos - clouds.getWidth() * 0.5f + xOffsetcloud, yPos - clouds.getHeight() * 0.5f, null);
        _canvas.drawBitmap(clouds, xPos - clouds.getWidth() * 0.5f + xOffsetcloud, yPos - clouds.getHeight() * 0.5f, null);

    }

    public static GameBackground Create()
    {
        GameBackground result = new GameBackground();
        EntityManager.Instance.AddEntity(result);
        return result;
    }


}


