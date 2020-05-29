package com.funkycoders.myapplication.views;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.funkycoders.myapplication.R;
import com.funkycoders.myapplication.api.models.UsersResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;

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
                        //This is used to remove the placeholder image from your ImageView
                        //and load the downloaded image with desired scale-type(FIT_XY in this case)
                        //Changing the scale type from 'CENTER_INSIDE' to 'FIT_XY'
                        //will stretch the placeholder for a (very) short duration,
                        //till the downloaded image is loaded
                        ivAvatar.setImageResource(0);
                        ivAvatar.setScaleType(ImageView.ScaleType.FIT_XY);
                        ivAvatar.setImageDrawable(resource);
                        //before setting the scale type to FIT_XY and ensures that the UX
                        //is not spoiled, even for a (very) short duration
                        //holder.ivBuilderLogo.setImageResource(0);
                        //holder.ivBuilderLogo.setScaleType(ImageView.ScaleType.FIT_XY);

                        return false;
                    }
                })
                .into(ivAvatar);

    }
}
