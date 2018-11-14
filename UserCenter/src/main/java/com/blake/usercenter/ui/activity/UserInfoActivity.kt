package com.blake.usercenter.ui.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import com.bigkoo.alertview.AlertView
import com.blake.baselibrary.common.BaseConstant
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
import com.kotlin.base.utils.GlideUtils
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import permissions.dispatcher.*
import java.io.File

@RuntimePermissions
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener,
    TakePhoto.TakeResultListener {

    private lateinit var mTakePhoto: TakePhoto
    private val mUploadManager: UploadManager by lazy { UploadManager() }
    private var mLocalFile: String? = null
    private lateinit var mRemoteFile: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        initView()
        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(activityComponent)
            .userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }

    private fun initView() {
        mUserIconIv.onClick {
            showAlertViewWithPermissionCheck()
        }
    }

    override fun onClick(v: View) {
//        when (v.id) {
//
//            else -> return
//        }
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
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            File(Environment.getExternalStorageDirectory(), tempFileName)
        } else {
            File(filesDir, tempFileName)
        }
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

        mLocalFile = result?.image?.compressPath
        mPresenter.getUploadToken()
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        println("TakeFailure :$msg")
    }

    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFile, null, result, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                response?.apply {
                    mRemoteFile = BaseConstant.IMAGE_SERVER_ADDRESS + get("hash")
                    println("fileUri:$mRemoteFile")
                    GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFile, mUserIconIv)
                }
            }
        }, null)
    }
}
