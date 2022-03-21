package View.LeaderBoard;

public class BestScore {
    private String username;
    private int score;
    public BestScore(String username, int score){
        this.score=score;
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public String getEncoded(){
        return username+"_"+score;
    }
}
