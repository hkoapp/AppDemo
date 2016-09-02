package hk.my.example.appdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainFragment extends Fragment {
    private static final String ARG_INPUT_FROM_ACTIVITY = "MainFragment.ARG_INPUT_FROM_ACTIVITY";
    private String inputFromActivity;

    private View view;
    private Callbacks callbacks;

    public MainFragment() {
        // Required empty public constructor
    }

    // newInstance is the way to pass arguments to the fragment
    // so that they(->arguments) are available after a fragment is recreated
    // by Android is to pass a bundle to the setArguments method.
    public static MainFragment newInstance(String inputFromActivity) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();

        args.putString(ARG_INPUT_FROM_ACTIVITY, inputFromActivity);

        // pass the arguments to fragment
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // restore the arguments into the fragment
            inputFromActivity = getArguments().getString(ARG_INPUT_FROM_ACTIVITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        switch (inputFromActivity) {
            case "home":
                view = inflater.inflate(R.layout.home_main, container, false);
                break;
            case "weather":
                view = inflater.inflate(R.layout.forecast_main, container, false);
                break;
            case "warning":
                view = inflater.inflate(R.layout.warning_main, container, false);
                break;
            case "information":
                view = inflater.inflate(R.layout.other_information_main, container, false);
                break;
            case "service":
                view = inflater.inflate(R.layout.service_main, container, false);
                break;
            default:
                view = inflater.inflate(R.layout.home_main, container, false);
                break;
        }
        Log.println(Log.ERROR, "Input From: ", inputFromActivity);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callbacks = (Callbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement Callbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    public interface Callbacks {
        public void passDataToActivity(String data);
    }

}
