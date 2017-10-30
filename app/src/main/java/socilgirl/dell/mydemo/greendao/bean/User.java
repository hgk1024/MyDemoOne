package socilgirl.dell.mydemo.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by dell on 2017/10/30.
 */
@Entity
public class User {
    private String introduce;
    private String nickname;

    private String headpic;
    private String backpic;
    private String sex;
    private String city;
    private String birthday;
    private int star;
    private int follow_num;
    private int fan_num;
    private int favorite_num;
    private int publish_num;
    private int order_num;
    @NotNull
    private String userid;
    @Id
    private long id;

    @Generated(hash = 699288453)
    public User(String introduce, String nickname, String headpic, String backpic, String sex, String city,
                String birthday, int star, int follow_num, int fan_num, int favorite_num, int publish_num,
                int order_num, @NotNull String userid, long id) {
        this.introduce = introduce;
        this.nickname = nickname;
        this.headpic = headpic;
        this.backpic = backpic;
        this.sex = sex;
        this.city = city;
        this.birthday = birthday;
        this.star = star;
        this.follow_num = follow_num;
        this.fan_num = fan_num;
        this.favorite_num = favorite_num;
        this.publish_num = publish_num;
        this.order_num = order_num;
        this.userid = userid;
        this.id = id;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getBackpic() {
        return backpic;
    }

    public void setBackpic(String backpic) {
        this.backpic = backpic;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getFollow_num() {
        return follow_num;
    }

    public void setFollow_num(int follow_num) {
        this.follow_num = follow_num;
    }

    public int getFan_num() {
        return fan_num;
    }

    public void setFan_num(int fan_num) {
        this.fan_num = fan_num;
    }

    public int getFavorite_num() {
        return favorite_num;
    }

    public void setFavorite_num(int favorite_num) {
        this.favorite_num = favorite_num;
    }

    public int getPublish_num() {
        return publish_num;
    }

    public void setPublish_num(int publish_num) {
        this.publish_num = publish_num;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
