package socilgirl.dell.mydemo.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.imagemanger.ImageLoaderOptions;
import socilgirl.dell.mydemo.imagemanger.ImageManager;

/**
 * Created by dell on 2017/10/31.
 */

public class MyRecycleAdapterTwo extends CommonAdapter<String>{
    public MyRecycleAdapterTwo(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv_item_mainrecycle,"hhhh");
        ImageManager.getInstance().showImage(new ImageLoaderOptions.Builder(holder.getView(R.id.iv_main_recycle_item),s)
                .setBlurImage(10)
//                .setCircleCrop(true)
                .setRoundeds(10)
                .build());
    }
}
