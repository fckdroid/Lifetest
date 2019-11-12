package dev.suhockii.lifetest.util.ui.activity

import android.app.Activity
import android.os.Build
import androidx.transition.Transition
import androidx.transition.TransitionManager
import androidx.collection.ArrayMap
import android.view.View
import android.view.ViewGroup

import moxy.MvpAppCompatActivity

import java.lang.ref.WeakReference
import java.lang.reflect.Field
import java.util.ArrayList

/**
 * Activity that helps to avoid memory leak from transition animations.
 * Calls removeActivityFromTransitionManager in onDestroy.
 */
open class NonLeakActivity : MvpAppCompatActivity() {
    override fun onDestroy() {
        super.onDestroy()
        removeActivityFromTransitionManager(this)
    }

    private fun removeActivityFromTransitionManager(activity: Activity) {
        if (Build.VERSION.SDK_INT < 21) {
            return
        }
        val transitionManagerClass = TransitionManager::class.java
        try {
            val runningTransitionsField = transitionManagerClass.getDeclaredField("sRunningTransitions")
            runningTransitionsField.isAccessible = true

            val runningTransitions =
                runningTransitionsField.get(transitionManagerClass) as ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>>
            if (runningTransitions.get() == null || runningTransitions.get()!!.get() == null) {
                return
            }
            val map = runningTransitions.get()!!.get()!!
            val decorView = activity.window.decorView
            if (map.containsKey(decorView)) {
                map.remove(decorView)
            }
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }
}
