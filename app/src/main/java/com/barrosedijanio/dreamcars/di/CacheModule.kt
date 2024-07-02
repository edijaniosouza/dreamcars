package com.barrosedijanio.dreamcars.di

import com.barrosedijanio.dreamcars.cache.CacheRepository
import org.koin.dsl.module

val cacheModule = module {
    single<CacheRepository> {
        CacheRepository(get(), get())
    }
}