package socilgirl.dell.mydemo.sharedprefrence;

import java.util.Comparator;

/**
 * Create by "hou" on 2017/10/31.
 * 默认比较器，当存储List集合中的String类型数据时，没有指定比较器，就使用默认比较器
 */

public class ComparatorImpl implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
