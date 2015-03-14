package com.example.aidl;

import com.example.aidl.MyAidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyAidlService extends Service {
	private static final String TAG = MyAidlService.class.getSimpleName();
	private static final String APPSOLUT_INTENT_ACTION_BIND_MESSAGE_SERVICE = "com.intent.action.bindAidlService";
	
	@Override
	public IBinder onBind(Intent intent) {
		if(APPSOLUT_INTENT_ACTION_BIND_MESSAGE_SERVICE.equals(intent.getAction())) {
			Log.d(TAG,"The AIDLParcelableMessageService was binded.");
			return new AidlBinder(this);
		}
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG,"The MyAidlService was created.");
	}

	@Override
	public void onDestroy() {
		Log.d(TAG,"The MyAidlService was destroyed.");
		super.onDestroy();
	}
	
	private class AidlBinder extends MyAidl.Stub {
		
		private MyAidlService aidlService;
		
		public AidlBinder(MyAidlService myAidlService) {
			Log.d(TAG,"The AidlBinder was destroyed.");
			this.aidlService = myAidlService;
		}

		@Override
		public String getMessage() throws RemoteException {
			Log.d(TAG,"The AidlBinder  getMessage was destroyed.");
			return aidlService.getMessage();
		}
		
	}
	
	private String getMessage() {
		Log.d(TAG,"The MyAidlService  getMessage was destroyed.");
		return "Message from my aidl server";
	}

}
