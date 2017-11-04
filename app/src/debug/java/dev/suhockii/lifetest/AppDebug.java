package dev.suhockii.lifetest;

import com.squareup.leakcanary.LeakCanary;

public class AppDebug extends App {

    @Override
    public void onCreate() {
        super.onCreate();

        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }
}
