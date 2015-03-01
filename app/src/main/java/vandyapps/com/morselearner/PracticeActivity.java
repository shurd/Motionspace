package vandyapps.com.morselearner;

import android.support.v4.app.Fragment;

/**
 * Created by Sam on 2/22/2015.
 */
public class PracticeActivity extends SingleFragmentActivity {
    protected Fragment createFragment(){
        return new PracticeFragment();
    }
}
