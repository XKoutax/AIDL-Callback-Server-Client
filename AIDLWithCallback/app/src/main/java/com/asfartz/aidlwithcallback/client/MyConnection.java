package com.asfartz.aidlwithcallback.client;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.asfartz.aidlwithcallback.IService;
import com.asfartz.aidlwithcallback.IServiceListener;

class MyConnection implements ServiceConnection {

    private IService remoteService;
    private ExampleActivity ea;

    private IServiceListener mListeners = new IServiceListener.Stub() {
        @Override
        public void callback() throws RemoteException {
            Log.d("AIDL service", "MyConnection - callback");
            someMethodInActivity();
        }
    };

    public MyConnection(ExampleActivity ea) {
        this.ea = ea;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d("AIDL service", "MyConnection - OnServiceConnected, adding callback to callback list");
        remoteService = IService.Stub.asInterface(service);

        try {
            remoteService.registerListener(mListeners);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }

        try {
            Log.d("Sample Activity", "MyConnection - Before calling fromActivity, trigger by onServiceConnected from OtherActiivty");
            remoteService.doSomething();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void someMethodInActivity() {
        ea.showMessage("method called from callback");
    }

    public IService getRemoteService() {
        return remoteService;
    }
}
