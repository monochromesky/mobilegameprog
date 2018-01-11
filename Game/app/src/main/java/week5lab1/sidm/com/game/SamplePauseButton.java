package week5lab1.sidm.com.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceView;

public class SamplePauseButton implements EntityBase, Collidable
{

    private boolean isDone = false;
    private Bitmap bmp = null;
    private float xPos, yPos , offset;
    private SurfaceView view = null;
    private boolean IsInit = false;
    private int renderLayer = 0;
    boolean touched = false;
    private boolean isInit = false;

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;

    }

    @Override
    public boolean isPlatform() {
        return false;
    }

    @Override
    public void Init(SurfaceView _view) {
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.button);
        view = _view;
        offset = 0.0f;
        // xPos = view.getWidth() * 0.5f;
        // yPos =  view.getHeight() * 0.5f;
    }

    @Override
    public void Update(float _dt)
    {
        boolean paused = false;
        if(TouchManager.Instance.IsDown() && !touched)
        {
            touched = true;
        }
        else if(!TouchManager.Instance.IsDown() && touched)
        {
            // Check collision here
            touched = false;
            float imgRadius = bmp.getHeight() * 0.5f;
            if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(),
                    TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius))
            {
                // Collision
                SGame.Instance.SetIsPaused(!SGame.Instance.GetIsPaused());
            }
            paused=true;
        }

    }

    @Override
    public void Render(Canvas _canvas)
    {
        // [This is to scale and move the button]
        // Matrix transform = new Matrix();
        // transform.postTranslate(0.5f * view.getWidth(), 0.5f * view.getHeight());
        // transform.postScale(0.5f, 0.5f);
        // transform.postTranslate(xPos, 10);
        // _canvas.drawBitmap(bmp, transform, null);

        xPos = 0.5f * view.getWidth();
        yPos = 0.5f * view.getHeight();
        float xOffset = (float) Math.sin(offset) * bmp.getWidth() * 0.3f;
        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f + xOffset,
                yPos - bmp.getHeight() * 0.5f, null);

    }

    @Override
    public boolean IsInit() {
        return IsInit;
    }

    @Override
    public int GetRenderLayer()
    {
        return LayerConstants.UI_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer)
    {
        return;
    }

    public static SamplePauseButton Create() {
        SamplePauseButton result = new SamplePauseButton();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    @Override
    public String GetType()
    {
        return "button";
    }

    @Override
    public float GetPosX()
    {
        return xPos;
    }

    @Override
    public float GetPosY()
    {
        return yPos;
    }

    @Override
    public float GetRadius()
    {
        return bmp.getHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other)
    {

    }
}

