package com.baselib.ui.module.base.utils;

/**
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃永无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * Created by  Administrator  on 2017/5/26.
 * Email:924686754@qq.com
 * 基本列表页的配置
 */
public class ListSettings {
    //垂直方向的列表
    public static final int LAYOUT_LIST = 0;
    //垂直方向的表格布局
    public static final int LAYOUT_GRID = 1;
    //垂直方向的瀑布流(暂不支持瀑布流)
    public static final int LAYOUT_STAGGERD = 2;
    //列表类型
    private int listType;
    // 分页数据页大小
    private int pageSize = 10;
    //grid列数
    private int gridSpanCount;
    //头部分隔线的高度
    private int headerDividerHeight;
    //头部分隔线的颜色
    private int headDividerColor;
    //内容分隔线的高度
    private int contentDividerHeight;
    //内容分隔线的颜色
    private int contentDividerColor;
    //底部背景色
    private int bottomBackgroudColor;
    //是否可以刷新
    private boolean canRefresh = true;
    //是否可以加载更多
    private boolean canLoadMore = true;
    //无内容背景颜色
    private int noDataBackgroudColor;
    // 自定义内容布局Id
    private int customLayout;
    //内容item布局
    private int itemLayout;
    //头部item布局
    private int headLayout;
    //没网络、空内容时，“遮罩”是否覆盖整个数据列表，即连头部也不显示
    private boolean noDataShowHeader = false;

    public int getListType() {
        return listType;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getGridSpanCount() {
        return gridSpanCount;
    }

    public void setGridSpanCount(int gridSpanCount) {
        this.gridSpanCount = gridSpanCount;
    }

    public int getHeaderDividerHeight() {
        return headerDividerHeight;
    }

    public void setHeaderDividerHeight(int headerDividerHeight) {
        this.headerDividerHeight = headerDividerHeight;
    }

    public int getHeadDividerColor() {
        return headDividerColor;
    }

    public void setHeadDividerColor(int headDividerColor) {
        this.headDividerColor = headDividerColor;
    }

    public int getContentDividerHeight() {
        return contentDividerHeight;
    }

    public void setContentDividerHeight(int contentDividerHeight) {
        this.contentDividerHeight = contentDividerHeight;
    }

    public int getContentDividerColor() {
        return contentDividerColor;
    }

    public void setContentDividerColor(int contentDividerColor) {
        this.contentDividerColor = contentDividerColor;
    }

    public int getBottomBackgroudColor() {
        return bottomBackgroudColor;
    }

    public void setBottomBackgroudColor(int bottomBackgroudColor) {
        this.bottomBackgroudColor = bottomBackgroudColor;
    }

    public boolean isCanRefresh() {
        return canRefresh;
    }

    public void setCanRefresh(boolean canRefresh) {
        this.canRefresh = canRefresh;
    }

    public boolean isCanLoadMore() {
        return canLoadMore;
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }

    public int getNoDataBackgroudColor() {
        return noDataBackgroudColor;
    }

    public void setNoDataBackgroudColor(int noDataBackgroudColor) {
        this.noDataBackgroudColor = noDataBackgroudColor;
    }

    public int getCustomLayout() {
        return customLayout;
    }

    public void setCustomLayout(int customLayout) {
        this.customLayout = customLayout;
    }

    public int getItemLayout() {
        return itemLayout;
    }

    public void setItemLayout(int itemLayout) {
        this.itemLayout = itemLayout;
    }

    public int getHeadLayout() {
        return headLayout;
    }

    public void setHeadLayout(int headLayout) {
        this.headLayout = headLayout;
    }

    public boolean isNoDataShowHeader() {
        return noDataShowHeader;
    }

    public void setNoDataShowHeader(boolean noDataShowHeader) {
        this.noDataShowHeader = noDataShowHeader;
    }
}
