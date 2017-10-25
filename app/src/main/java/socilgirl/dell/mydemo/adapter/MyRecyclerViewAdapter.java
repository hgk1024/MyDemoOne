package socilgirl.dell.mydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import socilgirl.dell.mydemo.R;

/**
 * Created by dell on 2017/10/25.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater inflater;
    private List<String> mList;
    public MyRecyclerViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        ;this.mList = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.recycler_main_item,null);
        ViewHolder hodler = new ViewHolder(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size()!=0?mList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
//        @BindView(R.id.tv_item_main_recy)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(mContext,itemView);
            tvName = itemView.findViewById(R.id.tv_item_mainrecycle);
        }
    }
}
