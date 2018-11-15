package com.blake.provider.common

import com.blake.baselibrary.common.BaseConstant
import com.blake.baselibrary.utils.AppPrefsUtils

fun isLogined(): Boolean {
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}