package socilgirl.dell.mydemo.datapackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by "hou" on 2017/11/1.
 * 提供分页数据
 */

public  class DataResource {
    private static List<String> mDatas = new ArrayList<>();
    private static int page = 0;

    public static List<String> getData(){
        page = 0;
        mDatas.clear();
        for (int i=0;i < 10;i++){
            mDatas.add("我是排名第" + i + "的猛男");
        }
        return mDatas;
    }

    public static List<String> getMoreData(){//每次执行该方法
        page = page+1;
        if (page == 1){
            for (int i = 10 ; i < 20 ; i++) {
                mDatas.add("我是排名第"+i+"的屌丝");
            }
        }else{
            for (int i = 10 * page; i < 10 * (page + 1); i++) {
                mDatas.add("我是排名第"+i+"的渣男");
            }
        }
        return mDatas;
    }
}
