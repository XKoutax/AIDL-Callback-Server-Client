package com.asfartz.aidlcallbackserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MessageService extends Service {

    final RemoteCallbackList<IServiceListener> mCallbacks = new RemoteCallbackList<>();

    private final IService.Stub mBinder = new IService.Stub() {
        @Override
        public void doSomething() throws RemoteException {
            Log.d("AIDL service", "MessageService - fromActivity method called from MyConnection called from ExampleActivity");
            fromActivityProcess();
        }

        @Override
        public void registerListener(IServiceListener cb) throws RemoteException {
            if (cb != null) {
                Log.d("AIDL service", "MessageService - registerCallBack");
                mCallbacks.register(cb);
            }
        }
    };

    public MessageService() {
        Log.d("AIDL service", "MessageService - constructor");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("AIDL service", "MessageService - onBind");
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        return mBinder;
    }

    private void fromActivityProcess() {
        Log.d("AIDL service", "MessageService - fromActivityProcess");

        try {
            // this is very important - if u miss it you will end in exception
            int N = mCallbacks.beginBroadcast();

            Log.d("AIDL service", "mCallBacks N value = " + N);
            // now for time being we will consider only one activity is bound to the service, so hardcode 0
            mCallbacks.getBroadcastItem(0).callback();
            mCallbacks.finishBroadcast();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
