package dev.suhockii.lifetest.util.rx_transformers

import io.reactivex.Scheduler
import io.reactivex.SingleTransformer

abstract class RxSchedulers {

    abstract val mainThreadScheduler: Scheduler
    abstract val ioScheduler: Scheduler
    abstract val computationScheduler: Scheduler

    open fun <T> getIoToMainTransformerSingle(): SingleTransformer<T, T> {
        return SingleTransformer { objectObservable ->
            objectObservable
                .subscribeOn(ioScheduler)
                .observeOn(mainThreadScheduler)
        }
    }
}