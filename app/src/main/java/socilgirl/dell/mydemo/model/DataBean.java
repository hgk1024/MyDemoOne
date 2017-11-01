package socilgirl.dell.mydemo.model;

/**
 * Create by "hou" on 2017/11/1.
 * 使用GsonFormate插件
 */

public class DataBean {

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
}
