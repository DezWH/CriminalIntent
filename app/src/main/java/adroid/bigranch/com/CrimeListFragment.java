package adroid.bigranch.com;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Dez on 11/11/2017.
 */

public class CrimeListFragment extends Fragment {

    private static final int REQUEST_CRIME = 1;

    private TextView mTitleTextView;
    private TextView mDateTextview;
    private ImageView mSolvedImageView;

    private RecyclerView mCrimeRecylerView;
    private CrimeAdapter mAdapter;
    private Crime mCrime;


    @Override
    public View OnCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecylerView= (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mAdapater == null) {
            mAdapter = new CrimeAdapter(crimes);
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapater);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecylerView.setAdapter(mAdapter);

    }


    private class CrimeAdapter extends RecylerView.Adapter<CrimeHolder>
    {

        private List<Crime> mCrimes;

        public CrimeAdapter(List < Crime > crimes) {
        mCrimes = crimes;
    }
    @Override
            public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

        return new CrimeHolder(layoutInflater, parent);
    }

    @Override
            public void onBindVieHolder(CrimeHolder holder, int position){
    }
    @Override
            public int getItemCount() {
        return mCrimes.size();
    }
    }

 private class CrimeHolder extends RecyclerView.ViewHolder
         implements View.OnClickListener{
     private TextView mTitleTextview;
     private TextView mDateTextView;
     private Crime mCrime;
     mSolvedImageView.setVisibility(crime.isSolved()? View.VISIBLE : View.GONE);


     public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
         super(inflater.inflate(R.layout.list_item_crime, parent, false));
         itemView.setOnClickListener(this);

         mTitleTextview = (TextView) itemView.findViewById(R.id.crime_title);
         mDateTextView=(TextView) itemView.findViewById(R.id.crime_date);
         mSolvedImageView=(ImageView) itemView.findViewById(R.id.crime_solved);
     }


     @Override
     public void onBindViewHolder(CrimeHolder holder, int position){


     }
     @Override)
     public int getItemCount(){
         return mCrimes.size();

     }
 }
    @Override
    public CrimeHolder onCreateViewHolder (ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInfater.from(getActivity());

        return new CrimeHolder(layoutInflater, parent);

    }
    @Override
    public void onBindViewHolder(CrimeHolder holder, int position){


    }
    @Override)
    public int getItemCount(){
        return mCrimes.size();

    }

    mTitleTextView.setText(mCrime.getTitle());
    mDateTextView.setText(mCrime.getDate().toString();

}
}
@Override
public void onBindViewHolder(CrimeHolder holder, int position)
        {
        Crime crime = mCrimes.get(position);
        holder.bind(crime);

        public void bind(Crime crime)
        {
            mCrime = crime;
            mTitileTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getdate().toString());

        }
        }
        public CrimeHolder(LayoutInflater inflater, VieGroup parent)
        {
            super(inflater.inflat(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view){
           /* Toast.makeText(getActivity(),
        mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
            .show();
        */
        //Intent intent = new Intent(getActivity(), CrimeActivity.class);
     Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
        startActivityForResult(intent, REQUEST_CRIME);
        }
        }
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data){
            if(requestCode == REQUEST_CRIME)
            //Handle result
        {

        }
        }