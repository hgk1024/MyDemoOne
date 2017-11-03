package socilgirl.dell.mydemo.gaodeditu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.autonavi.tbt.TrafficFacilityInfo;

import socilgirl.dell.mydemo.R;

public class MapOneActivity extends Activity {

    AMapNaviView naviView;
    AMapNavi aMapNavi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_one);
        // 获取AMapNaviView实例，并设置监听。
        initView(savedInstanceState);
        //获取AMapNavi实例，并设置监听。
//        initViewTwo();

    }

    private void initView(Bundle avedInstanceState) {
        naviView = findViewById(R.id.map_one);
        naviView.onCreate(avedInstanceState);
        naviView.setAMapNaviViewListener(new AMapNaviViewListener() {
            @Override
            public void onNaviSetting() {
                Log.d("MapOneActivity", "onNaviSetting被执行了");
            }

            @Override
            public void onNaviCancel() {
                Log.d("MapOneActivity", "onNaviCancel被执行了");
            }

            @Override
            public boolean onNaviBackClick() {
                Log.d("MapOneActivity", "onNaviBackClick被执行了");
                return false;
            }

            @Override
            public void onNaviMapMode(int i) {
                Log.d("MapOneActivity", "onNaviMapMode被执行了");
            }

            @Override
            public void onNaviTurnClick() {
                Log.d("MapOneActivity", "onNaviTurnClick被执行了");
            }

            @Override
            public void onNextRoadClick() {
                Log.d("MapOneActivity", "oonNextRoadClick被执行了");
            }

            @Override
            public void onScanViewButtonClick() {
                Log.d("MapOneActivity", "onScanViewButtonClick被执行了");
            }

            @Override
            public void onLockMap(boolean b) {
                Log.d("MapOneActivity", "onLockMap被执行了");
            }

            @Override
            public void onNaviViewLoaded() {
                Log.d("MapOneActivity", "onNaviViewLoaded被执行了");
            }
        });
    }

    private void initViewTwo() {
        aMapNavi = AMapNavi.getInstance(getApplicationContext());
        aMapNavi.addAMapNaviListener(new AMapNaviListener() {
            @Override
            public void onInitNaviFailure() {

            }

            @Override
            public void onInitNaviSuccess() {
                //导航对象AMapNavi对象初始化成功时，进行路径规划。
                /**
                 * 方法:
                 *   int strategy=mAMapNavi.strategyConvert(congestion, avoidhightspeed, cost, hightspeed, multipleroute);
                 * 参数:
                 * @congestion 躲避拥堵
                 * @avoidhightspeed 不走高速
                 * @cost 避免收费
                 * @hightspeed 高速优先
                 * @multipleroute 多路径
                 *
                 * 说明:
                 *      以上参数都是boolean类型，其中multipleroute参数表示是否多条路线，如果为true则此策略会算出多条路线。
                 * 注意:
                 *      不走高速与高速优先不能同时为true
                 *      高速优先与避免收费不能同时为true
                 */
                int strategy = 0;
                try {
                    strategy = aMapNavi.strategyConvert(true, false, false, false, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //驾车路径计算
//                aMapNavi.calculateDriveRoute(sList, eList, null, strategy);
            }

            @Override
            public void onStartNavi(int i) {

            }

            @Override
            public void onTrafficStatusUpdate() {

            }

            @Override
            public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

            }

            @Override
            public void onGetNavigationText(int i, String s) {

            }

            @Override
            public void onGetNavigationText(String s) {

            }

            @Override
            public void onEndEmulatorNavi() {

            }

            @Override
            public void onArriveDestination() {

            }

            @Override
            public void onCalculateRouteFailure(int i) {

            }

            @Override
            public void onReCalculateRouteForYaw() {

            }

            @Override
            public void onReCalculateRouteForTrafficJam() {

            }

            @Override
            public void onArrivedWayPoint(int i) {

            }

            @Override
            public void onGpsOpenStatus(boolean b) {

            }

            @Override
            public void onNaviInfoUpdate(NaviInfo naviInfo) {

            }

            @Override
            public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

            }

            @Override
            public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {

            }

            @Override
            public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {

            }

            @Override
            public void showCross(AMapNaviCross aMapNaviCross) {

            }

            @Override
            public void hideCross() {

            }

            @Override
            public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

            }

            @Override
            public void hideLaneInfo() {

            }

            @Override
            public void onCalculateRouteSuccess(int[] ints) {
                //当路径规划成功是，开启导航。
                //开始模拟导航
                aMapNavi.startNavi(NaviType.EMULATOR);
            }

            @Override
            public void notifyParallelRoad(int i) {

            }

            @Override
            public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

            }

            @Override
            public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

            }

            @Override
            public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

            }

            @Override
            public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

            }

            @Override
            public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

            }

            @Override
            public void onPlayRing(int i) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        naviView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        naviView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        naviView.onDestroy();
        //since 1.6.0 不再在naviview destroy的时候自动执行AMapNavi.stopNavi();请自行执行
//        aMapNavi.stopNavi();
//        aMapNavi.destroy();
    }
}
