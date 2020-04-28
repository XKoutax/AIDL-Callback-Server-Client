// IService.aidl
package com.asfartz.aidlcallbackserver;

import com.asfartz.aidlcallbackserver.IServiceListener;


interface IService {

    void doSomething();    // method called from Activity
    void registerListener(IServiceListener listener);

}
