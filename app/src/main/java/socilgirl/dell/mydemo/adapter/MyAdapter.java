package socilgirl.dell.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import socilgirl.dell.mydemo.R;

/**
 * Create by "hou" on 2017/11/1.
 */

public class MyAdapter extends BaseAdapter{
    private List<String> mList;
    private Context mContext;
private LayoutInflater inflater;
    public MyAdapter(List<String> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size() > 0 ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.recycler_main_item,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (mList != null && mList.size() > 0){
            viewHolder.textView.setText(mList.get(position));
        }
        return convertView;
    }

    public class ViewHolder{
        private TextView textView;
        private ImageView imageView;
        private View view;
        public ViewHolder(View view) {
            this.view = view;
            textView = view.findViewById(R.id.tv_item_mainrecycle);
            imageView = view.findViewById(R.id.iv_main_recycle_item);
        }


    }
}
