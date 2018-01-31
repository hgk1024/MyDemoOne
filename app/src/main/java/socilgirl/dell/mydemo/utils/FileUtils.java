package socilgirl.dell.mydemo.utils;

import android.content.Context;

import java.io.File;

/**
 * Created by Administrator on 2018/1/6/006.
 */

public class FileUtils {
    public static File getSaveFile(Context context){
        File file = new File(context.getFilesDir(),"pc.jpg");
        return file;
    }
}
