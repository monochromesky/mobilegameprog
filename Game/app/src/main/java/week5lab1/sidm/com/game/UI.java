package week5lab1.sidm.com.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

/**
 * Created by User on 4/12/2017.
 */

public class UI implements EntityBase {

    // TODO : ADD UI
//extends Activity implements View.OnClickListener

    private Bitmap bottomui, fullhp, twohp, onehp, nohp = null;
    private Bitmap hearticon = null;

    private boolean isDone = false;
    private boolean isInit = false;
    private int renderLayer = 0;

    private float xPos, yPos, offset;
    private SurfaceView view = null;

    @Override
    public boolean isPlatform()
    {
        return false;
    }
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
        bottomui = BitmapFactory.decodeResource(_view.getResources(), R.drawable.bottomui);
        fullhp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.fullhp);
        twohp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.twohp);
        onehp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.onehp);
        nohp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.zerohp);

        offset = 0.0f;
        view = _view;
    }

    @Override
    public void Update(float _dt)
    {
        offset += _dt * 0.1f;
    }

    @Override
    public void Render(Canvas _canvas)
    {
        xPos = 0.5f * view.getWidth();
        yPos = 0.5f * view.getHeight();

        // Sky BG
        // _canvas.drawBitmap(skybg, xPos - view.getWidth() * 0.5f, yPos - view.getHeight() * 0.5f, null);
       // _canvas.drawBitmap(bottomui, xPos - view.getWidth() * 0.5f, yPos - view.getHeight() * 0.5f, null);

        //Hardcoding the goddamned HP until i find a better way

        if (HealthSystem.Instance.getHealth() == 3)
        {
            _canvas.drawBitmap(fullhp, xPos - view.getWidth() * 0.5f, yPos - view.getHeight() * 0.5f, null);
        }
        else if (HealthSystem.Instance.getHealth() == 2)
        {
            _canvas.drawBitmap(twohp, xPos - view.getWidth() * 0.5f, yPos - view.getHeight() * 0.5f, null);
        }
        else if (HealthSystem.Instance.getHealth() == 1)
        {
            _canvas.drawBitmap(onehp, xPos - view.getWidth() * 0.5f, yPos - view.getHeight() * 0.5f, null);
        }
        else if (HealthSystem.Instance.getHealth() == 0)
        {
            _canvas.drawBitmap(nohp, xPos - view.getWidth() * 0.5f, yPos - view.getHeight() * 0.5f, null);
        }

    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return renderLayer;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        renderLayer = _newLayer;
    }

    public static UI Create()
    {
        UI result = new UI();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    public static UI Create(int _layer)
    {
        UI result = Create();
        result.SetRenderLayer(_layer);
        return result;
    }

}

