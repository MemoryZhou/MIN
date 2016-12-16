package com.simpleway.min.view;

import android.annotation.SuppressLint;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.simpleway.min.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DrawableActivity extends AbsActionbarActivity {

    @InjectView(R.id.imageView6)
    ImageView imageView6;

    private ClipDrawable cd;

    @SuppressLint("handlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                cd.setLevel(cd.getLevel() + 500);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ButterKnife.inject(this);
        clipPic();
    }


    @OnClick({R.id.imageView4, R.id.imageView6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView4:
                StartActivity(ViewActivity.class);
                break;
            case R.id.imageView6:
                clipPic();
                break;
        }
    }


    private void clipPic() {
        // 核心实现代码
        cd = (ClipDrawable) imageView6.getDrawable();
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
                if (cd.getLevel() >= 10000) {
                    timer.cancel();
                }
            }
        }, 0, 300);
    }
}
