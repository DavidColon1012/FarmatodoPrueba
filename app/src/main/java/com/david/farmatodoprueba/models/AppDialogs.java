package com.david.farmatodoprueba.models;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.david.farmatodoprueba.R;

public class AppDialogs {
    public static ProgressDialog createLoading(Context context) {
        return ProgressDialog.show(context, "", context.getResources().getString(R.string.espere), true);
    }

    public static Dialog createDialog(Context context, final String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setPositiveButton(context.getResources().getString(R.string.de_acuerdo), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        return builder.create();
    }
}
