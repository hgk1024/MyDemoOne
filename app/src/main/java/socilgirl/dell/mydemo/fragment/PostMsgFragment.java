package socilgirl.dell.mydemo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.model.DataBeanTwo;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostMsgFragment extends Fragment {
    private static final String TAG = "PostMsgFragment";
private Button btnPost;
    public static PostMsgFragment newInstance(String str) {
        // Required empty public constructor
        Bundle bundle = new Bundle();
        bundle.putString("nihao",str);
        PostMsgFragment fragmet = new PostMsgFragment();
        fragmet.setArguments(bundle);
        return fragmet;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_msg, container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btnPost = view.findViewById(R.id.btn_post_msg);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DataBeanTwo("至深",35));
                Log.d(TAG, "fragment发送了一条EventBus消息");
                getActivity().finish();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
