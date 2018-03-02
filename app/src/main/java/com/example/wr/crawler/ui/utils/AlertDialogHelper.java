package com.example.wr.crawler.ui.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by loadm on 2018-03-02.
 */

public class AlertDialogHelper {

    public static void showOKOnlyAlertDialog(Context context, int messageStringId, DialogInterface.OnClickListener positiveListener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(messageStringId);
        builder.setPositiveButton(android.R.string.ok, positiveListener);
        builder.setCancelable(false);
        builder.create().show();
    }

    public static void showAlertDialog(Context context, int messageStringId, DialogInterface.OnClickListener positiveListener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(messageStringId);
        builder.setPositiveButton(android.R.string.ok, positiveListener);
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.create().show();
    }
}
