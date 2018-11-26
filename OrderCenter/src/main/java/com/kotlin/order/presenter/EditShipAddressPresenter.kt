package com.kotlin.order.presenter

import com.blake.baselibrary.ext.execute
import com.blake.baselibrary.presenter.BasePresenter
import com.blake.baselibrary.rx.BaseSubscriber
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.presenter.view.EditShipAddressView
import com.kotlin.order.service.ShipAddressService
import javax.inject.Inject

/*
    编辑收货人信息Presenter
 */
class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService


    /*
        添加收货人信息
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        shipAddressService.addShipAddress(shipUserName, shipUserMobile, shipAddress)
            .execute(lifecycleProvider, object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    mView.onAddShipAddressResult(t)
                }
            })

    }

    /*
        修改收货人信息
     */
    fun editShipAddress(address: ShipAddress) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address).execute(lifecycleProvider, object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onEditShipAddressResult(t)
            }
        })

    }


}
