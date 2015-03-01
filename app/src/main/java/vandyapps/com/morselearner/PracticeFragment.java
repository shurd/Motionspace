package vandyapps.com.morselearner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import java.util.Random;

/**
 * Created by Sam on 2/22/2015.
 */
public class PracticeFragment extends Fragment {
    public ArrayList<String> morseArray, letterArray;
    //public Button mReset, mConnect;
    public Button mConnect;
    public TextView mQuestion, mAnswer, mYourAnswer;
    public String mGuess;
    public Hub hub;
    private int var;
    private DeviceListener mListener;

    public void initializeArrays(){
        morseArray=new ArrayList<>();
        letterArray = new ArrayList<>();
        morseArray.add("A");
        morseArray.add("B");
        morseArray.add("C");
        morseArray.add("D");
        morseArray.add("E");
        morseArray.add("F");
        morseArray.add("G");
        morseArray.add("H");
        morseArray.add("I");
        morseArray.add("J");
        morseArray.add("K");
        morseArray.add("L");
        morseArray.add("M");
        morseArray.add("N");
        morseArray.add("O");
        morseArray.add("P");
        morseArray.add("Q");
        morseArray.add("R");
        morseArray.add("S");
        morseArray.add("T");
        morseArray.add("U");
        morseArray.add("V");
        morseArray.add("W");
        morseArray.add("X");
        morseArray.add("Y");
        morseArray.add("Z");
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
        //mWord = "";
        //mMorse="";
        //origWord="";
        //var=0;
        setHasOptionsMenu(true);
        initializeArrays();

        mGuess="";
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

        View v = inflater.inflate(R.layout.practice_activity, parent, false);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            if(NavUtils.getParentActivityName(getActivity())!=null) {
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        mQuestion=(TextView)v.findViewById(R.id.guess);
        mAnswer=(TextView)v.findViewById(R.id.answer);
        Random randomGenerator = new Random();
        var=randomGenerator.nextInt(26);
        mQuestion.setText(morseArray.get(var));
        mYourAnswer=(TextView)v.findViewById(R.id.your_answer);
        mQuestion.setTextColor(Color.BLACK);
        mAnswer.setTextColor(Color.BLACK);
        mYourAnswer.setTextColor(Color.BLACK);

        mConnect = (Button)v.findViewById(R.id.connect3);
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
                        //mGuess="";
                        //mYourAnswer.setText(mGuess);
                        //mWord+=mWord+". ";
                        //mWhole.setText(mWhole.getText()+mWord);
                        break;
                    case FIST:
                        shortMethod();
                        break;
                    case WAVE_IN:
                        pauseMethod();
                        //nextLetter();
                        break;
                    case WAVE_OUT:
                        //pauseMethod();
                        nextLetter();
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
    public void nextLetter(){
        Random randomGenerator = new Random();
        var=randomGenerator.nextInt(26);
        mQuestion.setText(morseArray.get(var));
        mQuestion.setTextColor(Color.BLACK);
        mAnswer.setText("Signal Your Answer!");
        mAnswer.setTextColor(Color.BLACK);
        mYourAnswer.setText("");
        mYourAnswer.setTextColor(Color.BLACK);
    }
    public void connectMethod(){
        onScanActionSelected();
    }
    public void shortMethod(){
        mGuess+=".";
        //var++;
        if(mGuess.length()>6){
            //blueColor();
            mGuess="";
        }
        mYourAnswer.setText(mGuess);
    }
    public void longMethod(){
        if(mGuess.length()>0&&mGuess.charAt(mGuess.length()-1)==('_')){
            mGuess+=" ";
            //var++;
        }
        mGuess+="_";
        //var++;

        if(mGuess.length()>6){
            //blueColor();
            mGuess="";
        }
        mYourAnswer.setText(mGuess);
    }
    public void pauseMethod(){
        //blueColor();
        String nextLet="";
        if(mGuess.equals(letterArray.get(var))){
            mQuestion.setTextColor(Color.GREEN);
            mYourAnswer.setTextColor(Color.GREEN);
            mAnswer.setText("Correct!");
            mAnswer.setTextColor(Color.GREEN);
        } else {
            mAnswer.setText("Correct Answer: " +letterArray.get(var));
            mQuestion.setTextColor(Color.RED);
            mYourAnswer.setTextColor(Color.RED);
            mAnswer.setTextColor(Color.RED);
        }
        mGuess="";
    }
}
