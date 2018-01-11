package week5lab1.sidm.com.game;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class EntityManager
{
    public final static EntityManager Instance = new EntityManager();
    private LinkedList<EntityBase> entityList = new LinkedList<EntityBase>();
    private SurfaceView view = null;

    // Depends, no strict pattern
    private EntityManager()
    {

    }

    public LinkedList<EntityBase> GetListOfArray()
    {
        return entityList;
    }
    //This function is only called once, at the start of our game update thread
    //Cannot be used to init our entities
    public void Init(SurfaceView _view)
    {
        view = _view;
    }
    public void Update(float _dt)
    {
        LinkedList<EntityBase> removalList = new LinkedList<EntityBase>();

        // Update every single entity in the list
        for (EntityBase currEntity : entityList)
        {
            currEntity.Update(_dt);

            //Check if it is done
            if (currEntity.IsDone())
            {
                //Done, add to the removal list
                removalList.add(currEntity);
            }
        }

        //Collision Checking
        for (int i = 0; i < entityList.size(); ++i)
        {
            EntityBase currEntity = entityList.get(i);

            if (currEntity instanceof  Collidable)
            {
                Collidable first = (Collidable) currEntity;

                for (int j = i + 1; j < entityList.size(); ++j)
                {
                    EntityBase otherEntity = entityList.get(j);

                    if (otherEntity instanceof  Collidable)
                    {
                        Collidable second = (Collidable) otherEntity;
                        //We got 2 collidables, check collision here

                        if (Collision.SphereToSphere(first.GetPosX(), first.GetPosY(), first.GetRadius()
                                , second.GetPosX(), second.GetPosY(), second.GetRadius()))
                        {
                            // Collision yay
                            first.OnHit(second);
                            second.OnHit(first);
                        }
                    }
                }
            }

            //Check if it is done
            if (currEntity.IsDone())
            {
                //Done, add to the removal list
                removalList.add(currEntity);
            }
        }
        for (EntityBase currEntity : removalList)
        {
            entityList.remove(currEntity);
        }
        removalList.clear();
    }

    public void Render(Canvas _canvas)
    {
        //Use 'rendering layer' to sort the render order
        Collections.sort(entityList, new Comparator<EntityBase>()
        {
            @Override

            //Compares between two objects
            public int compare(EntityBase o1, EntityBase o2) {
                return o1.GetRenderLayer() - o2.GetRenderLayer();
            }
        });

        for (EntityBase currEntity : entityList)
        {
            currEntity.Render(_canvas);
        }
    }
    public void AddEntity(EntityBase _newEntity)
    {
        _newEntity.Init(view); //Stored from initialization
        entityList.add(_newEntity);
    }

    public void updatepausebutton(float dt)
    {
        for (EntityBase currEntity : entityList)
        {
            //check if instanceof
            if (currEntity instanceof SamplePauseButton)
            {
                currEntity.Update(dt);
            }
        }
    }
}


