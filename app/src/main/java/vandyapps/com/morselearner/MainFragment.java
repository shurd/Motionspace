package vandyapps.com.morselearner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by Sam on 2/21/2015.
 */
public class MainFragment extends Fragment {
    public Button mLearnButton, mWhyButton, mTryButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.activity_main, parent, false);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            if(NavUtils.getParentActivityName(getActivity())!=null) {
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        mLearnButton = (Button)v.findViewById(R.id.learn_button);
        mLearnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LearnActivity.class);
                startActivity(i);
            }
        });

        mTryButton = (Button)v.findViewById(R.id.write_button);
        mTryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WriteActivity.class);
                startActivity(i);
            }
        });

        mWhyButton = (Button)v.findViewById(R.id.why_button);
        mWhyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WhyActivity.class);
                startActivity(i);
            }
        });

        return v;
    }
}
