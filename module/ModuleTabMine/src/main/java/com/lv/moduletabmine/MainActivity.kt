package com.lv.moduletabmine

import com.car.core.delegate.base.BaseDelegate
import com.car.core.mvp.factory.CreatePresenter
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl
import com.car.core.mvp.view.BaseMvpActivity
import com.car.tabmine.MineDelegate

@CreatePresenter(DefaultPresenterImpl::class)
class MainActivity : BaseMvpActivity<DefaultPresenterImpl>() {

    override fun setRootDelegate(): BaseDelegate? {
        return MineDelegate()
    }

    override fun bindView() {}
}
