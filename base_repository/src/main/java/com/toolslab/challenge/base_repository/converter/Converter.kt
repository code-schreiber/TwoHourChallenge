package com.toolslab.challenge.base_repository.converter

interface Converter<S, M> {
    fun convert(source: S): M
}
