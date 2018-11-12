package com.blake.usercenter.ui.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.bigkoo.alertview.AlertView
import com.blake.baselibrary.ext.onClick
import com.blake.baselibrary.ui.activity.BaseMvpActivity
import com.blake.usercenter.R
import com.blake.usercenter.injection.component.DaggerUserComponent
import com.blake.usercenter.injection.module.UserModule
import com.blake.usercenter.presenter.UserInfoPresenter
import com.blake.usercenter.presenter.view.UserInfoView
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.utils.DateUtils
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import permissions.dispatcher.*
import java.io.File

@RuntimePermissions
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener,
    TakePhoto.TakeResultListener {
    lateinit var mTakePhoto: TakePhoto

    override fun onClick(v: View) {
//        when (v.id) {
//
//            else -> return
//        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(activityComponent)
            .userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        initView()
        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)
    }

    private fun initView() {
        mUserIconIv.onClick {
            showAlertViewWithPermissionCheck()
        }
    }

    @NeedsPermission(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun showAlertView() {
        AlertView.Builder().setContext(this)
            .setStyle(AlertView.Style.ActionSheet)
            .setTitle("选择图片")
            .setMessage(null)
            .setCancelText("取消")
            .setDestructive("拍照", "从相册中选择")
            .setOthers(null)
            .setOnItemClickListener { o, position ->
                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), true)
                when (position) {
                    0 -> {
                        val tempFile = createTempFile()
                        mTakePhoto.onPickFromCapture(Uri.fromFile(tempFile))
                    }
                    1 -> mTakePhoto.onPickFromGallery()
                }
            }.build()
            .show()
    }

    private fun createTempFile(): File {
        val tempFileName = "${DateUtils.curTime}.png"
        val tempFile: File
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            tempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
        } else {
            tempFile = File(filesDir, tempFileName)
        }
        return tempFile
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @OnShowRationale(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun showRationaleForCamera(request: PermissionRequest) {
        request.proceed()
//        AlertDialog.Builder(this)
//            .setPositiveButton("同意") { _, _ -> request.proceed() }
//            .setNegativeButton("拒绝") { _, _ -> request.cancel() }
//            .setCancelable(false)
//            .setMessage("拍照需要取得相机权限，是否授予？")
//            .show()
    }

    @OnPermissionDenied(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun onCameraDenied() {
        toast("权限被拒绝，请前往开启")
    }

    override fun takeSuccess(result: TResult?) {
        println(result?.image?.compressPath)
        println(result?.image?.originalPath)
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        println("TakeFailure :$msg")
    }
}
