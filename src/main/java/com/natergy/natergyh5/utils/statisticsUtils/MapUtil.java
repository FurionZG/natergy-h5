package com.natergy.natergyh5.utils.statisticsUtils;



import com.natergy.natergyh5.entity.statisticsEntity.SaleInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 王杰开发
 */
public class MapUtil {


    public static void SortMap(Map<String, SaleInfo> data, final String flag) {

        List<Map.Entry<String, SaleInfo>> list = new ArrayList<Map.Entry<String, SaleInfo>>(data.entrySet());

        list.sort(new Comparator<Map.Entry<String, SaleInfo>>() {
            @Override
            public int compare(Map.Entry<String, SaleInfo> o1, Map.Entry<String, SaleInfo> o2) {
                if (flag == "w") {
                    return o2.getValue().getTotalNetWeight().compareTo(o1.getValue().getTotalNetWeight());
                } else {
                    return o2.getValue().getTotalPrice().compareTo(o1.getValue().getTotalPrice());
                }
            }
        });

        data.clear();
        for (Map.Entry<String, SaleInfo> item :list){
            data.put(item.getKey(),item.getValue());
        }
    }


}
