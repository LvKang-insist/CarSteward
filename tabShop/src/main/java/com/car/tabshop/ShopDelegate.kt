package com.car.tabshop

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.car.core.delegate.BottomItemDelegate
import com.car.core.mvp.factory.CreatePresenter
import com.car.core.mvp.mvpdefault.DefaultContract
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl
import com.hjq.toast.ToastUtils
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.properties.Delegates

/**
 * @author 345 QQ:1831712732
 * @name CarSteward
 * @class name：com.car.tabshop
 * @time 2019/10/7 22:59
 * @description
 */
@CreatePresenter(DefaultPresenterImpl::class)
class ShopDelegate : BottomItemDelegate<DefaultPresenterImpl>(), DefaultContract.IDefaultView {

    var btn: AppCompatButton by Delegates.notNull()

    var X = 2
    var ShopDelegate.Sex: Int
        get() {
            return X
        }
        set(value) {
            X = value
        }

    override fun setLayout(): Any {
        return R.layout.shop_delegate
    }

    override fun bindView   (view: View) {
        //        getSupportDelegate().loadRootFragment(R.id.delegate_shop_layout,
        //                BaseShopListDelegate.newInstance(CarPreference.getMyCar(), BusinessScope.BUSINESSSCOPE_SHOP_LIST));

        val iv = view.findViewById<AppCompatImageView>(R.id.delegate_shop_iv)
        btn = view.findViewById(R.id.delegate_shop_btn)

        btn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val bitmap = getImage()
                get()
                iv.setImageBitmap(bitmap)
            }
            ToastUtils.show("哈哈哈哈")
        }
    }

    private suspend fun getImage(): Bitmap = withContext(Dispatchers.IO) {
        Thread.sleep(3000)
        OkHttpClient().newCall(Request.Builder()
                .url("https://dss0.bdstatic.com/6Ox1bjeh1BF3odCf/it/u=4256581120,3161125441&fm=193")
                .get()
                .build())
                .execute().body()?.byteStream().use {
                    BitmapFactory.decodeStream(it)
                }
    }

    suspend fun get() {
        delay(5)
    }

    override fun onResult(result: String?) {

    }
}
