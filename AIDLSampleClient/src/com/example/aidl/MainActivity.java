package com.example.aidl;

import com.example.aidlclient.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button connect;
	private Button disConnect;
	AIDLServiceConnection serviceConnection;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		connect = (Button) findViewById(R.id.button1);
		disConnect = (Button) findViewById(R.id.button2);
		
		connect.setOnClickListener(this);
		disConnect.setOnClickListener(this);
		
		serviceConnection = new AIDLServiceConnection(this);
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			serviceConnection.safelyConnectTheService();
			break;
		case R.id.button2:
			serviceConnection.safelyDisconnectTheService();
			break;
		default:
			break;
		}
	}
}
