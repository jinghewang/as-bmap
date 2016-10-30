package com.hbdworld.bmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.utils.poi.BaiduMapPoiSearch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MapView mMapView = null;
    BaiduMap mBaiduMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.main);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();


        this.bindOnClickListener(this, R.id.btn_type_normal, R.id.btn_type_satellite, R.id.btn_test3);

        //---

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        switch (view.getId()) {
            case R.id.btn_type_normal:
                //普通地图
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                mBaiduMap.setTrafficEnabled(true);
                break;

            case R.id.btn_type_satellite:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                showToast(btn.getText().toString());
                break;

            case R.id.btn_test3:
                // 构建 导航参数
// 天安门坐标
                double mLat1 = 39.915291;
                double mLon1 = 116.403857;
// 百度大厦坐标
                double mLat2 = 40.056858;
                double mLon2 = 116.308194;
                LatLng pt1 = new LatLng(mLat1, mLon1);
                LatLng pt2 = new LatLng(mLat2, mLon2);

                NaviParaOption para = new NaviParaOption()
                        .startPoint(pt1).endPoint(pt2)
                        .startName("天安门").endName("百度大厦");

                try {
// 调起百度地图步行导航
                    BaiduMapNavigation.openBaiduMapWalkNavi(para, this);
                } catch (BaiduMapAppNotSupportNaviException e) {
                    e.printStackTrace();
                    //showDialog();
                }
                break;

            default:
                break;
        }
    }


    public void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public Button findButtonById(int view) {
        return (Button) this.findViewById(view);
    }

    public TextView findTextViewById(int view) {
        return (TextView) this.findViewById(view);
    }

    public void bindOnClickListener(View.OnClickListener listener, int... views) {
        for (int view : views) {
            View v = this.findViewById(view);
            v.setOnClickListener(listener);
        }
    }
}
