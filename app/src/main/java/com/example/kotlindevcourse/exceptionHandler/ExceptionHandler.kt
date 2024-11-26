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

        /* To accomodate long message */
        if(error.message?.length!! > 100){

            var chunkStart = 0;
            var chunkEnd = 100;
            var chunkLeft = error.message?.length;
            var chunkRead = 100;

            if (chunkLeft != null) {

                while(chunkLeft > 0){

                    /* For final batch of chunks which could be less than the chunkEnd val */
                    if(chunkEnd > error.message?.length!!){

                        /* Define chunkEnd to take the value of message length minus 1*/
                        chunkEnd = error.message?.length!!;

                    }

                    error.message?.substring(chunkStart,chunkEnd-1)?.let {
                        Log.d("[Caught Exception]", it)
                    };

                    /* Move chunk window by 100*/
                    chunkStart += 100;
                    chunkEnd += 100;

                    /* Minus Chunk Read */
                    chunkLeft -= chunkRead;

                }
            }



        }else{


            Log.d("[Caught Exception]", error.message.toString())

        }

        Log.d("[Caught Exception]", error.stackTraceToString())



    }

    fun setAsDefaultExceptionHandler(){
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

}