package socilgirl.dell.mydemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.ToxicBakery.viewpager.transforms.FlipVerticalTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutTranformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import socilgirl.dell.mydemo.adapter.MyItemClickListener;
import socilgirl.dell.mydemo.adapter.SecondViewAdapter;
import socilgirl.dell.mydemo.imagemanger.ImageLoaderOptions;
import socilgirl.dell.mydemo.imagemanger.ImageManager;

public class Main2Activity extends Activity implements OnItemClickListener {
    private ConvenientBanner convenientBanner;//顶部广告栏控件
    private RecyclerView recyclerView;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private ArrayList<String> transformerList = new ArrayList<String>();
    private SecondViewAdapter adapter;
    private List<String> networkImages;
//    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
//            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
//            "http://d.3987.com/sqmy_131219/001.jpg",
//            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
//            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
//            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
//            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
//    };
    private List<String> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setImagesDatas();
        convenientBanner = findViewById(R.id.banner_secondActivity);
        recyclerView = findViewById(R.id.recyclerview_secondView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new SecondViewAdapter(this,transformerList);
        recyclerView.setAdapter(adapter);
        loadTextDatas();

        adapter.setOnClickItemListener(new MyItemClickListener() {
            @Override
            public void onClickItem(View view, int position) {
//                Toast.makeText(Main2Activity.this, "第"+position+"项被点击了", Toast.LENGTH_SHORT).show();
                String transforemerName = transformerList.get(position);
                try {
                    Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
                    ABaseTransformer transforemer= (ABaseTransformer)cls.newInstance();
                    convenientBanner.getViewPager().setPageTransformer(true,transforemer);

                    //部分3D特效需要调整滑动速度
                    if(transforemerName.equals("StackTransformer")){
                        convenientBanner.setScrollDuration(1200);
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        convenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>(){

            @Override
            public LocalImageHolderView createHolder() {

                return new LocalImageHolderView();
            }
        },mList)
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator,R.mipmap.ic_page_indicator_focused})
                .setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        convenientBanner.startTurning(3000);
    }

    //添加轮播图数据
    private void setImagesDatas() {
        mList = new ArrayList<>();
        mList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1380084653,2448555822&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=227953490,3054069314&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2302918630,1086443006&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2068546062,2852291024&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4219594110,2716012792&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2586885873,577264777&fm=27&gp=0.jpg");
        mList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1380084653,2448555822&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=227953490,3054069314&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2302918630,1086443006&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2068546062,2852291024&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4219594110,2716012792&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2586885873,577264777&fm=27&gp=0.jpg");
        mList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1380084653,2448555822&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=227953490,3054069314&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2302918630,1086443006&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2068546062,2852291024&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4219594110,2716012792&fm=27&gp=0.jpg");
        mList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2586885873,577264777&fm=27&gp=0.jpg");
    }

    //添加recycleView数据
    private void loadTextDatas() {
        //各种翻页效果
        transformerList.add(DefaultTransformer.class.getSimpleName());
        transformerList.add(AccordionTransformer.class.getSimpleName());
        transformerList.add(BackgroundToForegroundTransformer.class.getSimpleName());
        transformerList.add(CubeInTransformer.class.getSimpleName());
        transformerList.add(CubeOutTransformer.class.getSimpleName());
        transformerList.add(DepthPageTransformer.class.getSimpleName());
        transformerList.add(FlipHorizontalTransformer.class.getSimpleName());
        transformerList.add(FlipVerticalTransformer.class.getSimpleName());
        transformerList.add(ForegroundToBackgroundTransformer.class.getSimpleName());
        transformerList.add(RotateDownTransformer.class.getSimpleName());
        transformerList.add(RotateUpTransformer.class.getSimpleName());
        transformerList.add(StackTransformer.class.getSimpleName());
        transformerList.add(ZoomInTransformer.class.getSimpleName());
        transformerList.add(ZoomOutTranformer.class.getSimpleName());

        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        convenientBanner.stopTurning();
    }

    //轮播器子项点击事件
    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "轮播器中的第" + position+1 + "找那个图片被点击了", Toast.LENGTH_SHORT).show();
    }

    //自定义
    public class LocalImageHolderView implements Holder<String> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String url) {
            ImageManager.getInstance()
                    .showImage(new ImageLoaderOptions.Builder(imageView,url).build());
        }
    }
}
