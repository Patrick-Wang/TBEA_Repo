package com.tbea.ic.scanner.page.camera;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;

import com.google.zxing.client.android.BeepManager;

import com.google.zxing.client.android.ViewfinderView;
import com.google.zxing.client.android.camera.CameraManager;
import com.page.core.Page;
import com.squareup.otto.Produce;
import com.tbea.ic.scaner.R;

import android.graphics.Bitmap;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPage extends Page implements SurfaceHolder.Callback {

	private CameraManager cameraManager;
	private CameraPageHandler handler;
	private Result savedResultToShow;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Collection<BarcodeFormat> decodeFormats;
	private Map<DecodeHintType, ?> decodeHints;
	private String characterSet;
	private BeepManager beepManager;
	ScanEvent scanEvent = null;
	@Override
	protected void onInitialize() {
		beepManager = new BeepManager(getActivity());
		cameraManager = new CameraManager(this.getActivity().getApplication());
		viewfinderView = (ViewfinderView) query(R.id.viewfinder_view)
				.getView();
		viewfinderView.setCameraManager(cameraManager);
		

		beepManager.updatePrefs();
	}


	  @Override
	  public void onPause() {
	    if (handler != null) {
	      handler.quitSynchronously();
	      handler = null;
	    }
	    cameraManager.closeDriver();
	    if (!hasSurface) {
	    	SurfaceView surfaceView = (SurfaceView) query(R.id.preview_view)
					.getView();
	      SurfaceHolder surfaceHolder = surfaceView.getHolder();
	      surfaceHolder.removeCallback(this);
	    }
	    super.onPause();
	  }


	@Override
	public void onResume() {
		SurfaceView surfaceView = (SurfaceView) query(R.id.preview_view)
				.getView();
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			// The activity was paused but not stopped, so the surface still
			// exists. Therefore
			// surfaceCreated() won't be called, so init the camera here.
			initCamera(surfaceHolder);
		} else {
			// Install the callback and wait for surfaceCreated() to init the
			// camera.
			surfaceHolder.addCallback(this);
		}
		super.onResume();
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		if (surfaceHolder == null) {
			throw new IllegalStateException("No SurfaceHolder provided");
		}
		if (cameraManager.isOpen()) {
			return;
		}
		try {
			cameraManager.openDriver(surfaceHolder);
			// Creating the handler starts the preview, which can also throw a
			// RuntimeException.
			if (handler == null) {
				handler = new CameraPageHandler(this, decodeFormats,
						decodeHints, characterSet, cameraManager,
						viewfinderView);
			}
			decodeOrStoreSavedBitmap(null, null);
		} catch (IOException ioe) {
			// Log.w(TAG, ioe);
			// displayFrameworkBugMessageAndExit();
		} catch (RuntimeException e) {
			// Barcode Scanner has seen crashes in the wild of this variety:
			// java.?lang.?RuntimeException: Fail to connect to camera service
			// Log.w(TAG, "Unexpected error initializing camera", e);
			// displayFrameworkBugMessageAndExit();
		}
	}

	private void decodeOrStoreSavedBitmap(Bitmap bitmap, Result result) {
		// Bitmap isn't used yet -- will be used soon
		if (handler == null) {
			savedResultToShow = result;
		} else {
			if (result != null) {
				savedResultToShow = result;
			}
			if (savedResultToShow != null) {
				Message message = Message.obtain(handler,
						R.id.decode_succeeded, savedResultToShow);
				handler.sendMessage(message);
			}
			savedResultToShow = null;
		}
	}

	@Override
	protected int onGetLayoutId() {
		return R.layout.camera;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (holder == null) {

		}
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();
	}

	@Produce
	public ScanEvent produceResult(){
		return null;
	}
	
	public void handleDecode(Result code) {
		beepManager.playBeepSoundAndVibrate();
		scanEvent = new ScanEvent(this, code);
		goBack();
	}


	@Override
	public void onDestroy() {
		if (null == scanEvent){
			scanEvent = new ScanEvent(this, null);
		}
		bus().post(scanEvent);
		super.onDestroy();
	}
}
