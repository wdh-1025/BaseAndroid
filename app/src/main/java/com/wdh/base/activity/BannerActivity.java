package com.wdh.base.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.baselib.ui.module.banner.Banner;
import com.baselib.ui.module.banner.BannerConfig;
import com.baselib.ui.module.banner.listener.OnBannerClickListener;
import com.baselib.ui.module.banner.transformer.AccordionTransformer;
import com.baselib.ui.module.base.BaseLayoutActivity;
import com.wdh.base.R;
import com.wdh.base.adapter.BannerLoaderImage;

import java.util.ArrayList;
import java.util.List;


public class BannerActivity extends BaseLayoutActivity implements OnBannerClickListener {
    Banner banner1;
    Banner banner2;
    Banner banner3;
    Banner banner4;
    Banner banner5;
    Banner banner6;
    Banner banner7;
    Banner banner8;
    Banner banner9;
    Banner banner10;
    Banner banner11;
    private List<String> imgUrl = new ArrayList<>();
    private List<String> titles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        setToolbarBackgroudColor(0xff228efd);
        setToolbarTitle("輪播圖",0xffffffff);
        setToolbarLeftIcon(R.drawable.icon_aleft_white);

        banner1 = (Banner) findViewById(R.id.banner1);
        banner2 = (Banner) findViewById(R.id.banner2);
        banner3 = (Banner) findViewById(R.id.banner3);
        banner4 = (Banner) findViewById(R.id.banner4);
        banner5 = (Banner) findViewById(R.id.banner5);
        banner6 = (Banner) findViewById(R.id.banner6);
        banner7 = (Banner) findViewById(R.id.banner7);
        banner8 = (Banner) findViewById(R.id.banner8);
        banner9 = (Banner) findViewById(R.id.banner9);
        banner10 = (Banner) findViewById(R.id.banner10);
        banner11 = (Banner) findViewById(R.id.banner11);
        imgUrl.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        imgUrl.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        imgUrl.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        imgUrl.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        titles.add("51巅峰钜惠");
        titles.add("十大星级品牌联盟，全场2折起");
        titles.add("生命不是要超越别人，而是要超越自己。");
        titles.add("嗨购5折不要停");
        start();
    }

    private void start() {
        //简单使用
        banner1.setImages(imgUrl)
                .setImageLoader(new BannerLoaderImage())
                .setOnBannerClickListener(this)
                .start();
        //带动画，所有动画在transformer下
        banner2.setImages(imgUrl)
                .setImageLoader(new BannerLoaderImage())
                .setOnBannerClickListener(this)
                .start();
        banner2.setBannerAnimation(AccordionTransformer.class);
        //样式一
        banner3.setImages(imgUrl)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.NOT_INDICATOR)
                .setImageLoader(new BannerLoaderImage())
                .start();
        //样式二
        banner4.setImages(imgUrl)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new BannerLoaderImage())
                .start();
        //样式三
        banner5.setImages(imgUrl)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.NUM_INDICATOR)
                .setImageLoader(new BannerLoaderImage())
                .start();
        //样式四
        banner6.setImages(imgUrl)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setImageLoader(new BannerLoaderImage())
                .start();
        //样式五
        banner7.setImages(imgUrl)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setImageLoader(new BannerLoaderImage())
                .start();
        //样式六
        banner8.setImages(imgUrl)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new BannerLoaderImage())
                .start();

        //下面是自定义属性
        banner9.setImages(imgUrl)
                .setImageLoader(new BannerLoaderImage())
                .start();

        banner10.setImages(imgUrl)
                .setImageLoader(new BannerLoaderImage())
                .start();

        banner11.setImages(imgUrl)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new BannerLoaderImage())
                .start();

    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(this, "点击了" + position, Toast.LENGTH_SHORT).show();
    }
}
