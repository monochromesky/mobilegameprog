package week5lab1.sidm.com.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.util.Random;

public class HeartHP implements EntityBase, Collidable
{
  //  private Bitmap bmp = null;
    private Bitmap hearthp = null;

    private boolean isDone = false;
    private float xPos, yPos, xDir, yDir, lifeTime;

    private int currentAlpha;
    private Paint alphaPaint = new Paint();
    private int renderLayer = 0;
    private boolean isInit = false;

    @Override
    public boolean IsDone() {
        //return health <= 0; E.g
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
       // bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ship2_1);
        hearthp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.hearthp);

        lifeTime = 5.0f;
        Random ranGen = new Random();

        xPos = ranGen.nextFloat() * _view.getWidth();
        yPos = 0;

        //xPos = ranGen.nextFloat() * _view.getWidth();
        //yPos = ranGen.nextFloat() * _view.getHeight();

        //xDir = ranGen.nextFloat() * 100.0f - 50.f;
        //yDir = ranGen.nextFloat() * 100.0f - 50.f;

        currentAlpha = 255;
    }

    @Override
    public void Update(float _dt) {
        lifeTime -= _dt;

        if(lifeTime < 0.f)
        {
            SetIsDone(true);
        }
        //xPos += xDir * _dt;
        yPos += 150 * _dt;

        //xPos *= _dt;
        //yPos *= _dt;

        // If user clicks on object, remove object (it dies)
        // if (android.gettouch..etc)
        // Handle the touch and check collision with click/touch


        // ABSTRACTION - Can modify for another platform easily
        if (TouchManager.Instance.IsDown())
        {
            // Check Collision here
            float imgRadius = hearthp.getHeight() * 0.5f;

            if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(),
                    0.0f, xPos, yPos, imgRadius))
            {
                // Collided!
                SetIsDone(true);
            }
        }
    }

    @Override
    public void Render(Canvas _canvas) {

        // Fading Animation
        //if (currentAlpha > 0) {
           // _canvas.drawBitmap(skybg, xPos - view.getWidth() * 0.5f, yPos - view.getHeight() * 0.5f, alphaPaint);
            //currentAlpha -= 1;
            //alphaPaint.setAlpha(currentAlpha);

            // Centers the image, makes sure it isn't cut off
        //if (currentAlpha > 100) {
            _canvas.drawBitmap(hearthp, xPos - hearthp.getWidth() * 0.5f, yPos - hearthp.getHeight() * 0.5f, null);

    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public boolean isPlatform()
    {
        return false;
    }
    public static HeartHP Create()
    {
        HeartHP result = new HeartHP();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    @Override
    public String GetType() {
        return "HeartHP";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetRadius() {
        return hearthp.getHeight() * 0.5f;
    }

    @Override
    public int GetRenderLayer()
    {
        return renderLayer;
    }

    @Override
    public void SetRenderLayer(int _newLayer)
    {
        renderLayer = _newLayer;
    }
    public static HeartHP Create(int _layer)
    {
        HeartHP result = Create();
        result.SetRenderLayer(_layer);
        return result;
    }
    @Override
    public void OnHit(Collidable _other) {
        if (_other.GetType() == "HeartHP")
        {
            SetIsDone(true);
        }
    }
}

