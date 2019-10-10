package com.car.core.delegate;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.car.core.R;
import com.car.core.R2;
import com.car.core.mvp.presenter.IBasePresenter;
import com.car.core.mvp.view.BaseMvpFragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.delegate
 * @time 2019/9/5 21:07
 * @description 管理tab
 */

public abstract class TabItemDelegate<P extends IBasePresenter> extends BaseMvpFragment
        implements View.OnClickListener {

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

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
        return R.layout.bottom_delegate;
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
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        //加载多个同级 delegate,中间为要显示的delegate
        getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
        mCustomDelegate = mIndexDelegate;
    }

    @Override
    public void onClick(View view) {
        setTab(view);
    }

    protected void setTab(View tab) {
        int pos = (int) tab.getTag();
        RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(pos);
        defaultTab();
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mSelectColor);
        //显示delegate
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(pos), ITEM_DELEGATES.get(mCustomDelegate));
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

}
