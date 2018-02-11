package adroid.bigranch.com;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class CrimeFragment extends Fragment
{ //Beginning of CrimeFragment class that extends


    //--------------------------------------------------------------------------
    // Private statics Strings that can't be changed and instantiated as objects.
    //---------------------------------------------------------------------------
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;

    //Global Varables
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;


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
    public void onPause()
    {
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
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode != Activity.RESULT_OK) {
                return;

            }

            if (requestCode == REQUEST_DATE) {
                Date date = (Date) data
                        .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
                mCrime.setDate(date);
                updateDate();
            }
        }
    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());

    }
}

