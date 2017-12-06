package adroid.bigranch.com;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dez on 11/11/2017.
 */

public class CrimeLab {//Beginning of Crime Class

    //Global private static variables
    private static CrimeLab sCrimeLab;

    //
    private List<Crime> mCrimes;

    //-------------------------------------------------------------------------------------------
    // Accessor: Public static CrimeLab that get's the parameter context and returns sCrimeLab
    //------------------------------------------------------------------------------------------
    public static CrimeLab get(Context context)
    {
        //If sCrime == nul value
        if (sCrimeLab == null)
        {
            //Instatitates sCrimeLab obj.
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    // return sCrimeLab;
    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
    }
    public void addCrime(Crime c)

    {
        // Add (c) to the object mCrimes.
        mCrimes.add(c);
    }

    //-------------------------------------------------------
    // Array List that gets Crimes method and returns mCrimes
    //-------------------------------------------------------
    public List<Crime> getCrimes()


    {   //returns mCrimes
        return mCrimes;
    }

    //----------------------------------------------
    // Accessor: Gets Crime(UUID id) parameters
    //----------------------------------------------
    public Crime getCrime(UUID id) {
        //
        for (Crime crime : mCrimes) {    //----------------------------------------------------------------------------
            // The getId method is placed on the crime object and .equals(id) examines the Id
            //  and returns crime.
            //----------------------------------------------------------------------------
            if (crime.getId().equals(id)) {
                return crime;

            }
        }
        //returns null
        return null;

    }
}