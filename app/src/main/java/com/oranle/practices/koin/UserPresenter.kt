package com.oranle.practices.koin

class UserPresenter(private val type: String, val userRepo: UserRepo) {

    fun sayHi() = "hi,type[$type], ${userRepo.getName()}"

}