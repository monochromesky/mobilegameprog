package week5lab1.sidm.com.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

// TODO: HAZARDS THAT FALL FROM THE SKYYY

public class Hazards implements EntityBase, Collidable
{
    private Bitmap bmp = null;
    private Bitmap metalhazard, glasshazard, plastichazard = null;

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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.walk1);
        metalhazard = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metal);
      //  glasshazard = BitmapFactory.decodeResource(_view.getResources(), R.drawable.glasshazard);
       // plastichazard = BitmapFactory.decodeResource(_view.getResources(), R.drawable.plastichazard);

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
        _canvas.drawBitmap(metalhazard, xPos - bmp.getWidth() * 0.5f, yPos - metalhazard.getHeight() * 0.5f, null);
       // _canvas.drawBitmap(glasshazard, xPos - bmp.getWidth() * 0.5f, yPos - glasshazard.getHeight() * 0.5f, null);
       // _canvas.drawBitmap(plastichazard, xPos - bmp.getWidth() * 0.5f, yPos - plastichazard.getHeight() * 0.5f, null);

    }


    public static Hazards Create()
    {
        Hazards result = new Hazards();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    @Override
    public String GetType() {
        return "Hazards";
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
        return metalhazard.getHeight() * 0.5f;
       // return glasshazard.getHeight() * 0.5f;
        //return plastichazard.getHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other) {
        if (_other.GetType() == "Hazards")
        {
            SetIsDone(true);
        }
    }
}

