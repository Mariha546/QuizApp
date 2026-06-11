package services;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TimerService {
    private final Timer swingTimer;
    private int remainingSeconds;

    public TimerService(int secondsLimit, JLabel visualLabel, Runnable onTimeUpCallback) {
        this.remainingSeconds = secondsLimit;
        visualLabel.setText("Time Remaining: " + remainingSeconds + "s");

        // We initialize the Timer first, referencing the callback smoothly
        this.swingTimer = new Timer(1000, null);

        // Then we define the action listener safely after initialization
        this.swingTimer.addActionListener(event -> {
            remainingSeconds--;
            visualLabel.setText("Time Remaining: " + remainingSeconds + "s");
            if (remainingSeconds <= 0) {
                swingTimer.stop();
                onTimeUpCallback.run();
            }
        });
    }

    public void start() { swingTimer.start(); }
    public void stop() { swingTimer.stop(); }
}