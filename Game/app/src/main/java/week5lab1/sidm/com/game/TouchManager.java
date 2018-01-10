package week5lab1.sidm.com.game;


import android.view.MotionEvent;

public class TouchManager
{
    public final static TouchManager Instance = new TouchManager();

    private TouchManager()
    {
        posX = 0;
        posY = 0;
        posX2 = 0;
        posY2 = 0;
    }
    public enum TouchState
    {
        NONE,
        DOWN,
        MOVE,
        FLING_Left,
        FLING_Right
    }
    private TouchState status = TouchState.NONE;
    private int posX, posY;
    private int posX2, posY2;
    public boolean HasTouch()
    {
        return status == TouchState.DOWN || status == TouchState.MOVE;
    }

    //Check for drags (if finger is dragging along the screen)
    public boolean IsDrag()
    {
        return status == TouchState.MOVE;
    }
    //Check for "clicks" (if finger is tapping on screen)
    public boolean IsDown()
    {
        return status == TouchState.DOWN;
    }

    //Check for sweeping movements from UP to DOWN
    public boolean IsFlingLeft()
    {
        return status == TouchState.FLING_Left;
    }

    //Check for sweeping movements from DOWN to UP
    public boolean IsFlingRight()
    {
        return status == TouchState.FLING_Right;
    }

    public int GetPosX()
    {
        return posX;
    }
    public int GetPosY()
    {
        return posY;
    }
    public int GetPosX2()
    {
        return posX2;
    }

    public int  GetPosY2()
    {
        return posY2;
    }

    public void SetTouchStateNone()
    {
        status = TouchState.NONE;
    }

    public void Update(int _posX, int _posY, int _motionEventStatus)
    {
       // posX = _posX;
       // posY = _posY;

        switch(_motionEventStatus)
        {
            case MotionEvent.ACTION_DOWN:
                posX = _posX;
                posY = _posY;
                status = TouchState.DOWN;
                break;
            case MotionEvent.ACTION_MOVE:
                status = TouchState.MOVE;
                break;
            case MotionEvent.ACTION_UP:
                posX2 = _posX;
                posY2 = _posY;

                //If UP to DOWN sweep event on screen
                if (posX > posX2)
                {
                    status = TouchState.FLING_Left;
                }

                //If DOWN to UP sweep event on screen
                if (posX < posX2)
                {
                    status = TouchState.FLING_Right;
                }

                break;

        }
    }

}
