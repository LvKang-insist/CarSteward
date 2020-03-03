package com.car.tabshop

import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.car.core.delegate.BottomItemDelegate
import com.car.core.mvp.factory.CreatePresenter
import com.car.core.mvp.mvpdefault.DefaultContract
import com.car.core.mvp.mvpdefault.DefaultPresenterImpl
import com.elvishew.xlog.XLog
import com.hjq.toast.ToastUtils
import kotlinx.android.synthetic.main.shop_delegate.*
import kotlinx.coroutines.*
import okhttp3.*
import java.io.IOException
import java.lang.NullPointerException
import kotlin.coroutines.*
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

    private var btn: AppCompatButton by Delegates.notNull()

    override fun setLayout(): Any {
        return R.layout.shop_delegate
    }

    override fun bindView(view: View) {
        //        getSupportDelegate().loadRootFragment(R.id.delegate_shop_layout,
        //                BaseShopListDelegate.newInstance(CarPreference.getMyCar(), BusinessScope.BUSINESSSCOPE_SHOP_LIST));

        suspend {

        }.createCoroutine(object : Continuation<Unit> {
            override val context = EmptyCoroutineContext
            override fun resumeWith(result: Result<Unit>) {

            }
        }).resume(Unit)


        val iv = view.findViewById<AppCompatImageView>(R.id.delegate_shop_iv)
        btn = view.findViewById(R.id.delegate_shop_btn)

        btn.setOnClickListener {

            val animator = ObjectAnimator.ofFloat(testGroup, "HeightX", 50f,-50f)
                    .setDuration(1000).start()

//            CoroutineScope(Dispatchers.Main).launch {
//                val bitmap = getImage()
//                println(foo("345"))
//                iv.setImageBitmap(bitmap)
//            }
//            ToastUtils.show("哈哈哈哈")
        }
        testGroup.setOnClickListener{
            ToastUtils.show("嘻嘻嘻")
        }
    }

    private suspend fun getImage() = suspendCoroutine<Bitmap> { continuation ->
        OkHttpClient().newCall(Request.Builder()
                .url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581869591580&di=e0412feb1e101a144e416f7a873bd88d&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F6febb183087736d089b6583a790c491f2dc7469a.jpg")
                .get()
                .build())
                .enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        continuation.resumeWithException(e)
                    }

                    override fun onResponse(call: Call, response: Response) {
                        continuation.resume(response.body()?.byteStream().use { BitmapFactory.decodeStream(it) })
                    }
                })
    }

    suspend fun foo(name: String) = suspendCoroutine<String> { continuation ->
        if (name.isEmpty()) {
            continuation.resumeWithException(NullPointerException("为空"))
        } else {
            continuation.resume(name)
        }
    }


    override fun onResult(result: String?) {

    }
}
