package com.blake.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import com.blake.baselibrary.common.BaseConstant
import com.blake.baselibrary.utils.AppPrefsUtils
import com.blake.provider.router.RouterPath
import com.eightbitlab.rxbus.Bus

fun isLogined(): Boolean {
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}

fun afterLogin(method: () -> Unit) {
    if (isLogined()) {
        method()
    } else {
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
    }
}