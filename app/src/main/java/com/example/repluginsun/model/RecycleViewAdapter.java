package com.example.repluginsun.model;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.repluginsun.R;
import com.example.repluginsun.databinding.UserphotoItemBinding;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecyclerViewHolder> implements View.OnClickListener {
    private List<UserPhoto> userPhotoList;
    private LayoutInflater layoutInflater;

    /**
     * 用于模仿listview的itemclick事件，recyclerview没有itemclick
     */
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public RecycleViewAdapter (Context context, List<UserPhoto> list) {
        this.userPhotoList = list;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        UserphotoItemBinding binding = DataBindingUtil.inflate(layoutInflater,R.layout.userphoto_item,viewGroup,false);
        binding.getRoot().setOnClickListener(this);
        return new RecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder viewHolder, int i) {
        UserphotoItemBinding itemMvvmBinding = viewHolder.getBinding();
        UserPhoto userBean = userPhotoList.get(i);
        itemMvvmBinding.setUserPhoto(userBean);
        //将position保存在itemView的Tag中，以便点击时进行获取,不设置在点击是会报空指针
        itemMvvmBinding.getRoot().setTag(i);
        // 立刻执行绑定
        itemMvvmBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return userPhotoList == null ? 0 : userPhotoList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(imageView.getContext()).applyDefaultRequestOptions(options).load(url).into(imageView);
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        //这里只要给RecyclerView.ViewHolder返回一个view就可以，所以我们将构造方法中传入databinding
        UserphotoItemBinding itemMvvmBinding;

        public RecyclerViewHolder(UserphotoItemBinding itemMvvmBinding) {
            super(itemMvvmBinding.getRoot());
            this.itemMvvmBinding = itemMvvmBinding;
        }

        public UserphotoItemBinding getBinding() {
            return itemMvvmBinding;
        }

        public void setBinding(UserphotoItemBinding itemMvvmBinding) {
            this.itemMvvmBinding = itemMvvmBinding;
        }
    }
}
