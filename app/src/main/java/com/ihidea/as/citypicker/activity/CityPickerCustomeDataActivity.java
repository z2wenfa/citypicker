package com.ihidea.as.citypicker.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ihidea.as.citypicker.R;
import com.lljjcoder.Interface.OnCustomCityPickerItemClickListener;
import com.lljjcoder.bean.CustomCityData;
import com.lljjcoder.citywheel.CustomConfig;
import com.lljjcoder.style.citycustome.CustomCityPicker;

import java.util.ArrayList;
import java.util.List;

public class CityPickerCustomeDataActivity extends AppCompatActivity {

    private Button customeBtn;
    private TextView resultTv;
    private CustomCityPicker customCityPicker = null;
    /**
     * 自定义数据源-省份数据
     */
    private List<CustomCityData> mProvinceListData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_picker_custome_data);
        initCustomeCityData();

        customCityPicker = new CustomCityPicker(this);
        customeBtn = (Button) findViewById(R.id.customBtn);
        resultTv = (TextView) findViewById(R.id.resultTv);
        customeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showView();
            }

        });

    }

    private void showView() {
        CustomConfig cityConfig = new CustomConfig.Builder()
                .title("选择城市")
                .visibleItemsCount(5)
                .setCityData(mProvinceListData)
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .build();

        customCityPicker.setCustomConfig(cityConfig);
        customCityPicker.setOnCustomCityPickerItemClickListener(new OnCustomCityPickerItemClickListener() {
            @Override
            public void onSelected(CustomCityData province, CustomCityData city, CustomCityData district) {
                if (province != null && city != null && district != null) {
                    resultTv.setText("province：" + province.getName() + "    " + province.getId() + "\n" +
                            "city：" + city.getName() + "    " + city.getId() + "\n" +
                            "area：" + district.getName() + "    " + district.getId() + "\n");
                }else{
                    resultTv.setText("结果出错！");
                }
            }
        });
        customCityPicker.showCityPicker();

    }

    private void initCustomeCityData() {

        CustomCityData jsPro = new CustomCityData("10000", "江苏省");

        CustomCityData ycCity = new CustomCityData("11000", "盐城市");
        List<CustomCityData> ycDistList = new ArrayList<>();
        ycDistList.add(new CustomCityData("11100", "滨海县"));
        ycDistList.add(new CustomCityData("11200", "阜宁县"));
        ycDistList.add(new CustomCityData("11300", "大丰市"));
        ycDistList.add(new CustomCityData("11400", "盐都区"));
        ycCity.setList(ycDistList);

        CustomCityData czCity = new CustomCityData("12000", "常州市");
        List<CustomCityData> czDistList = new ArrayList<>();
        czDistList.add(new CustomCityData("12100", "新北区"));
        czDistList.add(new CustomCityData("12200", "天宁区"));
        czDistList.add(new CustomCityData("12300", "钟楼区"));
        czDistList.add(new CustomCityData("12400", "武进区"));
        czCity.setList(czDistList);

        List<CustomCityData> jsCityList = new ArrayList<>();
        jsCityList.add(ycCity);
        jsCityList.add(czCity);

        jsPro.setList(jsCityList);


        CustomCityData zjPro = new CustomCityData("20000", "浙江省");

        CustomCityData nbCity = new CustomCityData("21000", "宁波市");
        List<CustomCityData> nbDistList = new ArrayList<>();
        nbDistList.add(new CustomCityData("21100", "海曙去"));
        nbDistList.add(new CustomCityData("21200", "鄞州区"));
        nbCity.setList(nbDistList);

        CustomCityData hzCity = new CustomCityData("22000", "杭州市");
        List<CustomCityData> hzDistList = new ArrayList<>();
        hzDistList.add(new CustomCityData("22100", "上城区"));
        hzDistList.add(new CustomCityData("22200", "西湖区"));
        hzDistList.add(new CustomCityData("22300", "下沙区"));
        hzCity.setList(hzDistList);

        List<CustomCityData> zjCityList = new ArrayList<>();
        zjCityList.add(hzCity);
        zjCityList.add(nbCity);

        zjPro.setList(zjCityList);


        CustomCityData gdPro = new CustomCityData("30000", "广东省");

        CustomCityData fjCity = new CustomCityData("21000", "潮州市");
        List<CustomCityData> fjDistList = new ArrayList<>();
        fjDistList.add(new CustomCityData("21100", "湘桥区"));
        fjDistList.add(new CustomCityData("21200", "潮安区"));
        fjCity.setList(fjDistList);



        CustomCityData gzCity = new CustomCityData("22000", "广州市");
        List<CustomCityData> szDistList = new ArrayList<>();
        szDistList.add(new CustomCityData("22100", "荔湾区"));
        szDistList.add(new CustomCityData("22200", "增城区"));
        szDistList.add(new CustomCityData("22300", "从化区"));
        szDistList.add(new CustomCityData("22400", "南沙区"));
        szDistList.add(new CustomCityData("22500", "花都区"));
        szDistList.add(new CustomCityData("22600", "番禺区"));
        szDistList.add(new CustomCityData("22700", "黄埔区"));
        szDistList.add(new CustomCityData("22800", "白云区"));
        szDistList.add(new CustomCityData("22900", "天河区"));
        szDistList.add(new CustomCityData("22110", "海珠区"));
        szDistList.add(new CustomCityData("22120", "越秀区"));
        gzCity.setList(szDistList);

        List<CustomCityData> gdCityList = new ArrayList<>();
        gdCityList.add(gzCity);
        gdCityList.add(fjCity);

        gdPro.setList(gdCityList);


        mProvinceListData.add(jsPro);
        mProvinceListData.add(zjPro);
        mProvinceListData.add(gdPro);

    }
}
