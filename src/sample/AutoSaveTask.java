package sample;

import java.util.Timer;
import java.util.TimerTask;

public class AutoSaveTask extends TimerTask {

    Timer timer = new Timer(true);


    @Override
    public void run() {
        System.out.println("TimerTask начал свое выполнение");


    }

    public void timerStart(){
        TimerTask timerTask = new AutoSaveTask();
        // стартуем TimerTask в виде демона


        // будем запускать каждых 10 секунд (50 * 1000 миллисекунд)
        timer.scheduleAtFixedRate(timerTask, 0, 10*1000);
        System.out.println("TimerTask начал выполнение");

    }

    public void timerStop(){
        timer.cancel();
    }

}
