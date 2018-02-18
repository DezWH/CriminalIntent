package adroid.bigranch.com;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import java.util.Date;

import static android.widget.CompoundButton.*;

import java.util.UUID;

/**
 * Created by Dez on 11/11/2017.
 */

public class CrimeFragment extends Fragment { //Beginning of CrimeFragment class that extends


    //--------------------------------------------------------------------------
    // Private statics Strings that can't be changed and instantiated as objects.
    //---------------------------------------------------------------------------
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1; // Asking android for contact to help pick
    // an item in the request code and member variable

    //Global Varables
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mReportButton;
    private Button mSuspectButton;


    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);


        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;

    }

    //---------------------------------------------------------
    //Will be called by Activity hosting the fragment
    //The onCreateView metthod is the place to wire up widgets
    //------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();

        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public void onPause() {
        super.onPause();

        CrimeLab.get(getActivity())
                .updateCrime(mCrime);
    }

    //-----------------------------------------------------------------------------
    // Create amd coonfigure the fragment's view in another framgent lifecycle method.
    // It's where you inflate the layout for the frament's view and return the inflated
    // View to the hosting Activity.
    //-----------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //The Bundle will contain data that this method can use to
        // to re-creaet the view from a saved state.

        // Sending A Crime report in CriminalIntent.
        mReportButton = (Button) v.findViewById(R.id.crime_report);
        mReportButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
                i.putExtra(Intent.EXTRA_SUBJECT,
                        getString(R.string.crime_report_subject));

                // Using a Chooser to display the activities that respond to your
                // implicit intent.
                i = Intent.createChooser(i, getString(R.string.send_report));

                startActivity(i); // Then you pass the intent returned from createChooser
                // into startActvity.
            }
        });
        return v;
    }

    // Get a reference to the button and set a listener on it.
    // Within the listeners's implementation, create the implici intent
    // and pass  it into startActivity
    final Intent pickContact = new Intent(Intent.ACTION_PICK,
            ContactsContract.Contacts.CONTENT_URI);
    mSuspectButton =(Button)v.findViewById(R.id.crime_suspect);
    mSuspectButton.setOnClickListener(new View.OnClickListener()

    {

        public void onClick (View v)
        {
            startActivityForResult(pickContact, REQUEST_CONTACT);

        }
    });
}

    if (mCrime.getmSuspect()!= null){
        mSuspectButton.setText(mCrime.getmSuspect());

                                         }

                                         return;
        }

        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged
                    (CharSequence s, int i, int i1, int i2) {
                //This space intentionally left blank
            }
            @Override
            public void onTextChanged
                    (CharSequence s, int i, int i1, int i2) {
                    mCrime.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged
                    (Editable s) {// This is one tooo
            }
        });
        //-------------------------
        // Calls View.findViewById(int) behind the scenes
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
           public void onClick (View v){
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.
                        newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);

            }
        });
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonview, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }
    // Pulling contact name out
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode != Activity.RESULT_OK) {
                return;

            }

            if (requestCode == REQUEST_DATE){
        Date date=(Date)data
        .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
        mCrime.setDate(date);
        updateDate();
        }
        else if(requestCode == REQUEST_CONTACT && data != null)
        {
            Uri contatUri = date.getDate();

            // Spcify which fields you want your query to return
            // values for
            String [] queryFields = new String[]
        {
                    ContactsContract.Contacts.DISPLAY_NAME

            };

            // Perform your query - the contractUri is like a "where"
        // clause here
        Cursor c = getActivity().getContentResolver(0
        .query(contactUri, queryFields, null, null, null);

        try{
        // Double-check that you actually got results
        if(c.getCount()==0)
        {
        return;
        }

        // Pull out the first column of the first row of date -
        // that is your suspect's name
        c.moveToFirst();
        String suspect=c.getString(0);
        mCrime.setSuspect(suspect);
        mSuspectButton.setText(suspect);
        }finally{
        c.close();
            }
        }
}

        // Add a method that creates four strings and then pieces them together
        // and returns a complete report.

    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());

    }
    private String getCrimeReport()
    {
        String solvedString = null;
        if (mCrime.isSolved()) {
            solvedString = getString(R.string.crime_report_solved);
        }else{
            solvedString=getString(R.string.crime_report_unsolved);
        }

        String dateFormat = "EEE, MMM dd";
                String dateString = DateFormat.Format(dateFormat,
                        mCrime.getDate()).toString();

        String suspect = mCrime.getmSuspect();
        if(suspect == null) {
            suspect = getString(R.string.crime_report_no_suspect);
        }else{
        }
        String report = getString(R.string.crime_report,
                mCrime.getTitle(), dateString, solvedString, suspect);

        return report
    }
}

