package com.dataviewer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.common.CommonUtil;
import com.dataviewer.sheetAdapter.GreenCellAdapter;
import com.tbea.dataviewer.R;
import com.webservice.Server;
import com.webservice.Server.OnPackageDownloaded;
import com.webservice.Server.OnUserValidated;
import com.webservice.Server.OnVersionUpdated;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements Callback {

	Handler handler = new Handler(this);
	long exitTime = 0;
	long enterTime = 0;
	String meterData = "";
	boolean bAllShow = false;
	final static String meterHeader = "";
	private ProgressDialog dialog = null;
	private final static String updatePackage = "/tbea/TBEAReportPlatform.apk";

	@Override
	protected void onDestroy() {
		// System.exit(0);
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// outState.putString("reenter", "onSaveInstanceState");
		// super.onSaveInstanceState(outState);
	}

	void updatePackage(String path) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(path)),
				"application/vnd.android.package-archive");
		startActivity(intent);
		System.exit(0);
	}

	boolean exitUpdate(String info) {
		if (Server.getInstance().serverType() == Server.Type.WAN) {
			Server.resetAsLANServer(this);
			Message message = new Message();
			message.what = 100004;
			handler.sendMessageDelayed(message, 10);
			return false;
		} else {
			if (dialog != null) {
				dialog.hide();
			}
			Toast.makeText(this, info, 3000).show();
			Message message = new Message();
			message.what = 100003;
			handler.sendMessageDelayed(message, 3000);
			return true;
		}
	}

	int getAPKVersion(String url) {
		PackageManager packageManager = getPackageManager();
		PackageInfo packageInfo = packageManager.getPackageArchiveInfo(url,
				PackageManager.GET_ACTIVITIES);
		if (packageInfo == null) {
			return -1;
		}

		return packageInfo.versionCode;
	}

	int getVersion() {
		PackageInfo info = null;
		try {
			PackageManager manager = this.getPackageManager();
			info = manager.getPackageInfo(this.getPackageName(), 0);
			return info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;

	}

	private void enter() {
		if (dialog != null) {
			dialog.hide();
		}

		long cur = System.currentTimeMillis();
		Message message = new Message();
		message.what = 100001;

		if (cur - enterTime >= 2000) {
			handler.sendMessage(message);
		} else {
			handler.sendMessageDelayed(message, 2000 - (cur - enterTime));
		}
	}

	private void updateApp(int version, final String apkName,
			final boolean isLocalVersion) {
		final Server server = Server.getInstance();
		server.hasNewVersion(version, new OnVersionUpdated() {

			@Override
			public void onVersionUpdated(boolean hasNewVersion, final String url) {
				if (hasNewVersion) {
					if (dialog == null) {
						final AlertDialog.Builder builder = new AlertDialog.Builder(
								MainActivity.this);
						builder.setTitle("发现新版本");
						builder.setMessage("是否更新？");
						builder.setCancelable(false);
						builder.setPositiveButton("更新",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int which) {
										dialog = ProgressDialog.show(
												MainActivity.this, null,
												"正在下载更新，请稍侯...");
										server.downloadPackage(url, apkName,
												new OnPackageDownloaded() {

													@Override
													public void onDownloaded(
															boolean bSuccess,
															String path) {
														if (bSuccess) {
															updatePackage(path);
														} else {
															exitUpdate("网络异常，请检查后重新进入");
														}
													}
												});
									}
								});
						builder.setNegativeButton("退出",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int which) {
										System.exit(0);
									}
								});
						builder.create().show();
					}

				} else {
					if (url != null) {
						if (isLocalVersion) {
							updatePackage(apkName);
						} else {
							enter();
						}
					} else {
						exitUpdate("网络异常，请检查后重新进入");
					}
				}
			}
		});
	}

	private void updateCheck() {
		String apkName = Environment.getExternalStorageDirectory()
				+ updatePackage;
		File fLocalPackageFile = new File(apkName);
		int currentVersion = getVersion();
		if (fLocalPackageFile.exists()) {
			int localApkVersion = getAPKVersion(apkName);
			if (currentVersion >= localApkVersion) {
				updateApp(currentVersion, apkName, false);
			} else {
				updateApp(localApkVersion, apkName, true);
			}
		} else {
			updateApp(currentVersion, apkName, false);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// requestWindowFeature(Window.FEATURE_PROGRESS);
		super.onCreate(savedInstanceState);
		Server.resetAsWANServer(this);
		enterTime = System.currentTimeMillis();
		setContentView(R.layout.activity_main);
		updateCheck();
	}

	@Override
	public boolean handleMessage(Message arg0) {
		switch (arg0.what) {
		case 100001:
			
			Server.getInstance().validateUser(CommonUtil.getDeviceId(this),
					new OnUserValidated() {

						@Override
						public void onUserValidated(Boolean result) {
							
							if (null == result) {
								Toast.makeText(MainActivity.this,
										"网络异常，请检查后重新进入", 3000).show();
								Message message = new Message();
								message.what = 100003;
								handler.sendMessageDelayed(message, 3000);
							} else {
								FragmentTransaction ft = getFragmentManager()
										.beginTransaction();
								ft.add(R.id.host,
										result.booleanValue() ? new LoginPage()
												: new RegisterPage());
								ft.commit();
								if (null != findViewById(R.id.main_frame)
										.getBackground()) {
									findViewById(R.id.main_frame)
											.getBackground().setCallback(null);
									findViewById(R.id.main_frame)
											.setBackgroundDrawable(null);
								}
								findViewById(R.id.host).setVisibility(
										View.VISIBLE);
							}

						}
					});

			break;
		case 100003:
			System.exit(0);
			break;
		case 100004:
			updateCheck();
			break;
		// case 100002:
		// TextView tv = (TextView) findViewById(R.id.performance_meter);
		//
		// //应用程序最大可用内存
		// double maxMemory = ((double)(Runtime.getRuntime().maxMemory()/1024))
		// / 1024.0;
		// //应用程序已获得内存
		// double totalMemory = ((double)
		// (Runtime.getRuntime().totalMemory()/1024))/1024.0;
		// //应用程序已获得内存中未使用内存
		// double freeMemory = ((double)
		// (Runtime.getRuntime().freeMemory()/1024))/1024.0;
		//
		//
		// Debug.MemoryInfo memoryInfo=new Debug.MemoryInfo();
		// Debug.getMemoryInfo(memoryInfo);
		//
		// //应用程序最大可用内存
		// //double nativemaxMemory = ((double)(Debug.getNativeHeapSize()/1024))
		// / 1024.0;
		// //应用程序已获得内存
		// double nativetotalMemory = ((double)
		// (Debug.getNativeHeapAllocatedSize()/1024))/1024.0;
		// //应用程序已获得内存中未使用内存
		// //double nativefreeMemory = ((double)
		// (Debug.getNativeHeapFreeSize()/1024))/1024.0;
		//
		//
		// String currentMeterData = "\tDalvik heap (used/max)\t : "
		// + String.format("%8.3f", ((double) (totalMemory - freeMemory))) +
		// "M / "
		// + maxMemory
		// + "M\t\r\n\tDalvik Pss\t\t : "
		// + memoryInfo.dalvikPss / 1024.0
		// + "M\t\r\n\tNative Pss\t : "
		// + ((double) memoryInfo.nativePss / 1024.0)
		// + "M\t\r\n\tTotal Pss\t\t : "
		// + ((double) memoryInfo.getTotalPss() / 1024.0)
		// + "M\t\r\n";
		// meterData += currentMeterData;
		// if (bAllShow){
		// tv.setText(meterHeader + meterData);
		// }
		// else{
		// tv.setText(meterHeader + currentMeterData);
		// }
		//
		//
		// Message messagePerformance = new Message();
		// messagePerformance.what = 100002;
		// handler.sendMessageDelayed(messagePerformance, 1000);
		// break;
		default:
			break;
		}

		return false;
	}

	@Override
	public void onBackPressed() {
		if (0 == this.getFragmentManager().getBackStackEntryCount()) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出TBEA经营管理门户",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				System.exit(0);
			}
		} else {
			super.onBackPressed();
		}
	}

}
