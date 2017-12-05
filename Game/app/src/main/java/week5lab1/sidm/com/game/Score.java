package week5lab1.sidm.com.game;

public class Score {
    public final static Score Instance = new Score();
    private int score = 0;
    private int Highscore = 0;

    private Score() { }

    public int getHighscore() {
        return Highscore;
    }

    public int getScore(){
        return score;
    }

    void setHighscore(int _Highscore){
        Highscore = _Highscore;
    }

    void setScore(int _score){
        score = _score;
    }
}

