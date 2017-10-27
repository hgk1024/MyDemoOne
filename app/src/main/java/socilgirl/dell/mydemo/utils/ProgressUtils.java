package socilgirl.dell.mydemo.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.zhouyou.http.callback.ProgressDialogCallBack;

import socilgirl.dell.mydemo.R;

/**
 * Created by dell on 2017/10/25.
 */

public class ProgressUtils {

    public ProgressUtils() {
    }
    public void showDialog(Context context,String msg1,String msg2){
        showDialog(context,msg1,msg2,null);
    }
    public void showDialog(Context context,String msg1, String msg2, String msg3){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_base_layout,null);

    }
    public void showProgressDialog(Context context,String msg1){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(msg1);

    }
}
