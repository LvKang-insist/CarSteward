package com.car.core.utils.time;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.widget.Button;

/**
 * @author 345
 */
public class SetTelCountTimer extends CountDownTimer {
    private static final int TIME_COUNT = 60000;
    private Button btn;
    private String endStrRid;

    /**
     * 参数 millisInFuture 倒计时总时间（如60S，120s等） 参数 countDownInterval 渐变时间（每次倒计1s）
     * <p>
     * 参数 btn 点击的按钮(因为Button是Button子类，为了通用我的参数设置为Button）
     * <p>
     * 参数 endStrRid 倒计时结束后，按钮对应显示的文字
     */
    public SetTelCountTimer(long millisInFuture, long countDownInterval,
                            Button btn, String endStrRid) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
        this.endStrRid = endStrRid;
    }

    /**
     * 参数上面有注释
     */
    public SetTelCountTimer(Button btn, String endStrRid) {
        super(TIME_COUNT, 1000);
        this.btn = btn;
        this.endStrRid = endStrRid;
    }

    public SetTelCountTimer(Button btn) {
        super(TIME_COUNT, 1000);
        this.btn = btn;
        this.endStrRid = "获取验证码";
    }

    public SetTelCountTimer(Button btn, int normalColor, int timingColor) {
        this(btn);
        this.normalColor = normalColor;
        this.timingColor = timingColor;
    }

    private int normalColor = -1;
    private int timingColor = -1;


    /**
     * 计时完毕时触发
     */
    @Override
    public void onFinish() {
        if (normalColor != -1) {
            btn.setTextColor(normalColor);
        }
        btn.setText(endStrRid);
        btn.setEnabled(true);
    }

    /**
     * 计时过程
     * @param millisUntilFinished
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onTick(long millisUntilFinished) {
        btn.setEnabled(false);
        if (timingColor != -1) {
            btn.setTextColor(timingColor);
        }
        btn.setText(millisUntilFinished / 1000 + "s后重发");
    }
}
