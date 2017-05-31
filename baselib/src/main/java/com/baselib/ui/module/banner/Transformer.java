package com.baselib.ui.module.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.baselib.ui.module.banner.transformer.AccordionTransformer;
import com.baselib.ui.module.banner.transformer.CubeInTransformer;
import com.baselib.ui.module.banner.transformer.CubeOutTransformer;
import com.baselib.ui.module.banner.transformer.DefaultTransformer;
import com.baselib.ui.module.banner.transformer.DepthPageTransformer;
import com.baselib.ui.module.banner.transformer.FlipHorizontalTransformer;
import com.baselib.ui.module.banner.transformer.FlipVerticalTransformer;
import com.baselib.ui.module.banner.transformer.TabletTransformer;
import com.baselib.ui.module.banner.transformer.ZoomInTransformer;
import com.baselib.ui.module.banner.transformer.ZoomOutSlideTransformer;
import com.baselib.ui.module.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
