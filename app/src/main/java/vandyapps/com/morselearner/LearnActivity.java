package vandyapps.com.morselearner;

import android.support.v4.app.Fragment;

/**
 * Created by Sam on 2/21/2015.
 */
public class LearnActivity extends SingleFragmentActivity {
    protected Fragment createFragment(){
        return new LearnFragment();
    }
}
