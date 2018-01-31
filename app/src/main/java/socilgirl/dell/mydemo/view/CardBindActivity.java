package socilgirl.dell.mydemo.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.OcrRequestParams;
import com.baidu.ocr.sdk.model.OcrResponseResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.zhouyou.view.seekbar.SignSeekBar;

import java.io.File;
import java.util.Locale;

import socilgirl.dell.mydemo.R;
import socilgirl.dell.mydemo.service.RecognizeService;
import socilgirl.dell.mydemo.utils.FileUtils;
import socilgirl.dell.mydemo.view.IDCardActivity;
import socilgirl.dell.mydemo.weight.DialogStyle;

public class CardBindActivity extends Activity {

    private static final String TAG = "CardBindActivity";
    private boolean hasGotToken = false;
        private AlertDialog.Builder alertDialog;
    private DialogStyle dialogStyle;
    private static final int REQUEST_CODE_GENERAL = 105;
    private static final int REQUEST_CODE_GENERAL_BASIC = 106;
    private static final int REQUEST_CODE_ACCURATE_BASIC = 107;
    private static final int REQUEST_CODE_ACCURATE = 108;
    private static final int REQUEST_CODE_GENERAL_ENHANCED = 109;
    private static final int REQUEST_CODE_GENERAL_WEBIMAGE = 110;
    private static final int REQUEST_CODE_BANKCARD = 111;
    private static final int REQUEST_CODE_VEHICLE_LICENSE = 120;
    private static final int REQUEST_CODE_DRIVING_LICENSE = 121;
    private static final int REQUEST_CODE_LICENSE_PLATE = 122;
    private static final int REQUEST_CODE_BUSINESS_LICENSE = 123;
    private static final int REQUEST_CODE_RECEIPT = 124;

    private SignSeekBar seekBar;//CSDN地址：http://blog.csdn.net/zhouy478319399/article/details/78298104?readlog
    private TextView tv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_bind);
        alertDialog = new AlertDialog.Builder(this);
        dialogStyle = new DialogStyle(this);
        seekBar = findViewById(R.id.seek_bar);
        tv5 = findViewById(R.id.tv5);
