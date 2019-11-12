package dev.suhockii.lifetest.util.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import moxy.MvpAppCompatFragment

import dev.suhockii.lifetest.di.Injectable

/**
 * Sends signal to activity that implements [OnFragmentInteractionListener.onFragmentInteraction]
 * that fragment has been connected.
 */
abstract class InteractionFragment : MvpAppCompatFragment(), Injectable {
    private var onFragmentInteractionListener: OnFragmentInteractionListener? = null

    protected abstract val toolbarTitle: String

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onFragmentInteractionListener!!.onFragmentInteraction(toolbarTitle)
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            onFragmentInteractionListener = activity as OnFragmentInteractionListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement OnFragmentInteractionListener")
        }

    }

    override fun onDetach() {
        super.onDetach()
        onFragmentInteractionListener = null
    }

    interface OnFragmentInteractionListener {

        /**
         * Call when fragment attaches to activity
         */
        fun onFragmentInteraction(title: String)
    }
}
