package week5lab1.sidm.com.game;

public class Collision
{
    public static boolean SphereToSphere(float x1,  float y1, float radius1,
                                         float x2, float y2, float radius2)
    {
        //Get distance squared
        float xVec = x2 - x1;
        float yVec = y2 - y1;
        float distSquared = xVec * xVec + yVec * yVec;

         //Get radius squared
        float rSquared = radius1 + radius2;
        rSquared *= rSquared;

        //If dist greater than radius = no collide
        if (distSquared > rSquared)
            return false;

            return true;
    }

}
