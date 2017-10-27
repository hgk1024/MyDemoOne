package socilgirl.dell.mydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import socilgirl.dell.mydemo.R;

/**
 * Created by dell on 2017/10/27.
 */

public class SecondViewAdapter extends RecyclerView.Adapter<SecondViewAdapter.HolderView> {
    private Context mContext;
    private LayoutInflater inflater;
    private List<String> mList;
    private  MyItemClickListener mItemClickListener;
    public SecondViewAdapter(Context mContext,List<String> list) {
        this.mContext = mContext;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.iitem_text,parent,false);

        return new HolderView(view,mItemClickListener);
    }

    @Override
    public void onBindViewHolder(HolderView holder, int position) {
//        ImageManager.getInstance()
//                .showImage(new ImageLoaderOptions.Builder(holder.imageView,mList.get(position)).build());
        holder.textView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size() != 0 ? mList.size() : 0;
    }

    public static class HolderView extends RecyclerView.ViewHolder implements View.OnClickListener {

        //声明MyItemClickListener
        private MyItemClickListener mListener;
        TextView textView;
        public HolderView(View itemView,MyItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerview_item_text);
            this.mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //如果mListener不为空，就实现接口中的方法onItemClick其中getPosition()是得到被点击位置的position
            if(mListener != null){
                mListener.onClickItem(v,getPosition());
            }
        }
    }
    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnClickItemListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
