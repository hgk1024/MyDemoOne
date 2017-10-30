package socilgirl.dell.mydemo.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

import socilgirl.dell.mydemo.model.ChatMessage;

/**
 * Created by dell on 2017/10/30.
 */

public class RecyclerviewChatAdapter extends MultiItemTypeAdapter<ChatMessage>{

    public RecyclerviewChatAdapter(Context context, List<ChatMessage> datas) {
        super(context, datas);

        addItemViewDelegate(new ItemSentTypeRecycle());
        addItemViewDelegate(new ItemFromTypeRecycle());
    }

}
