package adroid.bigranch.com;

import android.support.v4.app.Fragment;

/**
 * Created by Dez on 11/11/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity
{

    @Override
    protected Fragment createFragment()
    {
        return new CrimeListFragment();

    }
}
