// Generated code from Butter Knife. Do not modify!
package com.car.ui.delegate.shoplist;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.car.ui.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseShopListDelegate_ViewBinding implements Unbinder {
  private BaseShopListDelegate target;

  private View view7f0b0046;

  private View view7f0b004c;

  private View view7f0b004f;

  private View view7f0b0049;

  @UiThread
  public BaseShopListDelegate_ViewBinding(final BaseShopListDelegate target, View source) {
    this.target = target;

    View view;
    target.mCityTv = Utils.findRequiredViewAsType(source, R.id.base_delegate_shop_select_city_tv, "field 'mCityTv'", AppCompatTextView.class);
    target.mCityLine = Utils.findRequiredView(source, R.id.base_delegate_shop_select_city_line, "field 'mCityLine'");
    target.mServiceLine = Utils.findRequiredView(source, R.id.base_delegate_shop_select_service_line, "field 'mServiceLine'");
    target.mSortLine = Utils.findRequiredView(source, R.id.base_delegate_shop_select_sort_line, "field 'mSortLine'");
    target.mFiltrateLine = Utils.findRequiredView(source, R.id.base_delegate_shop_select_filtrate_line, "field 'mFiltrateLine'");
    target.mRefreshLayout = Utils.findRequiredViewAsType(source, R.id.base_delegate_shop_refresh, "field 'mRefreshLayout'", SwipeRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.base_delegate_shop_select_city, "method 'selectClick'");
    view7f0b0046 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.selectClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.base_delegate_shop_select_service, "method 'selectClick'");
    view7f0b004c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.selectClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.base_delegate_shop_select_sort, "method 'selectClick'");
    view7f0b004f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.selectClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.base_delegate_shop_select_filtrate, "method 'selectClick'");
    view7f0b0049 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.selectClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    BaseShopListDelegate target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mCityTv = null;
    target.mCityLine = null;
    target.mServiceLine = null;
    target.mSortLine = null;
    target.mFiltrateLine = null;
    target.mRefreshLayout = null;

    view7f0b0046.setOnClickListener(null);
    view7f0b0046 = null;
    view7f0b004c.setOnClickListener(null);
    view7f0b004c = null;
    view7f0b004f.setOnClickListener(null);
    view7f0b004f = null;
    view7f0b0049.setOnClickListener(null);
    view7f0b0049 = null;
  }
}
