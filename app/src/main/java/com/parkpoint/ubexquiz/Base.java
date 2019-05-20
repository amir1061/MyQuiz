package com.parkpoint.ubexquiz;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.parkpoint.ubexquiz.dialogs.Loading;

import es.dmoral.toasty.Toasty;

public class Base extends AppCompatActivity {

    private Loading loadingDialog;

    protected Context mContext;

    public int SUCCESS = 0;
    public int ERROR = 1;
    public int WARNING = 2;
    public int INFO = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mContext = Base.this;

        Thread.setDefaultUncaughtExceptionHandler (new Thread.UncaughtExceptionHandler () {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                Log.e ("ERRO "+Thread.currentThread ().getStackTrace ()[2],e.getLocalizedMessage ());
            }
        });
    }
    public void showLoading() {
        try {
            loadingDialog = new Loading(this);
            loadingDialog.show();
        } catch (Exception e) {
            Log.e("", "showLoading: ");
        }

    }
    public void hideLoading() {
        try {
            if (loadingDialog != null && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
                loadingDialog=null;
            }
        } catch (Exception e) {
            Log.e("", "hideLoading: ");
        }

    }


    public void showToast(String message, int type) {
        try {
            Toasty.Config.getInstance()
                    .setErrorColor(getResources().getColor(R.color.error_color)) // optional
                    .setInfoColor(getResources().getColor(R.color.info_color)) // optional
                    .setSuccessColor(getResources().getColor(R.color.success_color)) // optional
                    .setWarningColor(getResources().getColor(R.color.warning_color)) // optional
                    .setTextColor(getResources().getColor(R.color.white)) // optional
                    .tintIcon(false) // optional (apply textColor also to the icon)
                    .setToastTypeface(getTypeFace()) // optional
                    .setTextSize(16) // optional
                    .apply(); // required
            switch (type) {
                case 0:
                    Toasty.success(mContext, message, Toast.LENGTH_SHORT, true).show();
                    break;
                case 1:
                    Toasty.error(mContext, message, Toast.LENGTH_SHORT, true).show();
                    break;
                case 2:
                    Toasty.warning(mContext, message, Toast.LENGTH_SHORT, true).show();
                    break;
                case 3:
                    Toasty.info(mContext, message, Toast.LENGTH_SHORT, true).show();
                    break;
            }
        } catch (Exception e) {
            Log.e("", "showToast: ");
        }

    }


    public Typeface getTypeFace() {
        Typeface tf;
            tf = ResourcesCompat.getFont(mContext,R.font.montserratlight);
        return tf;
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }
}
