package week5lab1.sidm.com.game;

public class HealthSystem{
    public final static HealthSystem Instance = new HealthSystem();
    private int playerhealth = 0;

    private HealthSystem() { }

    public int getHealth(){
        return playerhealth;
    }

    void setHealth(int _hp){
        playerhealth = _hp;
    }
}
