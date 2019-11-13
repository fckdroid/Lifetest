package dev.suhockii.lifetest.di

import android.app.Activity
import android.app.Application
import android.os.Bundle

abstract class ActivityCreationListener : Application.ActivityLifecycleCallbacks {
    override fun onActivityStarted(activity: Activity) { /*ignore*/
    }

    override fun onActivityResumed(activity: Activity) { /*ignore*/
    }

    override fun onActivityPaused(activity: Activity) { /*ignore*/
    }

    override fun onActivityStopped(activity: Activity) { /*ignore*/
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) { /*ignore*/
    }

    override fun onActivityDestroyed(activity: Activity) { /*ignore*/
    }
}
