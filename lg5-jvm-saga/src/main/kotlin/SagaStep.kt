package com.lg5.spring.saga

interface SagaStep<T> {
    fun process(data: T)

    fun rollback(data: T)
}