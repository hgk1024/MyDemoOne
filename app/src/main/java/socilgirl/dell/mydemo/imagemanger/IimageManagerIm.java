package socilgirl.dell.mydemo.imagemanger;

import android.content.Context;
import android.support.annotation.NonNull;


/**
 * Created by Administrator on 2017/3/20 0020.
 */

public interface IimageManagerIm {
    void showImage(@NonNull ImageLoaderOptions options) throws Exception;

    void cleanMemory(Context context);

    // 在application的oncreate中初始化
    void init(Context context);

    void init(Context context, ImageCookieUtils imageCookieUtils);
}
