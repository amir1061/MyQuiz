package com.parkpoint.ubexquiz;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parkpoint.ubexquiz.Api.ApiClient;
import com.parkpoint.ubexquiz.MenuScreen.MenuScreen;
import com.parkpoint.ubexquiz.Model.User;
import com.parkpoint.ubexquiz.SharedPrefs.SharedPrefs;
import com.parkpoint.ubexquiz.Utilities.Constants;
import com.parkpoint.ubexquiz.Utilities.Utils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Base {

    private EditText e1,e2;
    private Button login;
    private String UserName;
    private String Password;

    private SharedPrefs sharedPrefs;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        e1 = findViewById (R.id.e1);
        e2 = findViewById (R.id.e2);

        login = findViewById (R.id.login);
        sharedPrefs = new SharedPrefs (getApplicationContext ());
        apiInterface = ApiClient.getApiClient ().create (ApiInterface.class);

        login.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                performLogin ();
            }
        });
    }

    public void performLogin() {
        String name = e1.getText ().toString ().trim ();
        String pass = e2.getText ().toString ().trim ();
        if (ConnectionClass.isInternetConnection (MainActivity.this)) {
            showLoading ();
            apiInterface.performUserLogin (name, pass)
                    .subscribeOn (Schedulers.io ())
                    .observeOn (AndroidSchedulers.mainThread ())
                    .subscribe (new Observer<User> () {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(User user) {
                            assert user.getResonse () != null;
                            switch (user.getResonse ()) {
                                case "ok":
                                    sharedPrefs.setToken (user.getToken ());

                                    showToast ("Loged in", SUCCESS);
                                    e1.setText ("");
                                    e2.setText ("");
                                    Utils.goToActivity (MainActivity.this, MenuScreen.class,true);
                                    hideLoading ();
                                    break;
                                default:
                                    showToast ("Enter Valid Details", ERROR);
                                    hideLoading ();
                                    e1.setText ("");
                                    e2.setText ("");
                                    break;
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            hideLoading ();
                            showToast ("Error accured! Please try again", ERROR);
                        }

                        @Override
                        public void onComplete() {
                            hideLoading ();
                        }
                    });
        } else {
            showToast ("No Internet", ERROR);
        }
    }
}
