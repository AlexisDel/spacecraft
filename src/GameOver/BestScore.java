package GameOver;

/**
 * Cette classe permet de contenir la couple utilisateur, temps écoulé
 */
public class BestScore {
    /** Attributs*/
    private String username;
    private int score;

    /**
     * Constructeur
     * @param username
     * @param score
     */
    public BestScore(String username, int score){
        this.score=score;
        this.username=username;
    }

    /**
     * Getter de l'attribut Username
     * @return string l'username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter de l'attribut score
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * getter du couple encodé dans le format du fichier score.txt
     * @return
     */
    public String getEncoded(){
        return username+"_"+score;
    }

    @Override
    public String toString() {
        return username+"_"+score;
    }
}

