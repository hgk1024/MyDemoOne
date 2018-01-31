package socilgirl.dell.mydemo.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/18/018.
 */

public class UMPushMessageBin {


    /**
     * policy : {"expire_time":"2018-01-21 15:32:32"}
     * description : 有新功能上线
     * production_mode : true
     * appkey : 5a3e2280b27b0a1bb1000283
     * payload : {"body":{"title":"添加收款通道","ticker":"添加收款通道","text":"合力宝收款、代还更方便稳定","after_open":"go_app","play_vibrate":"false","play_lights":"false","play_sound":"true"},"display_type":"notification"}
     * device_tokens : AqBo-TV6HkiZILo2PZBMyg_SeeG1bCys3989baRCi0D9
     * type : unicast
     * timestamp : 1516262886887
     */

    private PolicyBean policy;
    private String description;
    private boolean production_mode;
    private String appkey;
    private PayloadBean payload;
    private String device_tokens;
    private String type;
    private String timestamp;

    public static UMPushMessageBin objectFromData(String str) {

        return new Gson().fromJson(str, UMPushMessageBin.class);
    }

    public static UMPushMessageBin objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), UMPushMessageBin.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<UMPushMessageBin> arrayUMPushMessageBinFromData(String str) {

        Type listType = new TypeToken<ArrayList<UMPushMessageBin>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<UMPushMessageBin> arrayUMPushMessageBinFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<UMPushMessageBin>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public PolicyBean getPolicy() {
        return policy;
    }

    public void setPolicy(PolicyBean policy) {
        this.policy = policy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isProduction_mode() {
        return production_mode;
    }

    public void setProduction_mode(boolean production_mode) {
        this.production_mode = production_mode;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public PayloadBean getPayload() {
        return payload;
    }

    public void setPayload(PayloadBean payload) {
        this.payload = payload;
    }

    public String getDevice_tokens() {
        return device_tokens;
    }

    public void setDevice_tokens(String device_tokens) {
        this.device_tokens = device_tokens;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class PolicyBean {
        /**
         * expire_time : 2018-01-21 15:32:32
         */

        private String expire_time;

        public static PolicyBean objectFromData(String str) {

            return new Gson().fromJson(str, PolicyBean.class);
        }

        public static PolicyBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), PolicyBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<PolicyBean> arrayPolicyBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<PolicyBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<PolicyBean> arrayPolicyBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<PolicyBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }
    }

    public static class PayloadBean {
        /**
         * body : {"title":"添加收款通道","ticker":"添加收款通道","text":"合力宝收款、代还更方便稳定","after_open":"go_app","play_vibrate":"false","play_lights":"false","play_sound":"true"}
         * display_type : notification
         */

        private BodyBean body;
        private String display_type;

        public static PayloadBean objectFromData(String str) {

            return new Gson().fromJson(str, PayloadBean.class);
        }

        public static PayloadBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), PayloadBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<PayloadBean> arrayPayloadBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<PayloadBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<PayloadBean> arrayPayloadBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<PayloadBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public BodyBean getBody() {
            return body;
        }

        public void setBody(BodyBean body) {
            this.body = body;
        }

        public String getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(String display_type) {
            this.display_type = display_type;
        }

        public static class BodyBean {
            /**
             * title : 添加收款通道
             * ticker : 添加收款通道
             * text : 合力宝收款、代还更方便稳定
             * after_open : go_app
             * play_vibrate : false
             * play_lights : false
             * play_sound : true
             */

            private String title;
            private String ticker;
            private String text;
            private String after_open;
            private String play_vibrate;
            private String play_lights;
            private String play_sound;

            public static BodyBean objectFromData(String str) {

                return new Gson().fromJson(str, BodyBean.class);
            }

            public static BodyBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), BodyBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<BodyBean> arrayBodyBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<BodyBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<BodyBean> arrayBodyBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<BodyBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTicker() {
                return ticker;
            }

            public void setTicker(String ticker) {
                this.ticker = ticker;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getAfter_open() {
                return after_open;
            }

            public void setAfter_open(String after_open) {
                this.after_open = after_open;
            }

            public String getPlay_vibrate() {
                return play_vibrate;
            }

            public void setPlay_vibrate(String play_vibrate) {
                this.play_vibrate = play_vibrate;
            }

            public String getPlay_lights() {
                return play_lights;
            }

            public void setPlay_lights(String play_lights) {
                this.play_lights = play_lights;
            }

            public String getPlay_sound() {
                return play_sound;
            }

            public void setPlay_sound(String play_sound) {
                this.play_sound = play_sound;
            }
        }
    }
}
