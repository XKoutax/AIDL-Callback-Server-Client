package com.asfartz.aidlwithcallback.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.asfartz.aidlwithcallback.IService;
import com.asfartz.aidlwithcallback.IServiceListener;

public class MessageService extends Service {

    private final RemoteCallbackList<IServiceListener> mCallbacks = new RemoteCallbackList<>();
    private IServiceListener serviceListener;

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
                serviceListener = cb;
            }
        }
    };

    public MessageService() {
        Log.d("AIDL service", "MessageService: - constructor");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("AIDL service", "MessageService - onBind");
        return mBinder;
    }


    private void fromActivityProcess() {
        Log.d("AIDL service", "MessageService - fromActivityProcess");

        try {
            // this is very important - if u miss it u ll end in exception
            int N = mCallbacks.beginBroadcast();

            Log.d("AIDL service", "mCallBacks N value = " + N);
            // now for time being we will consider only one activity is bound to the service, so hardcode 0
            // mCallbacks.getBroadcastItem(0).callback();

            mCallbacks.finishBroadcast();

            serviceListener.callback();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
