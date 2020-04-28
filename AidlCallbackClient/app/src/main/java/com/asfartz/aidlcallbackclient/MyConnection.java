package com.asfartz.aidlcallbackclient;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.asfartz.aidlcallbackserver.IService;
import com.asfartz.aidlcallbackserver.IServiceListener;

public class MyConnection implements ServiceConnection {

    private static final String TAG = "AIDL service client";

    private MainActivity mainActivity;
    private IService mService;
    private IServiceListener mListener = new IServiceListener.Stub() {
        @Override
        public void callback() throws RemoteException {
            Log.d(TAG, "MyConnection - callback");
            onServiceFinished();
        }
    };

    public MyConnection(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d(TAG, "MyConnection - OnServiceConnected, adding callback to callback list");
        mService = IService.Stub.asInterface(service);

        try {
            Log.d(TAG, "onServiceConnected: adding Listener");
            mService.registerListener(mListener);

            Log.d(TAG, "onServiceConnected: using Service");
            mService.doSomething();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    private void onServiceFinished() {
        mainActivity.showMessage("method called from callback");
    }
}
