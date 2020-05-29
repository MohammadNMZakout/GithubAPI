package com.funkycoders.myapplication.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.funkycoders.myapplication.R;
import com.funkycoders.myapplication.adapters.UsersAdapter;
import com.funkycoders.myapplication.api.RetrofitInstance;
import com.funkycoders.myapplication.api.models.UsersResponse;
import com.funkycoders.myapplication.utils.GUIUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();


    @BindView(R.id.rv_users)
    RecyclerView rvUsers;
    @BindView(R.id.page_counter)
    ElegantNumberButton btnPagination;

    private UsersAdapter adapter;
    private List<UsersResponse> mList = null;
    private int page = 1;
    private ProgressDialog loadingDialog;
    private AlertDialog alertDialog;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        rvUsers.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        loadingDialog = GUIUtil.showProgressDialog(view.getContext(), "Loading...\nPlease wait");
        alertDialog = GUIUtil.createAlertDialog(view.getContext(), "Something went wrong");
        adapter = new UsersAdapter(mList);
        rvUsers.setAdapter(adapter);
        loadPage();
        btnPagination.setOnClickListener((ElegantNumberButton.OnClickListener) view1 -> {
            page = Integer.parseInt(btnPagination.getNumber());
        });
        btnPagination.setRange(1, 100);
        btnPagination.setOnValueChangeListener((v, oldValue, newValue) -> {
            page = newValue;
            loadPage();
        });
        return view;
    }

    private void loadPage() {
        loadingDialog.show();
        RetrofitInstance.getService()
                .getUsers(30, page)
                .enqueue(new Callback<List<UsersResponse>>() {
                    @Override
                    public void onResponse(Call<List<UsersResponse>> call, Response<List<UsersResponse>> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                adapter.updateList(response.body());
                                loadingDialog.dismiss();
                            }
                        } else {
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UsersResponse>> call, Throwable t) {
                        loadingDialog.dismiss();
                        alertDialog.show();
                    }
                });
    }

}
