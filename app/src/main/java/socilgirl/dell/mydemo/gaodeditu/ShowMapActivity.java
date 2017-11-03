package socilgirl.dell.mydemo.gaodeditu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.offlinemap.OfflineMapManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import socilgirl.dell.mydemo.R;

public class ShowMapActivity extends Activity implements View.OnClickListener, LocationSource, AMapLocationListener, AMap.OnMapScreenShotListener, OfflineMapManager.OfflineMapDownloadListener {

    MapView mapView;
    AMap aMap;
    MyLocationStyle myLocationStyle;
    Button btnDefalt,btnNight,btnStar,starOffLine,stopOff,btnShotScreen;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        initView();
        mapView.onCreate(savedInstanceState);//必须重写这个方法
        if (aMap==null) {
            aMap = mapView.getMap();
        }
        setLocation();//定位
    }

    private void initView() {
        mapView = findViewById(R.id.map);
        btnDefalt = findViewById(R.id.btn_defalt);
        btnNight = findViewById(R.id.btn_night);
        btnStar = findViewById(R.id.btn_star);
        btnShotScreen = findViewById(R.id.btn_shot_screen);
        starOffLine = findViewById(R.id.btn_getMap_offline);
        stopOff = findViewById(R.id.btn_stop_offline);
        starOffLine.setOnClickListener(this);
        stopOff.setOnClickListener(this);
        btnShotScreen.setOnClickListener(this);
        btnStar.setOnClickListener(this);
        btnNight.setOnClickListener(this);
        btnDefalt.setOnClickListener(this);
    }

    private void setLocation() {
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));//设置定位蓝点的icon图标方法，需要用到BitmapDescriptor类对象作为参数。
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        myLocationStyle.strokeWidth(1.0f);//设置定位蓝点精度圈的边框宽度的方法。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_MARGIN_BOTTOM);//地图logo位置设定
        aMap.getUiSettings().setScaleControlsEnabled(true);//控制比例尺是否显示
        aMap.getUiSettings().setCompassEnabled(true);//设置指南针是否显示，用于展示地图方向，默认不显示
//        myLocationStyle.showMyLocation(true);//设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。
//        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。

//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
////以下三种模式从5.1.0版本开始提供
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。

//        MyLocationStyle anchor(float u, float v);//设置定位蓝点图标的锚点方法。
//        MyLocationStyle strokeColor(int color);//设置定位蓝点精度圆圈的边框颜色的方法。
//        MyLocationStyle radiusFillColor(int color);//设置定位蓝点精度圆圈的填充颜色的方法。
//        aMap.setOnMyLocationChangeListener(onMyLocationChangeListener);
        aMap.setTrafficEnabled(true);//显示实时路况图层，aMap是地图控制器对象。

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        deactivate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

//    private OfflineMapManager mapManager;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_defalt:
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                break;
            case R.id.btn_star:
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.btn_night:
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);
                break;
            case R.id.btn_shot_screen:
                aMap.getMapScreenShot(this);//截图
                break;
            case R.id.btn_getMap_offline://跳转到导航测试页面
//                mapManager = new OfflineMapManager(this,this);
//                mapManager.downloadByCityCode();
//                mapManager.downloadByCityName();
                startActivity(new Intent(ShowMapActivity.this,MapOneActivity.class));
                break;
            case R.id.btn_stop_offline:
//                mapManager.pause();//暂停下载
//                mapManager.stop();//停止下载

                break;
        }
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听，获取定位结果
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mLocationOption.setMockEnable(true);//允许模拟位置，默认为true

//单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
// 如果单次定位发生超时情况，定位随即终止；连续定位状态下当前这一次定位会返回超时，但按照既定周期的定位请求会继续发起。
            mLocationOption.setHttpTimeOut(20000);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//开启地位
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                Log.d("ShowMapActivity", "我的经纬度为：" + amapLocation.getLongitude() + "===:" + amapLocation.getLatitude());
                Log.d("ShowMapActivity", "城市为：" + amapLocation.getCity());
                Log.d("ShowMapActivity", "城市编码为：" + amapLocation.getCityCode());
                Log.d("ShowMapActivity", "定位时间是：" + new SimpleDateFormat("yyyy=MM-dd HH:mm:ss").format(amapLocation.getTime()));
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
            }
        }
    }

    //地图 SDK 支持对当前屏幕显示区域进行截屏，可以对地图、覆盖物（包含信息窗口）、Logo进行截取屏幕，这其中不包括地图控件、Toast窗口。
    //onMapScreenShot\onMapScreenShot
    @Override
    public void onMapScreenShot(Bitmap bitmap) {

    }

    @Override
    public void onMapScreenShot(Bitmap bitmap, int status) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        if(null == bitmap){
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(
                    Environment.getExternalStorageDirectory() + "/test_"
                            + sdf.format(new Date()) + ".png");
            boolean b = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            try {
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringBuffer buffer = new StringBuffer();
            if (b)
                buffer.append("截屏成功 ");
            else {
                buffer.append("截屏失败 ");
            }
            if (status != 0)
                buffer.append("地图渲染完成，截屏无网格");
            else {
                buffer.append( "地图未渲染完成，截屏有网格");
            }
            Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    //下载离线地图回调方法onDownload、onCheckUpdate、onRemove
    @Override
    public void onDownload(int i, int i1, String s) {}
    @Override
    public void onCheckUpdate(boolean b, String s) {}
    @Override
    public void onRemove(boolean b, String s, String s1) {}
}
