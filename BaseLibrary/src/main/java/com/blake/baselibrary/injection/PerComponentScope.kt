package com.blake.baselibrary.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * Create by Pidan
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class PerComponentScope