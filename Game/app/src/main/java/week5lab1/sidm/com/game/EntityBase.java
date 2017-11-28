package week5lab1.sidm.com.game;

import android.graphics.Canvas;
import android.view.SurfaceView;

// Every Entity must implement this
public interface EntityBase
{
    boolean IsDone();
    void SetIsDone(boolean _isDone);

    void Init(SurfaceView _view);
    void Update(float _dt);
    void Render (Canvas _canvas);
}

