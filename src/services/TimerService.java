package services;

import java.util.Timer;
import java.util.TimerTask;

public class TimerService {

    private Timer timer;
    private int remainingTime;

    public interface TimerListener {
        void onTick(int secondsLeft);
        void onFinish();
    }

    public void startTimer(int seconds, TimerListener listener) {
        remainingTime = seconds;

        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTime > 0) {
                    listener.onTick(remainingTime);
                    remainingTime--;
                } else {
                    timer.cancel();
                    listener.onFinish();
                }
            }
        }, 0, 1000);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }
}