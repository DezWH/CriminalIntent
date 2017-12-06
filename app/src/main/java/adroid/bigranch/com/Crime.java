package adroid.bigranch.com;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Dez on 11/11/2017.
 */

public class Crime { //Beginning of Crime Class

    //Private global variables
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    //Crime Method Constructor
    public Crime()
    {
        //Instantiated object mmutable universally unique identifier
        mId = UUID.randomUUID();
        mDate = new Date();
    }
    //------------------------------------------------------------
    // Accessor: Gets ID and returns mId private immutible variable
    //--------------------------------------------------------------
    public UUID getId()
    {

        return mId;
    }
    public String getTitle()
    {
        return mTitle;
    }
    public void setTitle(String title)
    {
        mTitle = title;

    }
    //------------------------------------------------------------
    // Accessor: Gets Date and returns mDate private immutible variable
    //--------------------------------------------------------------
    public Date getDate()
    {
        return mDate;

    }
    //------------------------------------------------------------
    // Mutator: Sets Date and returns mDate private immutible variable
    //--------------------------------------------------------------
    public void setDate(Date date)
    {
        mDate = date;

    }
    //------------------------------------------------------------
    // Accessor: boolean ismSolved method and returns mSolved.
    //--------------------------------------------------------------
    public boolean ismSolved()

    {
        return mSolved;
    }
    public void setSolved(boolean solved)
    {
        mSolved = solved;
    }
}
