package socilgirl.dell.mydemo.imagemanger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;


/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class ImageManager implements IimageManagerIm {
    private static final ImageManager INSTANCE = new ImageManager();
    private IimageManagerIm mIimageManagerIm;

    private ImageManager() {
    }

    public static ImageManager getInstance() {
        return INSTANCE;
    }

    public void setImageManagetIm(IimageManagerIm managetIm) {
        mIimageManagerIm = managetIm;
    }

    /*
     *   可创建默认的Options设置，假如不需要使用ImageView ，
     *    请自行new一个Imageview传入即可
     *  内部只需要获取Context
     */
    public static ImageLoaderOptions getDefaultOptions(@NonNull View container, @NonNull String url) {
        return new ImageLoaderOptions.Builder(container, url).setCrossFade(true).build();
    }

    @Override
    public void showImage(@NonNull ImageLoaderOptions options) {

        if (mIimageManagerIm != null) {
            try {
                mIimageManagerIm.showImage(options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            throw new NullPointerException("沒有初始化");
        }
    }


    @Override
    public void cleanMemory(Context context) {
        mIimageManagerIm.cleanMemory(context);
    }

    // 在application的oncreate中初始化
    @Override
    public void init(Context context) {
        mIimageManagerIm = new GlideImageLoader();//可以改为其他图片加载工具
        mIimageManagerIm.init(context);
    }

    @Override
    public void init(Context context, ImageCookieUtils imageCookieUtils) {
        mIimageManagerIm = new GlideImageLoader();
        mIimageManagerIm.init(context, imageCookieUtils);
    }

}
