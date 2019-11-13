package dev.suhockii.lifetest.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ProgressBar

import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

object AppUtils {
    fun getGlideListener(progressBar: ProgressBar?): RequestListener<Drawable> {
        return object : RequestListener<Drawable> {

            override fun onLoadFailed(
                e: GlideException?, model: Any,
                target: Target<Drawable>, isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable, model: Any, target: Target<Drawable>,
                dataSource: DataSource, isFirstResource: Boolean
            ): Boolean {
                if (progressBar != null) {
                    progressBar.visibility = View.INVISIBLE
                }
                return false
            }
        }
    }
}
