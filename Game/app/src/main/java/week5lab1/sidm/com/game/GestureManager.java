package week5lab1.sidm.com.game;
import android.view.GestureDetector;

public class GestureManager
{
    //Singleton
    public final static GestureManager Instance = new GestureManager();

    public enum GestureState
    {
        NONE,
        PRESS,
        FLING
    }

    GestureDetector.SimpleOnGestureListener Test;

    private GestureState status = GestureState.NONE;
    private int posX, posY; //int because pixels

    private GestureManager()
    {
        posX = 0;
        posY = 0;
    }

    //Check if finger is touching the screen; click or hold
    public boolean HasTouch()
    {
        return status == GestureState.PRESS || status == GestureState.FLING;
    }

    //Check for flings (if finger is swiping the screen)
    public boolean IsFling()
    {
        return status == GestureState.FLING;
    }

    public int GetPosX()
    {
        return posX;
    }

    public int  GetPosY()
    {
        return posY;
    }

    public void Update(int _posX, int _posY, int gestureDetectorStatus)
    {
        posX = _posX;
        posY = _posY;
    }
}
