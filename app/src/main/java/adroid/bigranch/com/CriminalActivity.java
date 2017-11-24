package adroid.bigranch.com;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CriminalActivity extends SingleFramgmentActivity {

    public static final String EXTRA_CRIME_ID =
            "com.adroid.bigranch.com.criminalintent.crime_id";

    public static Intent newInent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new CrimeFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)

                    .commit();
        }


        }
        @Override
        protected  Fragment createFragment(){
        return new CrimeFragment();

    }

}
