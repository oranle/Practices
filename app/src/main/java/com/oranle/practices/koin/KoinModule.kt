package com.oranle.practices.koin

import org.koin.dsl.module.module

val appModule = module {

    single<UserRepo> { UserRepoImpl() }

    factory { (type: String) -> UserPresenter(type, get()) }

}
