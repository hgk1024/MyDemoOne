package socilgirl.dell.mydemo.httpmanager;


import io.reactivex.disposables.Disposable;
import socilgirl.dell.mydemo.httpmanager.httpinterface.IRequestTask;

/**
 * Created by ${小强} on 2017/10/26.
 */

public class RequestTast implements IRequestTask {

    private Disposable mDisposable;

    public RequestTast(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void clearTask() {
        if(mDisposable != null){
            if(!mDisposable.isDisposed()){
                mDisposable.dispose();
            }
        }

    }
}
