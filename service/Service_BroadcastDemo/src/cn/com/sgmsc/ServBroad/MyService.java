package cn.com.sgmsc.ServBroad;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service{
	MyThread myThread;//�̵߳�����
	@Override
	public IBinder onBind(Intent intent) {//��д��onBind����
		return null;
	}
	@Override
	public void onDestroy() {   //��д��onDestroy����
		myThread.flag = false;  //ֹͣ�߳�����
		super.onDestroy();
	}
	@Override
	public void onStart(Intent intent, int startId) {//��дonStart����
		myThread = new MyThread() ;  //��ʼ���߳�
		myThread.start();            //�����߳�
		super.onStart(intent, startId);
	}	
	
	//�����߳���
	class MyThread extends Thread{  
		boolean flag = true;        //ѭ����־λ
		int c = 0;                  //��ֵΪ���͵���Ϣ
		@Override
		public void run() {
			while(flag){
				Intent i = new Intent("cn.com.sgmsc.ServBroad.myThread");//����Intent
				i.putExtra("myThread", c);      //��������
				sendBroadcast(i);               //���͹㲥
				c++;
				try{
					Thread.sleep(1000);        //˯��ָ��������
				}catch(Exception e){           //�����쳣
					e.printStackTrace();       //��ӡ�쳣
				}
			}
		}
	};
}
