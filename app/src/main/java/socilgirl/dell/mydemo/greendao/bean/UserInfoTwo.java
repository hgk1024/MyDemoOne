package socilgirl.dell.mydemo.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by dell on 2017/10/30.
 * 想测试是否可以建立两个数据库文件，没有写
 */

@Entity
public class UserInfoTwo {
    @Id
    private long userid;
    private String userName;
    private String userPhone;
    @NotNull
    private String passWord;

    @Generated(hash = 1910000769)
    public UserInfoTwo(long userid, String userName, String userPhone,
            @NotNull String passWord) {
        this.userid = userid;
        this.userName = userName;
        this.userPhone = userPhone;
        this.passWord = passWord;
    }

    @Generated(hash = 1746044017)
    public UserInfoTwo() {
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
