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
import com.thalmic.myo.Arm;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.XDirection;
import com.thalmic.myo.scanner.ScanActivity;

/**
 * Created by Sam on 2/21/2015.
 */
public class LearnFragment extends Fragment {

    public TextView a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,vv,w,x,y,z;
    public TextView al,bl,cl,dl,el,il,fl,gl,hl,jl,kl,ll,ml,nl,ol,pl,ql,rl,sl,tl,ul,vl,wl,xl,yl,zl;
    private String currentTap;
    public Button mShort, mLong, mPause, mConnect, mPractice;
    public TextView mCurrent;
    private DeviceListener mListener;
    private Hub hub;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        currentTap = "";

        ///here
        hub = Hub.getInstance();
        if (!hub.init(getActivity())) {
            Log.e("hey", "Could not initialize the Hub.");
            getActivity().finish();
            return;
        }

        //Hub.getInstance().attachToAdjacentMyo();
        Hub.getInstance().setLockingPolicy(Hub.LockingPolicy.NONE);
        //here

        //hub.addListener(mListener);

        //onScanActionSelected();
    }

    private void onScanActionSelected() {
// Launch the ScanActivity to scan for Myos to connect to.
        Intent intent = new Intent(getActivity(), ScanActivity.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.learn_code, parent, false);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            if(NavUtils.getParentActivityName(getActivity())!=null) {
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        a=(TextView)v.findViewById(R.id.a_morse);
        b=(TextView)v.findViewById(R.id.b_morse);
        c=(TextView)v.findViewById(R.id.c_morse);
        d=(TextView)v.findViewById(R.id.d_morse);
        e=(TextView)v.findViewById(R.id.e_morse);
        f=(TextView)v.findViewById(R.id.f_morse);
        g=(TextView)v.findViewById(R.id.g_morse);
        h=(TextView)v.findViewById(R.id.h_morse);
        i=(TextView)v.findViewById(R.id.i_morse);
        j=(TextView)v.findViewById(R.id.j_morse);
        k=(TextView)v.findViewById(R.id.k_morse);
        l=(TextView)v.findViewById(R.id.l_morse);
        m=(TextView)v.findViewById(R.id.m_morse);
        n=(TextView)v.findViewById(R.id.n_morse);
        o=(TextView)v.findViewById(R.id.o_morse);
        p=(TextView)v.findViewById(R.id.p_morse);
        q=(TextView)v.findViewById(R.id.q_morse);
        r=(TextView)v.findViewById(R.id.r_morse);
        s=(TextView)v.findViewById(R.id.s_morse);
        t=(TextView)v.findViewById(R.id.t_morse);
        u=(TextView)v.findViewById(R.id.u_morse);
        vv=(TextView)v.findViewById(R.id.v_morse);
        w=(TextView)v.findViewById(R.id.w_morse);
        x=(TextView)v.findViewById(R.id.x_morse);
        y=(TextView)v.findViewById(R.id.y_morse);
        z=(TextView)v.findViewById(R.id.z_morse);

        al=(TextView)v.findViewById(R.id.a_letter);
        bl=(TextView)v.findViewById(R.id.b_letter);
        cl=(TextView)v.findViewById(R.id.c_letter);
        dl=(TextView)v.findViewById(R.id.d_letter);
        el=(TextView)v.findViewById(R.id.e_letter);
        fl=(TextView)v.findViewById(R.id.f_letter);
        gl=(TextView)v.findViewById(R.id.g_letter);
        hl=(TextView)v.findViewById(R.id.h_letter);
        il=(TextView)v.findViewById(R.id.i_letter);
        jl=(TextView)v.findViewById(R.id.j_letter);
        kl=(TextView)v.findViewById(R.id.k_letter);
        ll=(TextView)v.findViewById(R.id.l_letter);
        ml=(TextView)v.findViewById(R.id.m_letter);
        nl=(TextView)v.findViewById(R.id.n_letter);
        ol=(TextView)v.findViewById(R.id.o_letter);
        pl=(TextView)v.findViewById(R.id.p_letter);
        ql=(TextView)v.findViewById(R.id.q_letter);
        rl=(TextView)v.findViewById(R.id.r_letter);
        sl=(TextView)v.findViewById(R.id.s_letter);
        tl=(TextView)v.findViewById(R.id.t_letter);
        ul=(TextView)v.findViewById(R.id.u_letter);
        vl=(TextView)v.findViewById(R.id.v_letter);
        wl=(TextView)v.findViewById(R.id.w_letter);
        xl=(TextView)v.findViewById(R.id.x_letter);
        yl=(TextView)v.findViewById(R.id.y_letter);
        zl=(TextView)v.findViewById(R.id.z_letter);




        mCurrent = (TextView)v.findViewById(R.id.current_string);

        mShort = (Button)v.findViewById(R.id.short_button);
        mShort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shortMethod();
            }
        });
        mLong = (Button)v.findViewById(R.id.long_button);
        mLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                longMethod();
            }
        });
        mPause = (Button)v.findViewById(R.id.pause_button);
        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMethod();
            }
        });
        mConnect = (Button)v.findViewById(R.id.connect_button);
        mConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectMethod();
            }
        });
        mPractice = (Button)v.findViewById(R.id.practice_button);
        mPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PracticeActivity.class);
                startActivity(i);
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

                        break;
                    case FIST:
                        shortMethod();
                        break;
                    case WAVE_IN:
                        pauseMethod();
                        break;
                    case WAVE_OUT:
                        //pauseMethod();
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
    public void shortMethod(){
        changeColor();
        currentTap+=".";
        mCurrent.setText(currentTap);
        if(mCurrent.length()>6){
            blueColor();
            currentTap="";
        }
    }
    public void longMethod(){
        changeColor();
        if(currentTap.length()>0&&currentTap.charAt(currentTap.length()-1)==('_')){
            currentTap+=" ";
        }
        currentTap+="_";
        mCurrent.setText(currentTap);
        if(mCurrent.length()>6){
            blueColor();
            currentTap="";
        }
    }
    public void pauseMethod(){
        blueColor();
        currentTap="";
    }
    public void connectMethod(){
        onScanActionSelected();
    }

    public void changeColor(){
        a.setTextColor(Color.BLACK);
        al.setTextColor(Color.BLACK);
        b.setTextColor(Color.BLACK);
        bl.setTextColor(Color.BLACK);
        c.setTextColor(Color.BLACK);
        cl.setTextColor(Color.BLACK);
        d.setTextColor(Color.BLACK);
        dl.setTextColor(Color.BLACK);
        e.setTextColor(Color.BLACK);
        el.setTextColor(Color.BLACK);
        f.setTextColor(Color.BLACK);
        fl.setTextColor(Color.BLACK);
        g.setTextColor(Color.BLACK);
        gl.setTextColor(Color.BLACK);
        h.setTextColor(Color.BLACK);
        hl.setTextColor(Color.BLACK);
        i.setTextColor(Color.BLACK);
        il.setTextColor(Color.BLACK);
        j.setTextColor(Color.BLACK);
        jl.setTextColor(Color.BLACK);
        k.setTextColor(Color.BLACK);
        kl.setTextColor(Color.BLACK);
        l.setTextColor(Color.BLACK);
        ll.setTextColor(Color.BLACK);
        m.setTextColor(Color.BLACK);
        ml.setTextColor(Color.BLACK);
        n.setTextColor(Color.BLACK);
        nl.setTextColor(Color.BLACK);
        o.setTextColor(Color.BLACK);
        ol.setTextColor(Color.BLACK);
        p.setTextColor(Color.BLACK);
        pl.setTextColor(Color.BLACK);
        q.setTextColor(Color.BLACK);
        ql.setTextColor(Color.BLACK);
        r.setTextColor(Color.BLACK);
        rl.setTextColor(Color.BLACK);
        s.setTextColor(Color.BLACK);
        sl.setTextColor(Color.BLACK);
        t.setTextColor(Color.BLACK);
        tl.setTextColor(Color.BLACK);
        u.setTextColor(Color.BLACK);
        ul.setTextColor(Color.BLACK);
        vv.setTextColor(Color.BLACK);
        vl.setTextColor(Color.BLACK);
        w.setTextColor(Color.BLACK);
        wl.setTextColor(Color.BLACK);
        x.setTextColor(Color.BLACK);
        xl.setTextColor(Color.BLACK);
        y.setTextColor(Color.BLACK);
        yl.setTextColor(Color.BLACK);
        z.setTextColor(Color.BLACK);
        zl.setTextColor(Color.BLACK);
    }

    public void blueColor(){
        if(currentTap.equals(a.getText())){
            a.setTextColor(Color.WHITE);
            al.setTextColor(Color.WHITE);
            mCurrent.setText(a.getText() + " is the morse code for A!");
        } else if(currentTap.equals(b.getText())){
            b.setTextColor(Color.WHITE);
            bl.setTextColor(Color.WHITE);
            mCurrent.setText(b.getText()+" is the morse code for B!");
        }else if(currentTap.equals(c.getText())){
            c.setTextColor(Color.WHITE);
            cl.setTextColor(Color.WHITE);
            mCurrent.setText(c.getText()+" is the morse code for C!");
        }else if(currentTap.equals(d.getText())){
            d.setTextColor(Color.WHITE);
            dl.setTextColor(Color.WHITE);
            mCurrent.setText(d.getText()+" is the morse code for D!");
        }else if(currentTap.equals(e.getText())){
            e.setTextColor(Color.WHITE);
            el.setTextColor(Color.WHITE);
            mCurrent.setText(e.getText()+" is the morse code for E!");
        }else if(currentTap.equals(f.getText())){
            f.setTextColor(Color.WHITE);
            fl.setTextColor(Color.WHITE);
            mCurrent.setText(f.getText()+" is the morse code for F!");
        }else if(currentTap.equals(g.getText())){
            g.setTextColor(Color.WHITE);
            gl.setTextColor(Color.WHITE);
            mCurrent.setText(g.getText()+" is the morse code for G!");
        }else if(currentTap.equals(h.getText())){
            h.setTextColor(Color.WHITE);
            hl.setTextColor(Color.WHITE);
            mCurrent.setText(h.getText()+" is the morse code for H!");
        }else if(currentTap.equals(i.getText())){
            i.setTextColor(Color.WHITE);
            il.setTextColor(Color.WHITE);
            mCurrent.setText(i.getText()+" is the morse code for I!");
        }else if(currentTap.equals(j.getText())){
            j.setTextColor(Color.WHITE);
            jl.setTextColor(Color.WHITE);
            mCurrent.setText(j.getText()+" is the morse code for J!");
        }else if(currentTap.equals(k.getText())){
            k.setTextColor(Color.WHITE);
            kl.setTextColor(Color.WHITE);
            mCurrent.setText(k.getText()+" is the morse code for K!");
        }else if(currentTap.equals(l.getText())){
            l.setTextColor(Color.WHITE);
            ll.setTextColor(Color.WHITE);
            mCurrent.setText(l.getText()+" is the morse code for L!");
        }else if(currentTap.equals(m.getText())){
            m.setTextColor(Color.WHITE);
            ml.setTextColor(Color.WHITE);
            mCurrent.setText(m.getText()+" is the morse code for M!");
        }else if(currentTap.equals(n.getText())){
            n.setTextColor(Color.WHITE);
            nl.setTextColor(Color.WHITE);
            mCurrent.setText(n.getText()+" is the morse code for N!");
        }else if(currentTap.equals(o.getText())){
            o.setTextColor(Color.WHITE);
            ol.setTextColor(Color.WHITE);
            mCurrent.setText(o.getText()+" is the morse code for O!");
        }else if(currentTap.equals(p.getText())){
            p.setTextColor(Color.WHITE);
            pl.setTextColor(Color.WHITE);
            mCurrent.setText(p.getText()+" is the morse code for P!");
        }else if(currentTap.equals(q.getText())){
            q.setTextColor(Color.WHITE);
            ql.setTextColor(Color.WHITE);
            mCurrent.setText(q.getText()+" is the morse code for Q!");
        }else if(currentTap.equals(r.getText())){
            r.setTextColor(Color.WHITE);
            rl.setTextColor(Color.WHITE);
            mCurrent.setText(r.getText()+" is the morse code for R!");
        }else if(currentTap.equals(s.getText())){
            s.setTextColor(Color.WHITE);
            sl.setTextColor(Color.WHITE);
            mCurrent.setText(s.getText()+" is the morse code for S!");
        }else if(currentTap.equals(t.getText())){
            t.setTextColor(Color.WHITE);
            tl.setTextColor(Color.WHITE);
            mCurrent.setText(t.getText()+" is the morse code for T!");
        }else if(currentTap.equals(u.getText())){
            u.setTextColor(Color.WHITE);
            ul.setTextColor(Color.WHITE);
            mCurrent.setText(u.getText()+" is the morse code for U!");
        }else if(currentTap.equals(vv.getText())){
            vv.setTextColor(Color.WHITE);
            vl.setTextColor(Color.WHITE);
            mCurrent.setText(vv.getText()+" is the morse code for V!");
        }else if(currentTap.equals(w.getText())){
            w.setTextColor(Color.WHITE);
            wl.setTextColor(Color.WHITE);
            mCurrent.setText(w.getText()+" is the morse code for W!");
        }else if(currentTap.equals(x.getText())){
            x.setTextColor(Color.WHITE);
            xl.setTextColor(Color.WHITE);
            mCurrent.setText(x.getText()+" is the morse code for X!");
        }else if(currentTap.equals(y.getText())){
            y.setTextColor(Color.WHITE);
            yl.setTextColor(Color.WHITE);
            mCurrent.setText(y.getText()+" is the morse code for Y!");
        }else if(currentTap.equals(z.getText())){
            z.setTextColor(Color.WHITE);
            zl.setTextColor(Color.WHITE);
            mCurrent.setText(z.getText()+" is the morse code for Z!");
        }else{
            mCurrent.setText("No Sequence!");
        }
    }
}
