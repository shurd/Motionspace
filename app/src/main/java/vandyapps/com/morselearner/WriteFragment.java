package vandyapps.com.morselearner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.scanner.ScanActivity;

import java.util.ArrayList;

/**
 * Created by Sam on 2/21/2015.
 */
public class WriteFragment extends Fragment {

    public ArrayList<String> morseArray, letterArray;
    public Button mReset, mConnect, mClear;
    public TextView mWhole, mCurrent;
    public String mWord, mMorse, origWord;
    public Hub hub;
    private int var;
    private DeviceListener mListener;

    public void initializeArrays(){
        morseArray=new ArrayList<>();
        letterArray = new ArrayList<>();
        morseArray.add("a");
        morseArray.add("b");
        morseArray.add("c");
        morseArray.add("d");
        morseArray.add("e");
        morseArray.add("f");
        morseArray.add("g");
        morseArray.add("h");
        morseArray.add("i");
        morseArray.add("j");
        morseArray.add("k");
        morseArray.add("l");
        morseArray.add("m");
        morseArray.add("n");
        morseArray.add("o");
        morseArray.add("p");
        morseArray.add("q");
        morseArray.add("r");
        morseArray.add("s");
        morseArray.add("t");
        morseArray.add("u");
        morseArray.add("v");
        morseArray.add("w");
        morseArray.add("x");
        morseArray.add("y");
        morseArray.add("z");
        letterArray.add("._");
        letterArray.add("_...");
        letterArray.add("_._.");
        letterArray.add("_..");
        letterArray.add(".");
        letterArray.add(".._.");
        letterArray.add("_ _.");
        letterArray.add("....");
        letterArray.add("..");
        letterArray.add("._ _ _");
        letterArray.add("_._");
        letterArray.add("._..");
        letterArray.add("_ _");
        letterArray.add("_.");
        letterArray.add("_ _ _");
        letterArray.add("._ _.");
        letterArray.add("_ _._");
        letterArray.add("._.");
        letterArray.add("...");
        letterArray.add("_");
        letterArray.add(".._");
        letterArray.add("..._");
        letterArray.add("._ _");
        letterArray.add("_.._");
        letterArray.add("_._ _ ");
        letterArray.add("_ _..");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWord = "";
        mMorse="";
        origWord="";
        var=0;
        setHasOptionsMenu(true);
        initializeArrays();

        hub = Hub.getInstance();
        if (!hub.init(getActivity())) {
            Log.e("hey", "Could not initialize the Hub.");
            getActivity().finish();
            return;
        }

        //Hub.getInstance().attachToAdjacentMyo();
        Hub.getInstance().setLockingPolicy(Hub.LockingPolicy.NONE);
    }
    private void onScanActionSelected() {
// Launch the ScanActivity to scan for Myos to connect to.
        Intent intent = new Intent(getActivity(), ScanActivity.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.activity_write, parent, false);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            if(NavUtils.getParentActivityName(getActivity())!=null) {
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        mWhole=(TextView)v.findViewById(R.id.text_in_box);
        mCurrent=(TextView)v.findViewById(R.id.current_letter);

        mReset=(Button)v.findViewById(R.id.reset_text);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWhole.setText("Start Using your Myo to Type!");
                mCurrent.setText("");
                origWord="";
                mMorse="";
            }
        });

        mClear= (Button)v.findViewById(R.id.clear_button);
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMorse="";
                mCurrent.setText(origWord);
            }
        });

        mConnect=(Button)v.findViewById(R.id.connect_button_2);
        mConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectMethod();
            }
        });
        mListener = new AbstractDeviceListener() {
            // onConnect() is called whenever a Myo has been connected.
            @Override
            public void onConnect(Myo myo, long timestamp) {
                Log.e("connected", "to myo");
            }
            // onDisconnect() is called whenever a Myo has been disconnected.
            @Override
            public void onDisconnect(Myo myo, long timestamp) {

            }
            // onPose() is called whenever a Myo provides a new pose.
            @Override
            public void onPose(Myo myo, long timestamp, Pose pose) {

                switch (pose) {
                    case UNKNOWN:

                        break;
                    case REST:
                    case DOUBLE_TAP:
                        //mWord+=mWord+". ";
                        //mWhole.setText(mWhole.getText()+mWord);
                        break;
                    case FIST:
                        shortMethod();
                        break;
                    case WAVE_IN:
                        pauseMethod();
                       /* origWord+=" ";
                        if(mWhole.getText().equals("Start Using your Myo to Type!")){
                            mWhole.setText("");
                        }
                        mWhole.setText(mWhole.getText()+origWord);
                        origWord="";
                        mMorse="";
                        mCurrent.setText("");*/
                        break;
                    case WAVE_OUT:
                        //pauseMethod();
                        origWord+=" ";
                        if(mWhole.getText().equals("Start Using your Myo to Type!")){
                            mWhole.setText("");
                        }
                        mWhole.setText(mWhole.getText()+origWord);
                        origWord="";
                        mMorse="";
                        mCurrent.setText("");
                        break;
                    case FINGERS_SPREAD:
                        longMethod();
                        break;
                }
            }
        };
        hub.addListener(mListener);
        return v;
    }
    public void connectMethod(){
        onScanActionSelected();
    }
    public void shortMethod(){
        mMorse+=".";

        var++;
        if(mCurrent.length()>6){
            //blueColor();
            mMorse="";
        }
        mCurrent.setText(origWord+mMorse);
    }
    public void longMethod(){
        if(mMorse.length()>0&&mMorse.charAt(mMorse.length()-1)==('_')){
            mMorse+=" ";
            //var++;
        }
        mMorse+="_";
        //var++;

        if(mCurrent.length()>6){
            //blueColor();
            mMorse="";
        }
        mCurrent.setText(origWord+mMorse);
    }
    public void pauseMethod(){
        //blueColor();
        String nextLet="";
        for(int i=0;i<26;i++){
            if(letterArray.get(i).equals(mMorse)){
                nextLet=morseArray.get(i);
                origWord+=nextLet;
            }
        }
//        mWord=mWord.substring(0, mWord.length()-var);
       // var=0;
       // mWord+=mWord+nextLet;
        mMorse="";
        mCurrent.setText(origWord);
    }
}
