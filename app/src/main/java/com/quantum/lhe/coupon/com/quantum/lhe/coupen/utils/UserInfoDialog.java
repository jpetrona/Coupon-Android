package com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.quantum.lhe.coupon.R;


/**
 * Created by Sharjeel on 1/21/2016.
 */
public class UserInfoDialog {

	public static Dialog createDialog(Context context, int messageId,
									  DialogInterface.OnClickListener onClick) {
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(messageId)
				.setPositiveButton(R.string.yes_title, onClick)
				.setNegativeButton(R.string.no_title,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// Do Nothing
							}
						});
		// Create the AlertDialog object and return it
		return builder.create();

	}

	public static Dialog createDialog(Context context, String messageId,
									  DialogInterface.OnClickListener onClick) {
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogStyle);
		builder.setTitle(messageId)
				.setPositiveButton(context.getString(R.string.ok), onClick)
				.setNegativeButton(context.getString(R.string.cancel_title),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// Do Nothing
							}
						});
		// Create the AlertDialog object and return it
		return builder.show();

	}


	public static void showResult(Context context,String title, String alertMessage) {
		new android.support.v7.app.AlertDialog.Builder(context)
				.setTitle(title)
				.setMessage(alertMessage)
				.setPositiveButton(context.getString(R.string.ok), null)
				.show();
	}

}
