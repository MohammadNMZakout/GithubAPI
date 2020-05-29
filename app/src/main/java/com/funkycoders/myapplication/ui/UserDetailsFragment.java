package com.funkycoders.myapplication.ui;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.funkycoders.myapplication.R;
import com.funkycoders.myapplication.adapters.TabsAdapter;
import com.funkycoders.myapplication.api.RetrofitInstance;
import com.funkycoders.myapplication.api.interfaces.BadgeListener;
import com.funkycoders.myapplication.api.models.UserResponse;
import com.funkycoders.myapplication.api.models.UsersResponse;
import com.funkycoders.myapplication.utils.GUIUtil;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.funkycoders.myapplication.utils.Constants.ARG_FOLLOWERS;
import static com.funkycoders.myapplication.utils.Constants.ARG_FOLLOWING;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailsFragment extends Fragment implements BadgeListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_avatar)
    ImageView avatar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tv_bio)
    TextView tvBio;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_public_repos)
    TextView tvRepos;
    @BindView(R.id.tv_public_gists)
    TextView tvGists;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    private ProgressDialog loadingDialog;
    private AlertDialog alertDialog;
    private UsersResponse user;
    private TabsAdapter tabsAdapter;
    private View view;

    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, view);
        this.view = view;
        UserDetailsFragmentArgs args = UserDetailsFragmentArgs.fromBundle(getArguments());
        user = args.getUser();
        loadingDialog = GUIUtil.showProgressDialog(view.getContext(), String.format("Loading  %s's information,\nPlease wait.", user.getLogin()));
        alertDialog = GUIUtil.createAlertDialog(view.getContext(), "Something went wrong!.");
        toolbar.setTitle(user.getLogin().equals("") ? "" : user.getLogin());
        Glide.with(view).load(user.getAvatarUrl()).into(avatar);
        tabsAdapter = new TabsAdapter(getChildFragmentManager(), 0);
        getUserDetails();
        return view;
    }

    private void getUserDetails() {
        loadingDialog.show();

        RetrofitInstance.getService().getUser(user.getLogin()).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        loadResponse(response.body());
                    }
                } else {
                    loadingDialog.dismiss();
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                loadingDialog.dismiss();
                alertDialog.show();
            }
        });

    }

    private void loadResponse(UserResponse body) {

        tvBio.setText(body.getBio().equals("") ? "This user doesn't have bio" : body.getBio());
        tvName.setText(body.getName());
        tvRepos.setText(getString(R.string.template_repos, String.valueOf(body.getPublicRepos())));
        tvGists.setText(getString(R.string.template_gists, String.valueOf(body.getPublicGists())));
        tvLocation.setText(getString(R.string.template_location, body.getLocation() == null ? "No location sat yet" : body.getLocation()));
        tabsAdapter.addFragment(ListFragment.newInstance(ARG_FOLLOWING, user.getLogin(), this), ARG_FOLLOWING);
        tabsAdapter.addFragment(ListFragment.newInstance(ARG_FOLLOWERS, user.getLogin(), this), ARG_FOLLOWERS);
        viewPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(viewPager);
        loadingDialog.dismiss();
    }

    @Override
    public void updateBadge(String tab, int count) {
        int tabIndex = tab.equals(ARG_FOLLOWING) ? 0 : 1;
        BadgeDrawable badge = Objects.requireNonNull(tabLayout.getTabAt(tabIndex)).getOrCreateBadge();
        badge.setVisible(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            badge.setBackgroundColor(view.getContext().getColor(R.color.transparent));
            badge.setBadgeTextColor(view.getContext().getColor(R.color.colorPrimary));
        } else {
            badge.setBackgroundColor(view.getContext().getResources().getColor(R.color.transparent));
            badge.setBadgeTextColor(view.getContext().getResources().getColor(R.color.colorPrimary));

        }

        badge.setBadgeGravity(BadgeDrawable.BOTTOM_END);
        badge.setNumber(count);

    }
}
