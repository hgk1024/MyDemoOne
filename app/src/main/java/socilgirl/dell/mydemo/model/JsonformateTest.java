package socilgirl.dell.mydemo.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/26/026.
 *
 * 测试gsonformate使用
 */

public class JsonformateTest {


    /**
     * title : Recent Uploads tagged cat
     * link : http://www.flickr.com/photos/tags/cat/
     * description :
     * modified : 2009-08-03T01:50:45Z
     * generator : http://www.flickr.com/
     * items : {"title":"DSC06844","link":"http://www.flickr.com/photos/g_bugel/3783605340/","media":{"m":"http://farm3.static.flickr.com/2638/3783605340_a3cfc9eeb9_m.jpg"},"date_taken":"2009-07-06T07:27:59-08:00","published":"2009-08-03T01:50:45Z","author":"nobody@flickr.com (g.bugel)","author_id":"38658309@N00","tags":"china cat feline beijing 2009 chinalab chinalab2009"}
     */

    private String title;
    private String link;
    private String description;
    private String modified;
    private String generator;
    private ItemsBean items;

    public static JsonformateTest objectFromData(String str) {

        return new Gson().fromJson(str, JsonformateTest.class);
    }

    public static JsonformateTest objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), JsonformateTest.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<JsonformateTest> arrayJsonformateTestFromData(String str) {

        Type listType = new TypeToken<ArrayList<JsonformateTest>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<JsonformateTest> arrayJsonformateTestFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<JsonformateTest>>() {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public ItemsBean getItems() {
        return items;
    }

    public void setItems(ItemsBean items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * title : DSC06844
         * link : http://www.flickr.com/photos/g_bugel/3783605340/
         * media : {"m":"http://farm3.static.flickr.com/2638/3783605340_a3cfc9eeb9_m.jpg"}
         * date_taken : 2009-07-06T07:27:59-08:00
         * published : 2009-08-03T01:50:45Z
         * author : nobody@flickr.com (g.bugel)
         * author_id : 38658309@N00
         * tags : china cat feline beijing 2009 chinalab chinalab2009
         */

        private String title;
        private String link;
        private MediaBean media;
        private String date_taken;
        private String published;
        private String author;
        private String author_id;
        private String tags;

        public static ItemsBean objectFromData(String str) {

            return new Gson().fromJson(str, ItemsBean.class);
        }

        public static ItemsBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ItemsBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ItemsBean> arrayItemsBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ItemsBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<ItemsBean> arrayItemsBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ItemsBean>>() {
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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public MediaBean getMedia() {
            return media;
        }

        public void setMedia(MediaBean media) {
            this.media = media;
        }

        public String getDate_taken() {
            return date_taken;
        }

        public void setDate_taken(String date_taken) {
            this.date_taken = date_taken;
        }

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public static class MediaBean {
            /**
             * m : http://farm3.static.flickr.com/2638/3783605340_a3cfc9eeb9_m.jpg
             */

            private String m;

            public static MediaBean objectFromData(String str) {

                return new Gson().fromJson(str, MediaBean.class);
            }

            public static MediaBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), MediaBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<MediaBean> arrayMediaBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<MediaBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<MediaBean> arrayMediaBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<MediaBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getM() {
                return m;
            }

            public void setM(String m) {
                this.m = m;
            }
        }
    }
}
