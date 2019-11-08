package com.oranle.practices.login

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oranle.practices.MainActivity
import com.oranle.practices.R
import com.oranle.practices.base.BaseViewModel
import com.oranle.practices.base.view.WheelView
import com.oranle.practices.base.view.WheelView.OnWheelViewListener
import com.oranle.practices.data.UserInfo
import com.oranle.practices.generated.callback.OnClickListener
import com.oranle.practices.refresh.RecyclerViewActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class LoginViewModel : BaseViewModel() {

    val userName = MutableLiveData<String>("zhangsan")

    val password = MutableLiveData<String>("123")

    val remember = MutableLiveData<Boolean>(true)

    val isShowProgress = MutableLiveData<Boolean>(false)

    override fun start(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val users = getDB(context).getUserDao().getUsers()
            if (users.isNotEmpty()) {
                withContext(UI) {
                    kotlinx.coroutines.delay(500)
                    users[users.size - 1]?.also {
                        if (it.remember) {
                            userName.value = it.userName
                            password.value = it.password
                            remember.value = it.remember
                        }
                    }
                }
            }
        }
    }

    override fun onClick(view: View) {
        super.onClick(view)

        Timber.v("onclick ${view.id}")

        when (view.id) {
            R.id.login -> {
                Timber.v("on click login")

                viewModelScope.launch(UI) {
                    isShowProgress.value = true

                    kotlinx.coroutines.delay(1000)

                    if (userName.value != null && password.value != null && remember.value != null) {
                        withContext(IO) {
                            getDB(view.context).getUserDao().insertUser(
                                UserInfo(
                                    userName = userName.value!! + "xcy",
                                    password = password.value!!,
                                    headUrl = "http://www.baidu.com",
                                    phoneNum = userName.value!!,
                                    remember = remember.value!!
                                )
                            )
                        }
                        isShowProgress.value = false

                        Toast.makeText(view.context, "登录成功", Toast.LENGTH_SHORT).show()

                        kotlinx.coroutines.delay(1000)

                        view.context.startActivity(
                            Intent(
                                view.context,
                                RecyclerViewActivity::class.java
                            )
                        )

                        start(view.context)
                    }
                }
            }
            R.id.register -> {
                Timber.v("on click register")
            }
            R.id.dialog -> {
                Timber.v("on click dialog")
                val list = MutableList(20) {
                    index: Int ->
                    "person__$index"
                }

                showChoiceDialog(view.context, list, 0, OnWheelViewListener { index, item ->
                    Timber.v("on click dialog $index, $item")
                })

            }
        }
    }

    fun showChoiceDialog(
        context: Context,
        dataList: MutableList<String>, selected: Int,
        listener: OnWheelViewListener
    ) {
        val outerView = LayoutInflater.from(context).inflate(R.layout.dialog_wheelview, null);

        // 显示对话框，点击确认后将所选项的值显示到Button上
        val alertDialog = AlertDialog.Builder(context)
            .setView(outerView)
            .create()
        val window = alertDialog.window
        window?.setGravity(Gravity.LEFT and Gravity.BOTTOM)
        val attributes = window?.attributes
        attributes?.x = 0
        attributes?.y = 0
        window?.attributes = attributes

        alertDialog.show()

        val wheelView = outerView.findViewById<WheelView>(R.id.wheel_view);
        wheelView.offset = 2;// 对话框中当前项上面和下面的项数
        wheelView.setItems(dataList);// 设置数据源
        wheelView.setSeletion(selected);// 默认选中第三项
        wheelView.onWheelViewListener = listener
    }

    fun onLogin() {


    }

    fun saveUser() {

    }


}