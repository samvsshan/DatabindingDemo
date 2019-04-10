package com.example.repluginsun;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.repluginsun.databinding.ActivityMainBinding;
import com.example.repluginsun.model.RecycleViewAdapter;
import com.example.repluginsun.model.UserPhoto;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private List<String> userPhotoList;
    private ArrayList<UserPhoto> userPhotoArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //接收宿主应用传递过来的数据
        Intent intent = getIntent();
        userPhotoList = intent.getStringArrayListExtra("userPostList");
        userPhotoList = new ArrayList<>();
        for (int i= 0;i<10;i++) {
            userPhotoList.add("https://sjbz-fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJlfJWBGIGWcKAAZPCehYNAYAAU-IAD5buIABk8h744.jpg");
        }
        if (userPhotoList!=null && userPhotoList.size()>0) {
            Log.d(TAG,"userPhotoList.size ="+userPhotoList.size());
            for (String url : userPhotoList) {
                UserPhoto userPhoto = new UserPhoto();
                userPhoto.setUserPhotoUrl(url);
                userPhotoArrayList.add(userPhoto);
            }

            RecyclerView.LayoutManager layoutManager;
            //需要设置recyclerview的layoutmanager，不然视图无法显示
            //第二个参数表示水平布局，第三个参数表示是否反转,视图从下往上滑
            layoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false); //表格布局
            //layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
            //layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);//瀑布流
            binding.recyclerTest.setLayoutManager(layoutManager);
            //初始化适配器
            RecycleViewAdapter recyclerViewAdapter = new RecycleViewAdapter(this,userPhotoArrayList);
            //绑定适配器
            binding.setAdapter(recyclerViewAdapter);
            //item的点击事件
            recyclerViewAdapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(MainActivity.this,"点击的position="+position,Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Log.d(TAG,"传递过来的数据为空不做处理");
        }

        TextView textView = findViewById(R.id.tv_version);
        textView.setText("v"+BuildConfig.VERSION_NAME);
    }
}
