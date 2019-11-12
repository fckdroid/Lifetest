package dev.suhockii.lifetest.util.rx_transformers

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer

abstract class RxSchedulers {

    abstract val mainThreadScheduler: Scheduler
    abstract val ioScheduler: Scheduler
    abstract val computationScheduler: Scheduler

    fun <T> getIoToMainTransformerObservable(): ObservableTransformer<T, T> {
        return ObservableTransformer { objectObservable ->
            objectObservable
                .subscribeOn(ioScheduler)
                .observeOn(mainThreadScheduler)
        }
    }

    fun <T> getIoToMainTransformerCompletable(): CompletableTransformer {
        return CompletableTransformer { transformerCompletable ->
            transformerCompletable
                .subscribeOn(ioScheduler)
                .observeOn(mainThreadScheduler)
        }
    }

    fun <T> getIoToMainTransformerSingle(): SingleTransformer<T, T> {
        return SingleTransformer { objectObservable ->
            objectObservable
                .subscribeOn(ioScheduler)
                .observeOn(mainThreadScheduler)
        }
    }

    fun <T> getMainToIoTransformerSingle(): SingleTransformer<T, T> {
        return SingleTransformer { objectObservable ->
            objectObservable
                .subscribeOn(mainThreadScheduler)
                .observeOn(ioScheduler)
        }
    }

    fun <T> getComputationToMainTransformer(): ObservableTransformer<T, T> {
        return ObservableTransformer { objectObservable ->
            objectObservable
                .subscribeOn(computationScheduler)
                .observeOn(mainThreadScheduler)
        }
    }

    fun <T> getComputationToMainTransformerSingle(): SingleTransformer<T, T> {
        return SingleTransformer { objectObservable ->
            objectObservable
                .subscribeOn(computationScheduler)
                .observeOn(mainThreadScheduler)
        }
    }

}