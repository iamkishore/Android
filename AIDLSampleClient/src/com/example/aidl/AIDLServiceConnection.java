package com.example.aidl;

import com.example.aidl.MyAidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AIDLServiceConnection implements ServiceConnection {
	private static final String AIDL_MESSAGE_SERVICE_CLASS = ".MyAidlService";
	private static final String AIDL_MESSAGE_SERVICE_PACKAGE = "com.example.aidl";
	private static final String APPSOLUT_INTENT_ACTION_BIND_MESSAGE_SERVICE = "com.intent.action.bindAidlService";
	private final static String LOG_TAG = AIDLServiceConnection.class.getCanonicalName();
	private MyAidl service;
	private Context context;
	
	public AIDLServiceConnection(Context context) {
		this.context = context;
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		Log.d(LOG_TAG, "The service is now connected!");
		this.service = MyAidl.Stub.asInterface(service);
		try {
			this.service.getMessage();
		} catch (RemoteException e) {
			Log.e(LOG_TAG, "The service :error : " + e );
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		Log.d(LOG_TAG, "The connection to the service got disconnected unexpectedly!");
		service = null;		
	}

	 /** Method to disconnect the Service.
	 * This method is required because the onServiceDisconnected
	 * is only called when the connection got closed unexpectedly
	 * and not if the user requests to disconnect the service.
	 */
	public void safelyDisconnectTheService() {
		if(service != null) {
			service = null;
			context.unbindService(this);
			Log.d(LOG_TAG, "The connection to the service was closed.!");
		}
		Log.d(LOG_TAG, "No service is connected.!");
	}

	/**
	 * Method to connect the Service.
	 */
	public void safelyConnectTheService() {
		if(service == null) {
			Intent bindIntent = new Intent(APPSOLUT_INTENT_ACTION_BIND_MESSAGE_SERVICE);
			bindIntent.setClassName(AIDL_MESSAGE_SERVICE_PACKAGE, AIDL_MESSAGE_SERVICE_PACKAGE + AIDL_MESSAGE_SERVICE_CLASS);
			context.bindService(bindIntent, this, Context.BIND_AUTO_CREATE);
			Log.d(LOG_TAG, "The Service will be connected soon (asynchronus call)!");
		}
	}
}
