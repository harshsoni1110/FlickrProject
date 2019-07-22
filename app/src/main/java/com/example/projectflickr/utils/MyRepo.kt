package com.hootsuite.shipmyid.utils

import android.app.Application

class MyRepo (private val application: Application){
    fun getClassName ():String = application.packageCodePath
}