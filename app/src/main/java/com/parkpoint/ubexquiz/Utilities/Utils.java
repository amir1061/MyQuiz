package com.parkpoint.ubexquiz.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


public class Utils {

    public static void goToActivity(Context context, Class<?> to, boolean finishAfter) {
        Intent i = new Intent(context, to);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
    public static void mainToNewEntry(Context context, Class<?> to, boolean finishAfter,String val) {
        Intent i = new Intent(context, to);
        i.putExtra ("new",val);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
    public static void newToNext(Context context, Class<?> to, boolean finishAfter,String val1,String val2,String val3,String val4,String val5,String val6,String val7) {
        Intent i = new Intent(context, to);
        i.putExtra ("car", val1);
        i.putExtra ("car-letters", val2);
        i.putExtra ("ticket", val3);
        i.putExtra ("service", val4);
        i.putExtra ("name", val5);
        i.putExtra ("member-code", val6);
        i.putExtra ("country", val7);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
    public static void seachAllToPhase(Context context, Class<?> to, boolean finishAfter,String val) {
        Intent i = new Intent(context, to);
        i.putExtra ("ticket",val);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
    public static void p1Top2(Context context, Class<?> to, boolean finishAfter,String val) {
        Intent i = new Intent(context, to);
        i.putExtra ("p1-To-p2",val);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
    public static void p3Top5(Context context, Class<?> to, boolean finishAfter,String val) {
        Intent i = new Intent(context, to);
        i.putExtra ("p3-To-p5",val);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
    public static void p3Top6(Context context, Class<?> to, boolean finishAfter,String val) {
        Intent i = new Intent(context, to);
        i.putExtra ("p3-To-p6",val);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
    public static void p4Top5(Context context, Class<?> to, boolean finishAfter,String val) {
        Intent i = new Intent(context, to);
        i.putExtra ("p4-To-p5",val);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
    public static void p4Top5Payed(Context context, Class<?> to, boolean finishAfter,String val) {
        Intent i = new Intent(context, to);
        i.putExtra ("p4-To-p5-payed",val);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
    public static void p4Top6(Context context, Class<?> to, boolean finishAfter,String val) {
        Intent i = new Intent(context, to);
        i.putExtra ("p4-To-p6",val);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
    public static void p5Top6NonPayed(Context context, Class<?> to, boolean finishAfter,String val) {
        Intent i = new Intent(context, to);
        i.putExtra ("p5-To-p6-no-payment",val);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
    public static void p5Top6Payed(Context context, Class<?> to, boolean finishAfter,String val) {
        Intent i = new Intent(context, to);
        i.putExtra ("p5-To-p6-payment",val);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
    public static void forActivation(Context context, Class<?> to, boolean finishAfter,String val) {
        Intent i = new Intent(context, to);
        i.putExtra ("activation_barcode",val);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }

    public static void UsageHistory(Context context, Class<?> to, boolean finishAfter,String val) {
        Intent i = new Intent(context, to);
        i.putExtra ("member_code",val);
        context.startActivity(i);
        if (finishAfter) {
            ((Activity) context).finish();
        }
    }
}
