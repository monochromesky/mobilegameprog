package week5lab1.sidm.com.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class SampleEntity implements EntityBase, Collidable
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos, yPos, xDir, yDir, lifeTime;

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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ship2_1);

        lifeTime = 5.0f;
        Random ranGen = new Random();

        xPos = ranGen.nextFloat() * _view.getWidth();
        yPos = ranGen.nextFloat() * _view.getHeight();

        xDir = ranGen.nextFloat() * 100.0f - 50.f;
        yDir = ranGen.nextFloat() * 100.0f - 50.f;
    }

    @Override
    public void Update(float _dt) {
        lifeTime -= _dt;

        if(lifeTime < 0.f)
        {
            SetIsDone(true);
        }
        xPos += xDir * _dt;
        yPos += yDir * _dt;

        // If user clicks on object, remove object (it dies)
        // if (android.gettouch..etc)
        // Handle the touch and check collision with click/touch


        // ABSTRACTION - Can modify for another platform easily
        if (TouchManager.Instance.IsDown())
        {
            // Check Collision here
            float imgRadius = bmp.getHeight() * 0.5f;

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
        // Centers the image, makes sure it isn't cut off
        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null);
    }


    public static SampleEntity Create()
    {
        SampleEntity result = new SampleEntity();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    @Override
    public String GetType() {
        return "SampleEntity";
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
        return bmp.getHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other) {
        if (_other.GetType() == "SampleEntity")
        {
            SetIsDone(true);
        }
    }
}

