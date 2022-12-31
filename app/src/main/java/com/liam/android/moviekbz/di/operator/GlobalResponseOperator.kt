package com.liam.android.moviekbz.di.operator

import android.app.Application
import android.os.Looper
import android.util.Log
import androidx.annotation.WorkerThread
import com.skydoves.sandwich.*
import com.skydoves.sandwich.operators.ApiResponseSuspendOperator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GlobalResponseOperator<T> constructor(
    private val application: Application
) : ApiResponseSuspendOperator<T>() {

    override suspend fun onSuccess(apiResponse: ApiResponse.Success<T>) = Unit

    override suspend fun onError(apiResponse: ApiResponse.Failure.Error<T>) =
        withContext(Dispatchers.IO) {
            apiResponse.run {
                Log.d("onError",message())

                when (statusCode) {
                    StatusCode.InternalServerError -> toast("InternalServerError")
                    StatusCode.BadGateway -> toast("BadGateway")
                    else -> toast("$statusCode(${statusCode.code}): ${message()}")
                }

                map(ErrorResponseMapper) {
                    android.util.Log.d("onError",message())
                }
            }
        }

    override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<T>) =
        withContext(Dispatchers.Main) {
            apiResponse.run {
                Log.d("onException",message())
                toast(message())
            }
        }



    @WorkerThread
    private fun toast(message: String) {
        android.os.Handler(Looper.getMainLooper()).post {
            //Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
        }
    }
}

object ErrorResponseMapper : ApiErrorModelMapper<MovieErrorResponse> {
    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): MovieErrorResponse {
        return MovieErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}