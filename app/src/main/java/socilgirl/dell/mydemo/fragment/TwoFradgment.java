package socilgirl.dell.mydemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.model.DataBeanTwo;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFradgment extends Fragment {

    private Button btnTwo;
    private TextView tvHellow;
    public static TwoFradgment newInstance(){
        // Required empty public constructor
        Bundle bundle = new Bundle();
        TwoFradgment fradgment = new TwoFradgment();
        fradgment.setArguments(bundle);
        return fradgment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two_post, container, false);
        initView(view);
        return view;
    }

    private void initView(final View view) {
        btnTwo = view.findViewById(R.id.btn_fragment_two);
        tvHellow = view.findViewById(R.id.tv_frag_two);
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DataBeanTwo("王万",99));
                Toast.makeText(view.getContext(), "我是新添加的一个fragment", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
    }

}
