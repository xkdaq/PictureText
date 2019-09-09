package com.picature.text;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String[] permission = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private Handler mHandler = new Handler();
    RxPermissions rxPermissions = new RxPermissions(this);

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rxPermissions.request(permission)
                .subscribe(granted -> {
                    if (granted) {

                    }
                });

    }


    private void getShareImg(final View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 要在运行在子线程中
                // 获取图片
                Bitmap bitmap = view.getDrawingCache();
                if (bitmap == null) {//处理华为meta9等手机出现的问题
                    bitmap = Bitmap.createBitmap(view.getWidth(),
                            view.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    view.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(),
                            View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(view.getHeight(), View.MeasureSpec.EXACTLY));
                    view.layout((int) view.getX(),
                            (int) view.getY(),
                            (int) view.getX() + view.getMeasuredWidth(),
                            (int) view.getY() + view.getMeasuredHeight());
                    view.draw(canvas);
                }
                savePicture(bitmap, "qufuusershare.jpg");// 保存图片
                view.destroyDrawingCache(); // 保存过后释放资源
            }
        }, 1000);
    }

    public void savePicture(Bitmap bm, String fileName) {
        if (null == bm) {
            return;
        }
        File foder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/qufuuser");
        if (!foder.exists()) {
            foder.mkdirs();
        }
        File myCaptureFile = new File(foder, fileName);
        try {
            if (!myCaptureFile.exists()) {
                myCaptureFile.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            //压缩保存到本地
            bm.compress(Bitmap.CompressFormat.JPEG, 90, bos);
            bos.flush();
            bos.close();
            //分享图片（myCaptureFile ）
            //myCaptureFile
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
