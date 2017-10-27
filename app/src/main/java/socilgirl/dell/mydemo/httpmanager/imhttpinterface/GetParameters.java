package socilgirl.dell.mydemo.httpmanager.imhttpinterface;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;


import java.util.Map;

import socilgirl.dell.mydemo.httpmanager.HttpManagerConstant;
import socilgirl.dell.mydemo.httpmanager.httpinterface.IParameters;

/**
 * Created by ${小强} on 2017/10/26.
 */

public class GetParameters implements IParameters {

    private Builder mBuilder;

    private GetParameters(Builder builder) {
        this.mBuilder = builder;
    }

    @Override
    public String getMethob() {
        return HttpManagerConstant.GET;
    }

    @Override
    public String getUrl() {
        if (TextUtils.isEmpty(mBuilder.mUrl)) {
            throw new NullPointerException("url地址不能为空");
        }
        return mBuilder.mUrl;
    }

    @Override
    public long getWriteTimeOut() {
        return mBuilder.mWriteTimeOut;
    }

    @Override
    public long getReadTimeOut() {
        return mBuilder.mReadTimeOut;
    }

    @Override
    public long getConnectTimeOut() {
        return mBuilder.mConnectTimeOut;
    }

    @Override
    public Map<String, String> getHeaders() {
        return mBuilder.mMapHeaders;
    }

    @Override
    public Map<String, String> getParameters() {
        return mBuilder.mParameters;
    }

    @Override
    public String getJson() {
        return null;
    }

    @Override
    public boolean getSign() {
        return mBuilder.mSign;
    }

    /**
     * 构建者
     */
    public static class Builder {
        private String mUrl;
        private long mWriteTimeOut ;
        private long mReadTimeOut;
        private long mConnectTimeOut;
        private Map<String, String> mMapHeaders;
        private Map<String, String> mParameters;
        private boolean mSign;
        /**
         * 添加url
         *
         * @param url
         * @return
         */
        public Builder setUrl(@NonNull String url) {
            this.mUrl = url;
            return this;
        }

        /**
         * 添加请求写入超时时间
         *
         * @param mWriteTimeOut
         * @return
         */
        public Builder setWriteTimeOut(@NonNull long mWriteTimeOut) {
            this.mWriteTimeOut = mWriteTimeOut;
            return this;
        }

        /**
         * 添加请求读取超时时间
         *
         * @param mReadTimeOut
         * @return
         */
        public Builder setReadTimeOut(@NonNull long mReadTimeOut) {
            this.mReadTimeOut = mReadTimeOut;
            return this;
        }

        /**
         * 添加请求超时时间
         *
         * @param mConnectTimeOut
         * @return
         */
        public Builder setConnectTimeOut(@NonNull long mConnectTimeOut) {
            this.mConnectTimeOut = mConnectTimeOut;
            return this;
        }

        /**
         * 添加请求头
         *
         * @param mapHeaders
         * @return
         */
        public Builder setHeaders(@NonNull Map<String, String> mapHeaders) {
            if (mapHeaders == null || mapHeaders.size() < 1) {
                throw new NullPointerException("请求头map 不能为空");
            }
            this.mMapHeaders = mapHeaders;
            return this;
        }

        /**
         * 添加参数
         *
         * @param mapParameters
         * @return
         */
        public Builder setParameters(@NonNull Map<String, String> mapParameters) {
            if (mapParameters == null || mapParameters.size() < 1) {
                throw new NullPointerException("请求头map 不能为空");
            }
            this.mParameters = mapParameters;
            return this;
        }

        /**
         * 添加参数
         *
         * @param key
         * @param value
         * @return
         */
        public Builder setParameter(@NonNull String key, String value) {
            if (TextUtils.isEmpty(key)) {
                throw new NullPointerException("参数key 不能为空");
            }

            if (this.mParameters == null) {
                this.mParameters = new ArrayMap<>();
            }
            this.mParameters.put(key, value);
            return this;
        }
        /**
         * 设置是否签名
         *
         * @return
         */
        public Builder setSign(boolean isSign) {
            this.mSign = isSign;
            return this;
        }
        /**
         * 构建完成
         *
         * @return
         */
        public GetParameters build() {
            return new GetParameters(this);
        }
    }
}
