package com.parkpoint.ubexquiz.MenuScreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.parkpoint.ubexquiz.Adapters.ShipmentListAdapter;
import com.parkpoint.ubexquiz.Api.ApiClient;
import com.parkpoint.ubexquiz.ApiInterface;
import com.parkpoint.ubexquiz.Base;
import com.parkpoint.ubexquiz.ConnectionClass;
import com.parkpoint.ubexquiz.MainActivity;
import com.parkpoint.ubexquiz.Model.ShipmentStatus;
import com.parkpoint.ubexquiz.Model.Shipments;
import com.parkpoint.ubexquiz.R;
import com.parkpoint.ubexquiz.SharedPrefs.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MenuScreen extends Base {

    boolean doubleBackToExitPressedOnce = false;
    private Button b_1,b_2;
    private LinearLayout menu_lay,shipment_lay;
    private EditText e1;
    private ListView listview;
    private ApiInterface apiInterface;
    private CompositeDisposable mCompositeDisposable;
    private ArrayList <Shipments> shipmentsArrayList;
    private SharedPrefs sharedPrefs;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_menu_screen);

        menu_lay = findViewById (R.id.menu_lay);
        shipment_lay = findViewById (R.id.shipment_lay);
        e1 = findViewById (R.id.search_ship);
        listview = findViewById (R.id.listview_1);

        b_1 = findViewById (R.id.b1);
        b_2 = findViewById (R.id.b2);
        apiInterface = ApiClient.getApiClient ().create (ApiInterface.class);
        mCompositeDisposable = new CompositeDisposable ();
        sharedPrefs = new SharedPrefs (getApplicationContext ());
        dialog = new Dialog (this);

        b_2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                LoadShipmentsList ();
            }
        });

        listview.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                String reference = shipmentsArrayList.get (position).getReference ();
                String status = shipmentsArrayList.get (position).getStatus ();
                String expected = shipmentsArrayList.get (position).getDelivered ();
                String created = shipmentsArrayList.get (position).getCreated_at ();
                ShowDetails (reference,status,expected,created);
            }
        });

    }




    private void LoadShipmentsList() {
        if (ConnectionClass.isInternetConnection (MenuScreen.this)){
            showLoading ();
            apiInterface.GetShipments (sharedPrefs.getToken ())
                    .subscribeOn (Schedulers.io ())
                    .observeOn (AndroidSchedulers.mainThread ())
                    .subscribe (new Observer <ShipmentStatus> () {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ShipmentStatus shipmentStatus) {
                            hideLoading ();
                            if (shipmentStatus.getStatus ().equals ("ok")){
                                menu_lay.setVisibility (View.GONE);
                                shipment_lay.setVisibility (View.VISIBLE);
                                handleShipments (shipmentStatus.getShipmentsList ());
                            }else {
                                showToast ("Unknown Response",ERROR);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            hideLoading ();
                            showToast ("Network Error "+e,ERROR);
                        }

                        @Override
                        public void onComplete() {
                            hideLoading ();
                        }
                    });

        }else {
            showToast ("No internet",ERROR);
        }

    }

    private void handleShipments(List <Shipments> shipmentsList) {
        shipmentsArrayList = new ArrayList<> (shipmentsList);
        listview.setAdapter (new ShipmentListAdapter (getApplicationContext (), shipmentsArrayList));

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            LogOut ();
            return;
        }else {
            menu_lay.setVisibility (View.VISIBLE);
            shipment_lay.setVisibility (View.GONE);
            this.doubleBackToExitPressedOnce = true;
        }


        new Handler ().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void LogOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder (this);
        builder.setCancelable (false);
        builder.setMessage ("Do you want to Logout?");
        builder.setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PreferenceManager.getDefaultSharedPreferences (getBaseContext ())
                        .edit ()
                        .clear ()
                        .apply ();
                Intent intent = new Intent (MenuScreen.this, MainActivity.class);
                intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity (intent);
                finish ();
            }
        });
        builder.setNegativeButton ("No", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel ();
            }
        });
        AlertDialog alert = builder.create ();
        alert.getWindow ();
        alert.show ();
    }

    private void ShowDetails(String reference,String status,String expected,String created){
        dialog.setContentView (R.layout.shipment_details);
        dialog.setCancelable (false);

        TextView t1 = dialog.findViewById (R.id.dt_1);
        TextView t2 = dialog.findViewById (R.id.dt_2);
        TextView t3 = dialog.findViewById (R.id.dt_3);
        TextView t4 = dialog.findViewById (R.id.dt_4);

        Button cancle = dialog.findViewById (R.id.cancle);

        t1.setText (reference);
        t2.setText (status);
        t3.setText (expected);
        t4.setText (created);

        cancle.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                dialog.cancel ();
            }
        });
        dialog.getWindow ().setLayout (ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow ().setBackgroundDrawable (new ColorDrawable (Color.TRANSPARENT));
        dialog.show ();
    }
}
