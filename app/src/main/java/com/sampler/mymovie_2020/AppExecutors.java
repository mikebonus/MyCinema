package com.sampler.mymovie_2020;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

// ★★★★★ step #19)
public class AppExecutors {

    private static AppExecutors instance;

    public static AppExecutors getInstance(){
        if(instance == null) {
            instance = new AppExecutors();
        }
        return instance;
    }

    // schedule a command after a given delay...
    // to do runnable tasks on the main / background thread...
    private final ScheduledExecutorService mNetworkIO
            = Executors.newScheduledThreadPool(3);  // 3 threads to do all the work!

    // access the 'networkIO' (executor!)
    public ScheduledExecutorService networkIO(){
        return mNetworkIO;
    }

    // This thing is going to be used in 'MovieApiClient.java' (for step #20)

}
