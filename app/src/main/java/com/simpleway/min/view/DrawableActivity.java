package com.simpleway.min.view;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.TransitionDrawable;
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
    @InjectView(R.id.imageView7)
    ImageView imageView7;
    @InjectView(R.id.imageView8)
    ImageView imageView8;
    @InjectView(R.id.imageView9)
    ImageView imageView9;
    @InjectView(R.id.imageView10)
    ImageView imageView10;

    private ClipDrawable clipDrawable;
    private RotateDrawable rotateDrawable;
    private AnimationDrawable animationDrawable;
    private TransitionDrawable transitionDrawable;
    private LevelListDrawable levelListDrawable;
    private int count;

    @SuppressLint("handlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (clipDrawable.getLevel() >= 10000) {
                        clipDrawable.setLevel(0);
                    } else {
                        clipDrawable.setLevel(clipDrawable.getLevel() + 500);
                    }
                    break;
                case 2:
                    if (rotateDrawable.getLevel() >= 10000) {
                        rotateDrawable.setLevel(0);
                    } else {
                        rotateDrawable.setLevel(rotateDrawable.getLevel() + 400);
                    }
                    break;
                case 4:
                    if (count % 2 == 0) {
                        transitionDrawable.startTransition(2000);
                    } else {
                        transitionDrawable.reverseTransition(2000);
                    }
                    break;
                case 5:
                    if (levelListDrawable.getLevel() > 10000) {
                        levelListDrawable.setLevel(0);
                    }
                    imageView10.setImageLevel(levelListDrawable.getLevel() + 2000);
                    break;
            }

            transitionDrawable = (TransitionDrawable) imageView9.getDrawable();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ButterKnife.inject(this);
        go(1);
    }


    @OnClick({R.id.imageView4, R.id.imageView6, R.id.imageView7, R.id.imageView8, R.id.imageView9, R.id.imageView10})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView4:
                StartActivity(ViewActivity.class);
                break;
            case R.id.imageView6:
                go(1);
                break;
            case R.id.imageView7:
                go(2);
                break;
            case R.id.imageView8:
                go(3);
                break;
            case R.id.imageView9:
                count++;
                go(4);
                break;
            case R.id.imageView10:
                go(5);
                break;
        }
    }


    private void go(final int what) {
        switch (what) {
            case 1:
                clipDrawable = (ClipDrawable) imageView6.getDrawable();
                break;
            case 2:
                rotateDrawable = (RotateDrawable) imageView7.getDrawable();
                break;
            case 3:
                animationDrawable = (AnimationDrawable) imageView8.getDrawable();
                break;
            case 4:
                transitionDrawable = (TransitionDrawable) imageView9.getDrawable();
                break;
            case 5:
                levelListDrawable = (LevelListDrawable) imageView10.getDrawable();
                break;
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (what == 3) {
                    animationDrawable.start();
                } else {
                    handler.sendEmptyMessage(what);
                }
            }
        }, 0, 300);
    }
}
