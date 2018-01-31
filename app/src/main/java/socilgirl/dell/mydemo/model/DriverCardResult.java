package socilgirl.dell.mydemo.model;

import com.baidu.ocr.sdk.model.ResponseResult;

/**
 * Created by Administrator on 2018/1/8/008.
 */

public class DriverCardResult {
    public DriverCardResult() {
    }
    private String log_id;
    private String words_result_num;
    private WordsResult words_esult;
    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public String getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(String words_result_num) {
        this.words_result_num = words_result_num;
    }

    public WordsResult getWords_esult() {
        return words_esult;
    }

    public void setWords_esult(WordsResult words_esult) {
        this.words_esult = words_esult;
    }

    public class WordsResult{
        private String id_number;//证件号码
        private  String limtedate;//有效期限XX年
        private String cartype;//准驾类型
        private String validity;//有效起始日期
        private String address;//住址
        private String name;//姓名
        private String nationality;//国籍
        private String brithday;//出生日期
        private String gender;//性别
        private String startday;//领证初始日期

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getLimtedate() {
            return limtedate;
        }

        public void setLimtedate(String limtedate) {
            this.limtedate = limtedate;
        }

        public String getCartype() {
            return cartype;
        }

        public void setCartype(String cartype) {
            this.cartype = cartype;
        }

        public String getValidity() {
            return validity;
        }

        public void setValidity(String validity) {
            this.validity = validity;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getBrithday() {
            return brithday;
        }

        public void setBrithday(String brithday) {
            this.brithday = brithday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getStartday() {
            return startday;
        }

        public void setStartday(String startday) {
            this.startday = startday;
        }
    }
}
