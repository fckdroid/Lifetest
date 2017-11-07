package dev.suhockii.lifetest.util.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import dev.suhockii.lifetest.di.Injectable;

/**
 * Sends signal to activity that implements {@link OnFragmentInteractionListener#onFragmentInteraction}
 * that fragment has been connected.
 */
public abstract class InteractionFragment extends MvpAppCompatFragment implements Injectable {
    protected OnFragmentInteractionListener onFragmentInteractionListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        onFragmentInteractionListener.onFragmentInteraction(getToolbarTitle());
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @NonNull
    protected abstract String getToolbarTitle();

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onFragmentInteractionListener = (OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentInteractionListener = null;
    }

    public interface OnFragmentInteractionListener {

        /**
         * Call when fragment attaches to activity
         */
        void onFragmentInteraction(String title);
    }
}
