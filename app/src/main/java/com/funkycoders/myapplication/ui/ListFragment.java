package com.funkycoders.myapplication.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.funkycoders.myapplication.R;
import com.funkycoders.myapplication.adapters.UsersAdapter;
import com.funkycoders.myapplication.api.RetrofitInstance;
import com.funkycoders.myapplication.api.interfaces.BadgeListener;
import com.funkycoders.myapplication.api.models.UsersResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.funkycoders.myapplication.utils.Constants.ARGS_CASE;
import static com.funkycoders.myapplication.utils.Constants.ARGS_USERNAME;
import static com.funkycoders.myapplication.utils.Constants.ARG_FOLLOWERS;
import static com.funkycoders.myapplication.utils.Constants.ARG_FOLLOWING;


public class ListFragment extends Fragment {


    private static final String TAG = ListFragment.class.getSimpleName();
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private List<UsersResponse> mList;
    private String mCase;
    private String username;
    private UsersAdapter adapter;
    private BadgeListener badgeListener;

    public ListFragment() {
        // Required empty public constructor
    }


    public static ListFragment newInstance(String mCase, String userLogin, BadgeListener badgeListener) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_CASE, mCase);
        args.putString(ARGS_USERNAME, userLogin);
        fragment.setArguments(args);
        fragment.badgeListener = badgeListener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCase = getArguments().getString(ARGS_CASE);
            username = getArguments().getString(ARGS_USERNAME);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        adapter = new UsersAdapter(mList);
        rvList.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        rvList.setAdapter(adapter);
        Call<List<UsersResponse>> call;
        Log.d(TAG, "onCreateView: ");
        switch (mCase) {
            case ARG_FOLLOWERS:
                call = RetrofitInstance.getService().getFollowers(username);
                Log.d(TAG, "onCreateView: " + ARG_FOLLOWERS);
                makeRequest(call);
                break;
            case ARG_FOLLOWING:
                call = RetrofitInstance.getService().getFollowings(username);
                Log.d(TAG, "onCreateView: " + ARG_FOLLOWING);
                makeRequest(call);
                break;
            default:
                break;
        }

        return view;
    }

    private void makeRequest(Call<List<UsersResponse>> call) {
        Log.d(TAG, "makeRequest: ");
        call.enqueue(new Callback<List<UsersResponse>>() {
            @Override
            public void onResponse(Call<List<UsersResponse>> call, Response<List<UsersResponse>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse:isSuccessful ");
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().size());
                        adapter.updateList(response.body());
                        badgeListener.updateBadge(mCase, response.body().size());
                    }
                } else {
                    Log.d(TAG, "onResponse: Failed");
                }
            }

            @Override
            public void onFailure(Call<List<UsersResponse>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}