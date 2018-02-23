package com.cpm.alert_message;

import org.acra.ACRA;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.Toast;

import com.cpm.download.CompleteDownloadActivity;
import com.cpm.gsk.GSKOralCareActivity;
import com.cpm.gsk.PerformanceDetailAuthentication;
import com.cpm.mainmenu.MainMenuScreenActivity;
import com.cpm.upload.UploadSale;

public class AlertMessageActivity {

	public AlertMessageActivity(){

	}

	Exception es;
	String value;
	private Exception exception;
	private String data, condition;
	private Activity activity;
	public static String Message_problem = "Problem Occured : Do you want to Report the problem to Developer";
	public static String Message_network = "Internet Not Available.Please Check Your Network Connection";
	public static final String MESSAGE_EXCEPTION = "Problem Occured : Report The Problem To Parinaam";
	public static final String MESSAGE_SOCKETEXCEPTION = "Internet Not Available. Check Your Network Connection";
	public static final String MESSAGE_NO_DATA = "No Data To Upload";
	public static final String MESSAGE_ERROR = "Network Connection Problem , ";
	public static final String MESSAGE_DOWNLOAD = "Data Downloaded Successfully";
	public static final String MESSAGE_UPLOAD = "Data Uploaded Successfully";

	public AlertMessageActivity(Activity activity, String data,
			String condition, Exception exception) {
		this.activity = activity;
		this.data = data;
		this.condition = condition;
		this.exception = exception;
	}

	public void showMessage() {
		if (condition.equals("valid")) {

			// ShowAlert(data);
		} else if (condition.equals("invalid")) {

			// ShowAlert1(data);

		}
		else if (condition.equals("network error")) {

		Error(data);

		}

		else if (condition.equals("socket")) {

			socket(data);

		}

		else if (condition.equals("socket_upload")) {

			socket_upload(data);

		}

		else if (condition.equals("socket_download")) {

			socket_download(data);

		}

		else if (condition.equals("upload")) {

			// ShowAlertgeotag(data);

		} else if (condition.equals("download")) {

			acra(data);

		} else if (condition.equals("login")) {

			// ShowAlert5(data);
		} else if (condition.equals("success")) {

			ShowAlert1(data);
		}

		else if (condition.equals("success_upload")) {

			upload_succcess(data);
		}

		else if (condition.equals("login_success")) {

			login(data);
		}

		else if (condition.equals("acra")) {

			acra_login(data);
		}
	}

	public void socket(String str) {

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle("Parinaam");
		builder.setMessage(str)
				.setCancelable(false)
				.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						Intent i = new Intent(activity,
								activity.getClass());
						activity.startActivity(i);
						activity.finish();

					}
				})

				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
								Intent i = new Intent(activity,
										GSKOralCareActivity.class);
								activity.startActivity(i);
								activity.finish();

							}
						});

		AlertDialog alert = builder.create();
		alert.show();

	}

	public void socket_upload(String str) {

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle("Parinaam");
		builder.setMessage(str)
				.setCancelable(false)
				.setPositiveButton("Try Again",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								Intent i = new Intent(activity,
										UploadSale.class);
								activity.startActivity(i);
								activity.finish();
							}
						})

				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
								Intent i = new Intent(activity,
										MainMenuScreenActivity.class);
								activity.startActivity(i);
								activity.finish();
							}
						});

		AlertDialog alert = builder.create();
		alert.show();

	}

	public void socket_download(String str) {

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle("Parinaam");
		builder.setMessage(str)
				.setCancelable(false)
				.setPositiveButton("Try Again",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								Intent i = new Intent(activity,
										CompleteDownloadActivity.class);
								activity.startActivity(i);
								activity.finish();
							}
						})

				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
								Intent i = new Intent(activity,
										MainMenuScreenActivity.class);
								activity.startActivity(i);
								activity.finish();
							}
						});

		AlertDialog alert = builder.create();
		alert.show();

	}

	public void ShowAlert1(String str) {

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle("Parinaam");
		builder.setMessage(str).setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						Intent i = new Intent(activity,
								MainMenuScreenActivity.class);
						
						activity.startActivity(i);
						activity.finish();

					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}
	public void Error(String str) {

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle("Parinaam");
		builder.setMessage(str).setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						
						Intent i = new Intent(activity,
								activity.getClass());
						activity.startActivity(i);
						activity.finish();

					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}
	public void upload_succcess(String str) {

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle("Parinaam");
		builder.setMessage(str).setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						Intent i = new Intent(activity,
								PerformanceDetailAuthentication.class);
						activity.startActivity(i);
						activity.finish();

					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}

	public void login(String str) {

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle("Parinaam");
		builder.setMessage(str).setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}

	public void acra_login(String str) {

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle("Parinaam");

		builder.setMessage(str)
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								ACRA.getErrorReporter().handleException(
										exception);
								dialog.cancel();
								Intent i = new Intent(activity,
										GSKOralCareActivity.class);
								activity.startActivity(i);
								activity.finish();

							}
						})

				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						Intent i = new Intent(activity,
								GSKOralCareActivity.class);
						activity.startActivity(i);
						activity.finish();

					}
				});

		AlertDialog alert = builder.create();
		alert.show();

	}

	public void acra(String str) {

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle("Parinaam");

		builder.setMessage(str)
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								ACRA.getErrorReporter().handleException(
										exception);
								Intent i = new Intent(activity,
										MainMenuScreenActivity.class);
								activity.startActivity(i);
								activity.finish();

							}
						})

				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						Intent i = new Intent(activity,
								MainMenuScreenActivity.class);
						activity.startActivity(i);
						activity.finish();

					}
				});

		AlertDialog alert = builder.create();
		alert.show();

	}

}
