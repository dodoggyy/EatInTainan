package com.example.luckycookies;


import java.util.Timer;
import java.util.TimerTask;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Preview extends Activity{
	private ImageButton mBtnLoginDlg;
	private int clo = 0;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview);
        mBtnLoginDlg = (ImageButton)findViewById(R.id.imageGreen);
        mBtnLoginDlg.setOnClickListener(registerBtnOKOnClkLis);
        spark();
        
        
    }
	
	private Button.OnClickListener registerBtnOKOnClkLis = new Button.OnClickListener() {
		public void onClick(View v) {
			/*mRegDlg = new Dialog(NFCProjectActivity.this);
			mRegDlg.setTitle("µù¥U");
			mRegDlg.setCancelable(false);
			mRegDlg.setContentView(R.layout.register);
    		Button btnRegOK = (Button)mLoginDlg.findViewById(R.id.btnRegOK);
    		btnRegOK.setOnClickListener(RegDlgBtnOKOnClkLis);
    		mRegDlg.show();*/
			Intent intent = new Intent();
			intent.setClass(Preview.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
    };
	
	public void spark() {
		final TextView touchScreen = (TextView) findViewById(R.id.textHint);
		Timer timer = new Timer();
		TimerTask taskcc = new TimerTask(){

		public void run() {
			runOnUiThread(new Runnable() {
				public void run() {

					if (clo == 0) {
						clo = 1;
						touchScreen.setTextColor(Color.TRANSPARENT); // ³z©ú
					} else {
						if (clo == 1) {
							clo = 2;
							touchScreen.setTextColor(Color.LTGRAY);
						} else {
							clo = 0;
						}
					}
				}
			});
		}
		};

		timer.schedule(taskcc, 1, 700); 

		}
}
