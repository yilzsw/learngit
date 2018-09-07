package com.example.mvp.demo.common.observer

/**
 * datetime：18-9-7
 * author：yilz
 */
class ResultThrowable : Throwable {
    var resultCode: Int

    constructor(message: String?, cause: Throwable?, resultCode: Int) : super(message, cause) {
        this.resultCode = resultCode
    }

    constructor(message: String?, resultCode: Int) : super(message) {
        this.resultCode = resultCode
    }

    constructor(cause: Throwable?, resultCode: Int) : super(cause) {
        this.resultCode = resultCode
    }

    constructor(resultCode: Int) : super() {
        this.resultCode = resultCode
    }

    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean, resultCode: Int) : super(message, cause, enableSuppression, writableStackTrace) {
        this.resultCode = resultCode
    }
}