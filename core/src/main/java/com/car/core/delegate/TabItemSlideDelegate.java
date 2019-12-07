package com.car.core.delegate;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.car.core.R;
import com.car.core.R2;
import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.BaseMvpDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.delegate
 * @time 2019/9/5 21:07
 * @description 管理tab
 */

public abstract class TabItemSlideDelegate<P extends IBasePresenter> extends BaseMvpDelegate
        implements View.OnClickListener {

    @BindView(R2.id.bottom_slide_bar)
    LinearLayoutCompat mBottomBar = null;
    @BindView(R2.id.bottom_bar_slide_delegate_container)
    ViewPager mViewPager = null;

    /**
     * 存储所有的子 Fragment
     */
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    /**
     * 存储所有的子 TabBean
     */
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();

    /**
     * 存储 Fragment和TabBean 的映射
     */
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    /**
     * 默认显示的delegate
     */
    private int mIndexDelegate = 0;

    /**
     * 当前显示的delegate
     */
    private int mCustomDelegate = 0;

    /**
     * tab 的颜色
     */
    public int mSelectColor;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int startDelegate();

    public abstract int selectColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = startDelegate();
        mSelectColor = selectColor();
        //拿到工厂类的实例
        final ItemBuilder builder = ItemBuilder.builder();
        //获取 添加完成的键值对
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        //将 键值对 保存在ITEMS 中
        ITEMS.putAll(items);
        //拿到键和值
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.bottom_slide_delegate;
    }

    @Override
    public void bindView(View view) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            //第一个参数 布局，第二个参数 为给第一个参数加载的布局 设置一个父布局
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            //返回指定的视图
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            item.setOnClickListener(this);
            //设置每个 item的点击事件 和标记
            item.setTag(i);
            //拿到 item 的第一个和 第二个子布局
            final AppCompatImageView itemIcon = (AppCompatImageView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);

            //获取 集合中对应的 Tab
            final BottomTabBean bean = TAB_BEANS.get(i);
            //初始化 tab 数据
            itemIcon.setImageResource(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            if (i == mIndexDelegate) {
                itemTitle.setTextColor(mSelectColor);
            }
        }

        /*
         * Android X 后 Viewpager 适配器加入了 第二个参数
         * 作用是 ：在加载的时候旁边页面的 onResume 不会执行
         */
        mViewPager.setAdapter(new TabItemSlideAdapter(getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setSelectPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mViewPager.setCurrentItem(mIndexDelegate, false);
        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onClick(View view) {
        setTab(view);
    }

    protected void setSelectPage(int page) {
        RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(page);
        defaultTab();
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mSelectColor);
        mCustomDelegate = page;
    }

    protected void setTab(View tab) {
        int pos = (int) tab.getTag();
        RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(pos);
        defaultTab();
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mSelectColor);
        //显示delegate
        mViewPager.setCurrentItem(pos, false);
        mCustomDelegate = pos;
    }

    protected void defaultTab() {
        final int size = mBottomBar.getChildCount();
        for (int i = 0; i < size; i++) {
            RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    class TabItemSlideAdapter extends FragmentPagerAdapter {

        public TabItemSlideAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return ITEM_DELEGATES.get(position);
        }

        @Override
        public int getCount() {
            return ITEM_DELEGATES.size();
        }
    }


}
