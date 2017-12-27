package socilgirl.dell.mydemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Create by "hou" on 2017/11/1.
 * 使用GsonFormate插件
 */

public class DataBean implements Parcelable {

    /**
     * name : hou
     * age : 45
     * sex : 男
     * is_true : true
     */

    private String name;
    private int age;
    private String sex;
    private boolean is_true;

    public DataBean(String name, int age, String sex, boolean is_true) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.is_true = is_true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isIs_true() {
        return is_true;
    }

    public void setIs_true(boolean is_true) {
        this.is_true = is_true;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", is_true=" + is_true +
                '}';
    }

    //内容描述接口，基本不用管
    @Override
    public int describeContents() {
        return 0;
    }

    //写入接口函数、打包
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeString(this.sex);
        dest.writeByte(this.is_true ? (byte) 1 : (byte) 0);
    }

    //读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
    // 因为实现类在这里还是不可知的，所以需要用到模板的方式，继承类名通过模板参数传入
    protected DataBean(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
        this.sex = in.readString();
        this.is_true = in.readByte() != 0;
    }

    //为了能够实现模板参数的传入，这里定义Creator嵌入接口,内含两个接口函数分别返回单个和多个继承类实例
    public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
        @Override
        public DataBean createFromParcel(Parcel source) {
            return new DataBean(source);
        }

        @Override
        public DataBean[] newArray(int size) {
            return new DataBean[size];
        }
    };
}
