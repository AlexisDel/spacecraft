package Game.Model.Scoring;

public class Timer {
    /** Attribut*/
    private long startTime;
    /** Constructeur*/
    public Timer(){
        //En millisecondes
         startTime = System.currentTimeMillis();
    }

    /**
     * Cette méthode est utilisée pour calculer le temps écoulé
     * depuis le déclanchement du timer
     * @return
     */
    public long getTime(){
        //En millisecondes
        long elapsedTime = System.currentTimeMillis() - startTime;
      return elapsedTime;
    }
}
