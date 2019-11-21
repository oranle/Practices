package com.oranle.practices.koin

import com.oranle.practices.login.LoginViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    single<UserRepo> { UserRepoImpl() }

    factory { (type: String) -> UserPresenter(type, get()) }

    viewModel { LoginViewModel() }

}
