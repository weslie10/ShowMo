package com.weslie10.showmo.data

import java.util.concurrent.Executor

class TestExecutor : Executor {
    override fun execute(runnable: Runnable) {
        runnable.run()
    }
}