package Model;

public class GameTime extends Thread{
    private int clock;
    GameTime(){
        this.clock = 0;
    }
    @Override
    public void run(){
        while(true){
            this.clock++;
            try {
                sleep(GameConstants.TICK);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
