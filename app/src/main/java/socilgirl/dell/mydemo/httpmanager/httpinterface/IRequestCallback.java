package socilgirl.dell.mydemo.httpmanager.httpinterface;

import java.lang.reflect.Type;

/**
 * Created by ${小强} on 2017/10/26.
 */

public interface IRequestCallback<T> {
    void onStart();

    void onSuccess(T t);

    void onError(int code, String msg, Exception e);

    Type getGenericityType();

}
