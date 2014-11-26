package cn.com.sgmsc.ServBroad;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Service_Broad_Demo extends Activity {
	Button button1;//������ť������
	Button button2;//������ť������
	TextView myTextView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myTextView = (TextView) this.findViewById(R.id.myTextView);
        button1 = (Button) this.findViewById(R.id.myButton1);
        button2 = (Button) this.findViewById(R.id.myButton2);
        
        //Click"����Service"��ť
        button1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Service_Broad_Demo.this, MyService.class);
				startService(i);
				Toast.makeText(Service_Broad_Demo.this,
						       "Service�����ɹ�", Toast.LENGTH_SHORT).show();
			}
        });
        //Click"ֹͣService"��ť
        button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Service_Broad_Demo.this, MyService.class);
				stopService(i);
				Toast.makeText(Service_Broad_Demo.this,
						       "Serviceֹͣ�ɹ�", Toast.LENGTH_SHORT).show();
			}
        });  
        
        //��̬ע��һ��BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter("cn.com.sgmsc.ServBroad.myThread"); 
                                                                              //����������
        MyBroadcasReceiver myBroadcasReceiver = new MyBroadcasReceiver();
        registerReceiver(myBroadcasReceiver, intentFilter);    //ע��BroadcastReceiver����
    }
    
    //ʵ��MyBroadcasReceiver
    public class MyBroadcasReceiver extends BroadcastReceiver{
    	@Override
    	public void onReceive(Context context, Intent intent) {
    		Bundle myBundle = intent.getExtras();
    		int myInt = myBundle.getInt("myThread");
    		if(myInt < 60){                                    //��̨Service����������
    			myTextView.setText("��̨Service������"+myInt+"��");
    		}else{
    			Intent i = new Intent(Service_Broad_Demo.this, MyService.class);
    			stopService(i);
    			myTextView.setText("��̨Service��"+myInt+"���ֹͣ����");
    		}
    	}   
    }
}