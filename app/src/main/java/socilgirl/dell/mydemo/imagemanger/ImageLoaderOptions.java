package socilgirl.dell.mydemo.imagemanger;

import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.bumptech.glide.request.target.BaseTarget;

import java.io.File;

/**
 * Created by Administrator on 2017/3/20 0020.
 */
public class ImageLoaderOptions {
    // All, NONE, ,DATA,RESOURCE, RESULT, DEFAULT
    public static final int All = 1;//使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
    public static final int NONE = 2;//不使用磁盘缓存
    public static final int DATA = 3;//在资源解码前就将原始数据写入磁盘缓存
    public static final int RESOURCE = 4;//在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
    public static final int AUTOMATIC = 5;//根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。


    private File mFile;//图片地址

    public File getFile() {
        return mFile;
    }

    private View viewContainer;  // 图片容器
    private String url;  // 图片地址
    private Uri mUri;//图片地址
    private Integer resource;  // 图片地址
    private int holderDrawable;  // 设置展位图
    private ImageSize imageSize;  //设置图片的大小
    private int errorDrawable;  //是否展示加载错误的图片
    private boolean isCrossFade = true; //是否渐变平滑的显示图片,默认为true
    private boolean isSkipMemoryCache = false; //是否跳过内存缓存
    private int mDiskCacheStrategy = AUTOMATIC; //磁盘缓存策略
    private int blurImage = 0; //是否使用高斯模糊
    private boolean circleCrop = false;//圆形
    private int mRoundeds = 0;//圆角 默认0
    private BaseTarget target = null; //target


    private ImageLoadListener mImageLoadListener;//加载监听


    private ImageLoaderOptions(Builder builder) {
        this.errorDrawable = builder.errorDrawable;
        this.holderDrawable = builder.holderDrawable;
        this.imageSize = builder.mImageSize;
        this.isCrossFade = builder.isCrossFade;
        this.isSkipMemoryCache = builder.isSkipMemoryCache;
        this.mDiskCacheStrategy = builder.mDiskCacheStrategy;
        this.url = builder.url;
        this.mUri = builder.mUri;
        this.resource = builder.resource;
        this.viewContainer = builder.mViewContainer;
        this.blurImage = builder.blurImage;
        this.target = builder.target;
        this.circleCrop = builder.circleCrop;
        this.mRoundeds = builder.mRoundeds;
        this.mFile = builder.mfile;
        this.mImageLoadListener = builder.mImageLoadListener;
    }

    public int getBlurImage() {
        return blurImage;
    }

    public Uri getUri() {
        return mUri;
    }

    public boolean isCircleCrop() {
        return circleCrop;
    }


    public void setUri(Uri uri) {
        mUri = uri;
    }

    public int getRoundeds() {
        return mRoundeds;
    }

    public BaseTarget getTarget() {
        return target;
    }

    public Integer getResource() {
        return resource;
    }


    public View getViewContainer() {
        return viewContainer;
    }

    public String getUrl() {
        return url;
    }


    public int getHolderDrawable() {
        return holderDrawable;
    }


    public ImageSize getImageSize() {
        return imageSize;
    }


    public int getErrorDrawable() {
        return errorDrawable;
    }


    public boolean isCrossFade() {
        return isCrossFade;
    }


    public boolean isSkipMemoryCache() {
        return isSkipMemoryCache;
    }


    public int getDiskCacheStrategy() {
        return mDiskCacheStrategy;
    }

    public ImageLoadListener getImageLoadListener() {
        return mImageLoadListener;
    }

    public final static class Builder {
        private File mfile;//图片地址
        private Uri mUri;//图片地址
        private int holderDrawable = -1;  // 设置位图
        private View mViewContainer;  // 图片容器
        private String url;  // 图片地址
        private Integer resource;  // 图片地址id
        private ImageSize mImageSize;  //设置图片的大小
        private int errorDrawable = -1;  //是否展示加载错误的图片
        private boolean isCrossFade = true; //是否渐变平滑的显示图片
        private boolean isSkipMemoryCache = false; //是否跳过内存缓存
        private int blurImage = 0; //是否使用高斯模糊
        private int mDiskCacheStrategy = AUTOMATIC; //磁盘缓存策略
        private boolean circleCrop = false;//圆形
        private int mRoundeds = 0;//圆角 默认0
        private BaseTarget target = null; //target
        private ImageLoadListener mImageLoadListener;

        public Builder(@NonNull View v, @NonNull String url) {
            this.url = url;
            this.mViewContainer = v;
        }

        public Builder(@NonNull View v, @NonNull Integer resource) {
            this.resource = resource;
            this.mViewContainer = v;
        }

        public Builder(@NonNull View v, @NonNull Uri muri) {
            this.mUri = muri;
            this.mViewContainer = v;
        }

        public Builder(@NonNull View v, @NonNull File file) {
            this.mfile = file;
            this.mViewContainer = v;
        }

        public Builder setCircleCrop(boolean isCircleCrop) {
            this.circleCrop = isCircleCrop;
            this.mRoundeds = 0;
            return this;
        }

        public Builder setRoundeds(int mRoundeds) {
            this.circleCrop = false;
            this.mRoundeds = mRoundeds;
            return this;
        }

        public Builder setPlaceholder(@DrawableRes int holderDrawable) {
            this.holderDrawable = holderDrawable;
            return this;
        }

        public Builder setCrossFade(boolean isCrossFade) {
            this.isCrossFade = isCrossFade;
            return this;
        }

        public Builder setBlurImage(int blurImage) {
            this.blurImage = blurImage;
            return this;
        }

        public Builder setSkipMemoryCache(boolean isSkipMemoryCache) {
            this.isSkipMemoryCache = isSkipMemoryCache;
            return this;

        }

        public Builder setOverride(int width, int height) {
            this.mImageSize = new ImageSize(width, height);
            return this;
        }


        public Builder setListener(ImageLoadListener listener) {
            this.mImageLoadListener = listener;
            return this;
        }

        public Builder setError(@DrawableRes int errorDrawable) {
            this.errorDrawable = errorDrawable;
            return this;
        }

        public Builder setTarget(BaseTarget target) {
            this.target = target;
            return this;
        }

        public Builder setDiskCacheStates(int mDiskCacheStrategy) {
            this.mDiskCacheStrategy = mDiskCacheStrategy;
            return this;

        }

        public ImageLoaderOptions build() {
            return new ImageLoaderOptions(this);
        }


    }

    //对应重写图片size
    public final static class ImageSize {
        private int width = 0;
        private int height = 0;

        public ImageSize(int width, int heigh) {
            this.width = width;
            this.height = heigh;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }
    }

}
