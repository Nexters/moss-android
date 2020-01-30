package com.nexters.moss.utils

import android.util.Log
import java.lang.StringBuilder

class DLog {
    companion object {
        private const val TAG = "MOSS"

        fun e(msg: String) {
            if (MossApplication.debugMode)
                Log.e(TAG, buildLogMsg(msg))
        }
        fun d(msg: String) {
            if (MossApplication.debugMode)
                Log.d(TAG, buildLogMsg(msg))
        }
        fun w(msg: String) {
            if (MossApplication.debugMode)
                Log.w(TAG, buildLogMsg(msg))
        }
        fun v(msg: String) {
            if (MossApplication.debugMode)
                Log.v(TAG, buildLogMsg(msg))
        }
        fun i(msg: String) {
            if (MossApplication.debugMode)
                Log.i(TAG, buildLogMsg(msg))
        }

        private fun buildLogMsg(msg: String): String {
            val ste = Thread.currentThread().stackTrace[4]

            val sb = StringBuilder()

            sb.append("[")
            sb.append(ste.fileName.replace(".java", ""))
            sb.append("::")
            sb.append(ste.methodName)
            sb.append("]")
            sb.append(msg)

            return sb.toString()
        }
    }
}