package com.example.helloji;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrendingFragment extends ListFragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrendingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrendingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrendingFragment newInstance(String param1, String param2) {
        TrendingFragment fragment = new TrendingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Tags, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Trending Now");

        return inflater.inflate(R.layout.fragment_trending, container, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String[] mess = getResources().getStringArray(R.array.Tags);
        Toast.makeText(getActivity(), "People Nearby: " + (15 - position), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    Intent intent = new Intent(getContext(),ProfileActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
}
