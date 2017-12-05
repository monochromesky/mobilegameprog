package week5lab1.sidm.com.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Wafieqa on 12/4/2017.
 */

public class character implements EntityBase, Collidable
{
    //  private Bitmap bmp = null;
    private Bitmap [] charapic = new Bitmap[4];
    private Bitmap [] charapic2 = new Bitmap[4];
    private float timerDelay = 0.25f;
    private boolean isDone = false;
    private boolean thePlatform = false;
    private float xPos, yPos, xDir, yDir, lifeTime;

    private int currentAlpha;
    private Paint alphaPaint = new Paint();

    private short animIndex = 0;
    @Override
    public boolean IsDone() {
        //return health <= 0; E.g
        return isDone;
    }

    @Override
    public boolean isPlatform()
    {
        return false;
    }
    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {

            // bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ship2_1);
            charapic[0] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.walk1);
            charapic[1] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.walk2);
            charapic[2] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.walk3);
            charapic[3] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.walk4);


            charapic2[0] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.jump1);
            charapic2[1] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.jump2);
            charapic2[2] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.jump3);
            charapic2[3] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.jump4);


        lifeTime = 5.0f;

        Random ranGen = new Random();

        //xPos = 50;
        //yPos = 50;
        //  xPos = ranGen.nextFloat() * _view.getWidth();
        // yPos = ranGen.nextFloat() * _view.getHeight();

        // xDir = ranGen.nextFloat() * 100.0f - 50.f;
         //yDir = ranGen.nextFloat() * 100.0f - 50.f;

        yDir = 500.f;

        currentAlpha = 255;
    }

    @Override
    public void Update(float _dt) {
        // lifeTime -= _dt;

        //   if(lifeTime < 0.f)
        //  {
        //      SetIsDone(true);
        // }
        // xPos += xDir * _dt;
        // yPos += yDir * _dt;

        // If user clicks on object, remove object (it dies)
        // if (android.gettouch..etc)
        // Handle the touch and check collision with click/touch
        timerDelay-= _dt;

        if (timerDelay <= 0)
        {
            animIndex++;
            timerDelay = 0.25f;
        }

        animIndex %= 4;
        float imgRadius = charapic2[animIndex].getHeight() * 0.5f;

        //float imgRadius = charapic[animIndex].getHeight() * 0.5f;
        LinkedList<EntityBase> currentListOfObj = EntityManager.Instance.GetListOfArray();

        for (EntityBase it : currentListOfObj)
        {
            if (it.isPlatform() && !TouchManager.Instance.IsDown()) {
                if (it instanceof Collidable) {
                    if (Collision.SphereToSphere(((Collidable) it).GetPosX(), ((Collidable) it).GetPosY(), ((Collidable) it).GetRadius(), xPos, yPos, imgRadius)) {
                        yDir = 0;
                        break;
                    }
                    else
                    {
                        //TODO: get the collision for platform to prevent character from falling through.
                        yDir = 500.f;
                    }
                }
            }
        }
        yPos += yDir*_dt;
        // ABSTRACTION - Can modify for another platform easily
        if (TouchManager.Instance.IsDown())
        {
            // Check Collision here
            yDir = 1500.f;
            yPos -= yDir*_dt;

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



        _canvas.drawBitmap(charapic[animIndex], xPos - charapic[animIndex].getWidth() * 0.5f, yPos - charapic[animIndex].getHeight() * 0.5f, null);
        _canvas.drawBitmap(charapic2[animIndex], xPos - charapic2[animIndex].getWidth() * 0.5f, yPos - charapic2[animIndex].getHeight() * 0.5f, null);


    }


    public static character Create(int pox, int posy)
    {
        character result = new character();
        result.xPos = pox;
        result.yPos = posy;
        EntityManager.Instance.AddEntity(result);
        return result;
    }
    @Override
    public String GetType() {
        return "character";
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
        return charapic[animIndex].getHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other) {
        if (_other.GetType() == "character")
        {
            //  SetIsDone(true);
        }
    }
}
