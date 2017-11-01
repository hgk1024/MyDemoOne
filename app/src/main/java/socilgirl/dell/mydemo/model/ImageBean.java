package socilgirl.dell.mydemo.model;

/**
 * Create by "hou" on 2017/11/1.
 */

public class ImageBean {
    private String url;
    private String name;

    public ImageBean(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