//        initAccessToken();
        initAccessTokenWithAkSk();
        initView();
        setSeekBarEvent();
    }

    private void setSeekBarEvent() {
        seekBar.getConfigBuilder().min(0).max(4)
                .progress(2)
                .sectionCount(4)
                .trackColor(ContextCompat.getColor(this, R.color.grgray))
                .secondTrackColor(ContextCompat.getColor(this, R.color.possible_result_points))
                .thumbColor(ContextCompat.getColor(this, R.color.possible_result_points))
                .sectionTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .sectionTextSize(16)
                .thumbTextColor(ContextCompat.getColor(this, R.color.bg_color))
                .thumbTextSize(18)
                .signColor(ContextCompat.getColor(this, R.color.sb__background))
                .signTextSize(18)
                .autoAdjustSectionMark()
                .sectionTextPosition(SignSeekBar.TextPosition.BELOW_SECTION_MARK)
                .build();
        seekBar.setOnProgressChangedListener(new SignSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
                String s = String.format(Locale.CHINA, "onChanged int:%d, float:%.1f", progress, progressFloat);
                tv5.setText(s);
            }

            @Override
            public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {
                String s = String.format(Locale.CHINA, "onActionUp int:%d, float:%.1f", progress, progressFloat);
                tv5.setText(s);
            }

            @Override
            public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
                String s = String.format(Locale.CHINA, "onFinally int:%d, float:%.1f", progress, progressFloat);
                tv5.setText(s + getResources().getStringArray(R.array.labels)[progress]);

            }
        });
        tv5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                seekBar.setProgress(3f);
                return false;
            }
        });
    }

    private void initView() {
        //驾驶证识别
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkTokenStatus()){
                    return;
                }
                Intent intent = new Intent(CardBindActivity.this,CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,CameraActivity.CONTENT_TYPE_GENERAL);
                startActivityForResult(intent,REQUEST_CODE_DRIVING_LICENSE);
            }
        });
        //身份证
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!checkTokenStatus()){
                    return;
                }
                Intent intent = new Intent(CardBindActivity.this,IDCardActivity.class);
                startActivity(intent);
            }
        });
        //银行卡
        findViewById(R.id.tv4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkTokenStatus()){
                    return;
                }
                Intent intent = new Intent(CardBindActivity.this,CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,CameraActivity.CONTENT_TYPE_BANK_CARD);
                startActivityForResult(intent,REQUEST_CODE_BANKCARD);
            }
        });
    }

    //检查是否拿到TOKEN值
    private boolean checkTokenStatus() {
        if (!hasGotToken){
            Toast.makeText(this, "token值还没有获取到", Toast.LENGTH_SHORT).show();
        }
        return hasGotToken;
    }

    //获取token值
    private void initAccessToken() {
        OCR.getInstance().initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
//                alertText("licence方式获取token失败", error.getMessage());
            }
        }, getApplicationContext());
    }
    //获取token值
    private void initAccessTokenWithAkSk() {
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
//                alertText("",result.getAccessToken());
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
//                alertText("AK，SK方式获取token失败", error.getMessage());
                dialogStyle.setType(1);
                dialogStyle.setTitle("错误信息");
                dialogStyle.setMessage("AK，SK方式获取token失败 "+error.getMessage());
                dialogStyle.show();
            }
        }, getApplicationContext(), "TV4bp0s0LUmhCHsl86gppAyK", "ggV53qs1Ft5lxmWXdXRXSnTTCY321ecU");
    }

    //提示弹窗
    private void alertText(final String title, final String message) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("确定", null)
                        .show();
            }
        });
    }


    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //文字识别回调
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE_DRIVING_LICENSE:
//                    RecognizeService.recDrivingLicense(FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath()
//                            , new RecognizeService.ServiceListener() {
//                                @Override
//                                public void onResult(String result) {
//                                    //显示结果
//                                    alertText("驾驶证信息",result);
//                                }
//                            });
                    // 驾驶证识别参数设置
                    OcrRequestParams param2 = new OcrRequestParams();

                    // 设置image参数
                    param2.setImageFile(new File(FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath()));
                    // 设置其他参数
                    param2.putParam("detect_direction", true);
                    // 调用驾驶证识别服务
                    OCR.getInstance().recognizeDrivingLicense(param2, new OnResultListener<OcrResponseResult>() {
                        @Override
                        public void onResult(OcrResponseResult result) {
                            // 调用成功，返回OcrResponseResult对象

                            dialogStyle.setType(1);
                            dialogStyle.setMessage(result.getJsonRes());
                            dialogStyle.show();
                        }

                        @Override
                        public void onError(OCRError error) {
                            // 调用失败，返回OCRError对象
                        }
                    });

                    break;
                case REQUEST_CODE_BANKCARD:
                    RecognizeService.recBankCard(FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath(),
                            new RecognizeService.ServiceListener() {
                                @Override
                                public void onResult(String result) {
                                    alertText("银行卡信息",result);
                                }
                            });

//                    BankCardParams param = new BankCardParams();
//                    param.setImageFile(new File(FileUtils.getSaveFile(getApplicationContext()).getAbsolutePath()));
//                    OCR.getInstance().recognizeBankCard(param, new OnResultListener<BankCardResult>() {
//                        @Override
//                        public void onResult(BankCardResult result) {
////                            alertText(result.getBankName(),"卡号为："+result.getBankCardNumber());
//                            String str = null;
//                            if (result.getBankCardType()!=null && result.getBankCardType()== BankCardResult.BankCardType.Debit){
//                                str = "卡号为："+result.getBankCardNumber()+";\n"+"类型：储蓄卡";
//                            }else if (result.getBankCardType()!=null && BankCardResult.BankCardType.Credit.equals(result.getBankCardType())){
//                                str = "卡号为："+result.getBankCardNumber()+";\n"+"类型：信用卡";
//                            }else{
//                                str = "卡号为："+result.getBankCardNumber()+";\n"+"类型:"+result.getBankCardType();
//                            }
//                            dialogStyle.setType(1);
//                            dialogStyle.setTitle(result.getBankName());
//                            dialogStyle.setMessage(str);
//                            dialogStyle.show();
//                        }
//
//                        @Override
//                        public void onError(OCRError ocrError) {
////                            alertText("获取身份证信息：",ocrError.getMessage());
//                            dialogStyle.setType(1);
//                            dialogStyle.setMessage(ocrError.getMessage());
//                            dialogStyle.show();
//                        }
//                    });
                    break;

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放内存资源
        OCR.getInstance().release();
    }
}
