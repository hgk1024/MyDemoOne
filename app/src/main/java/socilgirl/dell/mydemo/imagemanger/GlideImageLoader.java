package socilgirl.dell.mydemo.imagemanger;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import socilgirl.dell.mydemo.imagemanger.transform.BlurTransformation;


/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class GlideImageLoader implements IimageManagerIm {
    private Context mContext;
    private ImageCookieUtils mImageCookieUtils;

    @Override
    public void showImage(final ImageLoaderOptions options) {

        GlideRequests manager;
        if (options.getViewContainer() != null && options.getViewContainer().getContext() != null) {
            manager = GlideApp.with(options.getViewContainer().getContext());
        } else {
            manager = GlideApp.with(mContext);
        }

        GlideRequest<Drawable> load;
        if (options.getResource() != null) {
            load = manager.load(options.getResource());
        } else if (options.getUri() != null) {
            load = manager.load(options.getUri());
        } else if (options.getUrl() != null) {
            load = manager.load(getGlideUrl(options.getUrl()));
        } else if (options.getFile() != null) {
            load = manager.load(options.getFile());
        } else {
            throw new NullPointerException("加载图片的地址找不到！！");
        }


        if (options.getHolderDrawable() != -1) {//设置占位图
            load.placeholder(options.getHolderDrawable());
        }
        if (options.getImageSize() != null) {//设置图片大小
            load.override(options.getImageSize().getWidth(), options.getImageSize().getHeight());
        }
        if (options.getErrorDrawable() != -1) {//设置错误图片
            load.error(options.getErrorDrawable());
        }
        if (options.getImageLoadListener() != null) {
            load.listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                            Target<Drawable> target, boolean isFirstResource) {
                    return options.getImageLoadListener().onError(e, isFirstResource);
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model,
                                               Target<Drawable> target, DataSource dataSource,
                                               boolean isFirstResource) {
                    return options.getImageLoadListener().onSuccess(resource, dataSource, isFirstResource);
                }
            });
        }


        load.skipMemoryCache(options.isSkipMemoryCache());//设置跳过内存缓存
        if (!options.isCrossFade()) {//设置不使用动画
            load.dontAnimate();
        }
        switch (options.getDiskCacheStrategy()) {
            case ImageLoaderOptions.All:
                load.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case ImageLoaderOptions.NONE:
                load.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case ImageLoaderOptions.DATA:
                load.diskCacheStrategy(DiskCacheStrategy.DATA);
                break;
            case ImageLoaderOptions.RESOURCE:
                load.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                break;
            case ImageLoaderOptions.AUTOMATIC:
                load.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                break;
        }

        boolean Round = false;
        boolean Circle = false;
        boolean Blur = false;
        if (options.isCircleCrop()) {//是否圆形
            Circle = true;
        }
        if (options.getRoundeds() != 0) {//是否圆角
            Round = true;
        }
        if(options.getBlurImage() != 0){
            Blur = true;
        }

        if(Round && Blur){
            load.transforms(new BlurTransformation(options.getBlurImage()),new RoundedCorners(options.getRoundeds()));
        }else if(Circle && Blur){
            load.transforms(new BlurTransformation(options.getBlurImage()),new CircleCrop());
        }else if(Blur){
            load.transforms(new BlurTransformation(options.getBlurImage()));
        }else if(Round){
            load.transforms(new RoundedCorners(options.getRoundeds()));
        }else if(Circle){
            load.transforms(new CircleCrop());
        }


        if (options.getTarget() != null) {
            load.into(options.getTarget());
            return;
        }

        if (options.getViewContainer() instanceof ImageView) {
            load.into((ImageView) options.getViewContainer());
        } else {
            throw new NullPointerException("加载图片的imageview找不到！！");
        }
    }

    /**
     * 是否添加cookie
     *
     * @param url
     */
    private GlideUrl getGlideUrl(String url) {
        GlideUrl mGlideUrl = new GlideUrl(url);
        if (mImageCookieUtils != null) {
            List<String> cookies = mImageCookieUtils.getCookies(url);
            if (cookies != null && !cookies.isEmpty()) {
                LazyHeaders.Builder builder = new LazyHeaders.Builder();
                for (String stringh : cookies) {
                    builder.addHeader("Cookie", stringh);
                }
                mGlideUrl = new GlideUrl(url, builder.build());
            }
        }
        return mGlideUrl;
    }

    @Override
    public void cleanMemory(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            GlideApp.get(context).clearMemory();
        }
        new AsyncTask<Context,Void,Boolean>(){

            @Override
            protected Boolean doInBackground(Context... params) {
                GlideApp.get(params[0]).clearDiskCache();
                return true;
            }

        }.execute();

    }

    @Override
    public void init(Context context) {
        mContext = context;
    }

    @Override
    public void init(Context context, ImageCookieUtils imageCookieUtils) {
        mContext = context;
        mImageCookieUtils = imageCookieUtils;
    }


}
