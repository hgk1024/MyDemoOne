package socilgirl.dell.mydemo.imagemanger;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.DataSource;

/**
 * 作者：Created by ${小强} on 2017/8/25.
 * 邮箱：980766134@qq.com
 */

public interface ImageLoadListener {
    /**
     * 加载成功
     *
     * @param resource        成功转成的Drawble 对象
     * @param dataSource      数据源
     * @param isFirstResource 是否是第一资源 网络获取
     */
    boolean onSuccess(Drawable resource, DataSource dataSource, boolean isFirstResource);

    /**
     * 加载失败
     *
     * @param e               异常信息
     * @param isFirstResource 是否是第一资源 （从网络获取）
     */
    boolean onError(Exception e, boolean isFirstResource);
}
