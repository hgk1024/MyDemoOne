package socilgirl.dell.mydemo.model;

import java.util.List;

/**
 * Created by dell on 2017/10/25.
 */

public class UserInfo {

    /**
     * code : 10000
     * msg : tab5用户信息
     * data : {"tags":[],"introduce":"","nickname":"","headpic":"http://qiniu.zaiwaner.cn/defaultpic/defaultheadpic.png","backpic":"http://qiniu.zaiwaner.cn/defaultpic/defaultcoverpic.png","sex":"","city":"","birthday":"","star":5,"role":null,"follow_num":0,"fan_num":0,"favorite_num":0,"publish_num":0,"order_num":0}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tags : []
         * introduce :
         * nickname :
         * headpic : http://qiniu.zaiwaner.cn/defaultpic/defaultheadpic.png
         * backpic : http://qiniu.zaiwaner.cn/defaultpic/defaultcoverpic.png
         * sex :
         * city :
         * birthday :
         * star : 5
         * role : null
         * follow_num : 0
         * fan_num : 0
         * favorite_num : 0
         * publish_num : 0
         * order_num : 0
         */

        private String introduce;
        private String nickname;
        private String headpic;
        private String backpic;
        private String sex;
        private String city;
        private String birthday;
        private int star;
        private Object role;
        private int follow_num;
        private int fan_num;
        private int favorite_num;
        private int publish_num;
        private int order_num;
        private List<?> tags;

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

        public Object getRole() {
            return role;
        }

        public void setRole(Object role) {
            this.role = role;
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

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }
    }
}
