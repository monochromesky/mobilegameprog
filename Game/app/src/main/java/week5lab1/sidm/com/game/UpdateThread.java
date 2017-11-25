package week5lab1.sidm.com.game;

/**
 * Created by User on 25/11/2017.
 */
import android.graphics.Canvas;
import android.graphics.Color;
import android.provider.Settings;
import android.view.SurfaceHolder;


public class UpdateThread extends Thread
{
    static final long targetFPS = 60;

    private Gameview view = null;
    private SurfaceHolder holder = null;
    private boolean isRunning = false;

    //Constructor
    public UpdateThread(Gameview _view)
    {
        view = _view;
        holder = _view.getHolder();

        //Init here game
        SGame.Instance.Init(_view);
    }

    public boolean isRunning()
    {
        return isRunning;
    }
    public void Initialize()
    {
        isRunning = true;
    }
    public void Terminate()
    {
        isRunning = false;
    }

    @Override
    public void run()
    {
        //FPS
        long FPS = 1000 / targetFPS; //1000 is milliseconds

        //Delta Time
        long startTime = 0;

        //Another variable to calculate delta time
        long prevTime = System.nanoTime();

        while (isRunning)
        {
            // Update Start!
            startTime = System.currentTimeMillis(); //For framerate controller

            // Using prevtime vs currtime to get delta time
            long currTime = System.nanoTime();
            float deltaTime = (float)((currTime - prevTime) / 1000000000.0f);
            prevTime = currTime;

            SGame.Instance.Update(deltaTime);

            // Update End...

            // Rendering Start!
            Canvas canvas = holder.lockCanvas(null);
            if (canvas != null)
            {
                // prevents 2 threads from rendering at same time
                synchronized (holder)
                {
                    //Render the whole screen black
                    canvas.drawColor(Color.BLACK);

                    //Render game
                    SGame.Instance.Render(canvas);
                }
                // This is the part to do the render
                holder.unlockCanvasAndPost(canvas);
            }
            // Rendering System end.

            //Post Update/Render
            try
            {
                long sleepTime = FPS - (System.currentTimeMillis() - startTime);

                if (sleepTime > 0)
                    sleep(sleepTime);
            }
            catch (InterruptedException e)
            {
                Terminate();;
            }
            //End of loop!
        }
    }
}
