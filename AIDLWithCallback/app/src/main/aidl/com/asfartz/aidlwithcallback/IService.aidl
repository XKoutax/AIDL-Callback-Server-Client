// IService.aidl
package com.asfartz.aidlwithcallback;
import com.asfartz.aidlwithcallback.IServiceListener;


interface IService {

    void doSomething();    // method called from Activity
    void registerListener(IServiceListener listener);

}
