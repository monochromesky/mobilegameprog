package week5lab1.sidm.com.game;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by User on 25/11/2017.
 */

public class Gameview extends SurfaceView
{
    private SurfaceHolder holder = null;
    private UpdateThread updateThread = new UpdateThread(this);

    //Base Constructor
    public Gameview(Context _context)
    {
        //Do what the base surface view will do to setup
        super(_context);
        holder = getHolder();

        if (holder != null) {
            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    //Init

                    //To set boolean to loop
                    if (!updateThread.isRunning())
                        updateThread.Initialize();

                    //Kick to start thread if not alive
                    if (!updateThread.isAlive())
                        updateThread.start();
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    //Nothing
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    //clean up
                    updateThread.Terminate();
                }
            });
            {

            }
        }
    }

}
