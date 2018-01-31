package socilgirl.dell.mydemo.model;

/**
 * Created by Administrator on 2018/1/18/018.
 */

public class NameBin  {
    String strUri;
    String picName;

    public NameBin(String strUri, String picName) {
        this.picName = strUri;
        this.strUri = picName;
    }

    public String getStrUri() {
        return strUri;
    }

    public void setStrUri(String strUri) {
        this.strUri = strUri;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    @Override
    public String toString() {
        return strUri+"/n"+picName;
    }
}
