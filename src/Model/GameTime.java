package Model;

public class GameTime extends Thread{
    private int horloge;
    GameTime(){
        this.horloge = 0;
    }
    @Override
    public void run(){
        while(true){
            this.horloge++;
            try {
                sleep(GameConstants.Tic);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
