package com.example.kotlindevcourse.exceptionHandler

import android.util.Log


/* This exception handler is created because try and catch is not usable in composables.
*
*  An instance of this will be set in MainActivity onCreate() so that it is applied down
*  to all composables
*
*  */
class ExceptionHandler: Thread.UncaughtExceptionHandler {


    override fun uncaughtException(thread: Thread, error: Throwable) {

        Log.e("[Uncaught Exception", "Caught Exception: $error")

    }

    fun setAsDefaultExceptionHandler(){
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

}