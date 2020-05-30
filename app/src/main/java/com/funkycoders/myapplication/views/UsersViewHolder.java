package com.funkycoders.myapplication.views;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.funkycoders.myapplication.MainActivity;
import com.funkycoders.myapplication.R;
import com.funkycoders.myapplication.api.models.UsersResponse;
import com.funkycoders.myapplication.ui.HomeFragmentDirections;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = UsersViewHolder.class.getSimpleName();
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.container)
    CardView container;

    public UsersViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(UsersResponse usersResponse) {
        tvUsername.setText(usersResponse.getLogin());
        Glide.with(itemView)
                .load(usersResponse.getAvatarUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        ivAvatar.setImageResource(0);
                        ivAvatar.setImageDrawable(resource);

                        return false;
                    }
                })
                .into(ivAvatar);
        container.setOnClickListener(v -> {
            //To prevent crash when clicked from ListFragment
            try {
                MainActivity.getNavController().navigate(HomeFragmentDirections.actionShowUser(usersResponse));
            } catch (Exception e) {
                Log.e(TAG, "bind: ", e);
            }
        });
    }
}
