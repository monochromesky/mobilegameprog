package week5lab1.sidm.com.game;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class SampleBackground implements EntityBase
{

    private Bitmap bmp = null;
    private Bitmap clouds = null;
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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.taintskybg);
        clouds = BitmapFactory.decodeResource(_view.getResources(), R.drawable.hearthp);

        offset = 0.0f;
        view = _view;
    }

    @Override
    public void Update(float _dt)
    {
        offset += _dt * 0.8f;
    }

    @Override
    public void Render(Canvas _canvas)
    {
        xPos = 0.5f * view.getWidth();
        yPos = 0.5f * view.getHeight();

        //float xOffsetbg = (float)Math.sin(offset) * bmp.getWidth() * 0.3f;
        _canvas.drawBitmap(bmp, xPos - view.getWidth() * 0.5f, yPos - view.getHeight() * 0.5f, null);

        float xOffsetcloud = (float)Math.sin(offset) * clouds.getWidth() * 0.3f;
        _canvas.drawBitmap(clouds, xPos - clouds.getWidth() * 0.5f + xOffsetcloud, yPos - clouds.getHeight() * 0.5f, null);
    }

    public static SampleBackground Create()
    {
        SampleBackground result = new SampleBackground();
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
