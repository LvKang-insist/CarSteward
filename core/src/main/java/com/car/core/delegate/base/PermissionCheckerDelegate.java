package com.car.core.delegate.base;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.car.core.R;
import com.car.core.utils.camera.CameraImageBean;
import com.car.core.utils.camera.CropPhoto;
import com.car.core.utils.camera.LatteCamera;
import com.car.core.utils.camera.RequestCode;
import com.elvishew.xlog.XLog;
import com.hjq.toast.ToastUtils;

import io.reactivex.annotations.NonNull;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * @author 345 QQ:1831712732
 * @name MvpFrame
 * @class name：com.latte.core.delegate
 * @time 2019/9/26 21:35
 * @description 对权限进行处理
 */

@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

    /**
     * 不是直接调用方法，他仅仅只是为了生成代码而存在的
     */
    @NeedsPermission({Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void startCamera() {
        LatteCamera.start(this);
    }

    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE})
    void initPermission() {
        XLog.e("权限获取成功");
    }

    @NeedsPermission({Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE})
    void initPsermissionDenied() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                (dialog, which) -> {
                    dialog.cancel();
                });

        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                        dialog.cancel();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    public void startInitPermission() {
        PermissionCheckerDelegatePermissionsDispatcher.initPermissionWithPermissionCheck(this);
    }


    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCamerDenied() {
        Toast.makeText(getContext(), "不允许拍照", Toast.LENGTH_SHORT).show();
    }

    void onCamerNever() {
        Toast.makeText(getContext(), "永久拒绝权限", Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale({Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onCameraReational(PermissionRequest request) {
        showRetionaeDialog(request);
    }

    private void showRetionaeDialog(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setPositiveButton("同意使用", (dialog, which) -> request.proceed())
                .setNegativeButton("拒绝使用", (dialog, which) -> request.cancel())
                .setCancelable(false)
                .setMessage("权限管理")
                .show();
    }

    /**
     * Rebuild 之后会生成一个辅助类，用来调用被注解的Activity的方法，所以，第一次使用的话
     * 注解添加完后 要Rebuild 一次。否则不能生成辅助类。
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] data) {
        super.onRequestPermissionsResult(requestCode, permissions, data);
        PermissionCheckerDelegatePermissionsDispatcher
                .onRequestPermissionsResult(this, requestCode, data);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //相机回调
                case RequestCode.TAKE_PHOTO:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    CropPhoto.cropPhoto(this, true, resultUri);
                    break;
                case RequestCode.PICK_PHOTO:
                    if (data != null) {
                        final Uri pickPath = data.getData();
                        CropPhoto.cropPhoto(this, false, pickPath);
                    }
                    break;
                case RequestCode.CROP_ERROR:
                    ToastUtils.show("裁剪图片");
                    XLog.e(CameraImageBean.getInstance().getPath());
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getContext().getPackageName()));
        startActivity(intent);
    }
}
