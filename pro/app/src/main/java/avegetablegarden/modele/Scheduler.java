package avegetablegarden.modele;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);

    public void scheduleBackground(Runnable r) {
	scheduledExecutor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
    };
    public void scheduleUi(Runnable r) {
	scheduledExecutor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS); 
    };


}
