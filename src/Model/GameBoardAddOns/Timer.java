package Model.GameBoardAddOns;

public class Timer {
    private long startTime;
    public Timer(){
         startTime = System.currentTimeMillis();
    }
    public long getTime(){
        long elapsedTime = System.currentTimeMillis() - startTime;
      return elapsedTime;
    }
}
