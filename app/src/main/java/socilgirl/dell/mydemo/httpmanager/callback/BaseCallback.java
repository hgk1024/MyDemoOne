package socilgirl.dell.mydemo.httpmanager.callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by ${小强} on 2017/10/26.
 */

public abstract class BaseCallback<T> implements IRequestCallback<T> {
    private Type genericityType;

    public BaseCallback() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            this.genericityType = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        } else {
            this.genericityType = Object.class;
        }
    }

    @Override
    public Type getGenericityType() {
        return genericityType;
    }

    @Override
    public void onProgress(int progress) {

    }
}
