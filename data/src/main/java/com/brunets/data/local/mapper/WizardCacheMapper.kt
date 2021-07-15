package com.brunets.data.local.mapper

import com.brunets.data.local.model.WizardCache
import entities.WizardDomain

class WizardCacheMapper {
    fun listDomainToCache(list: List<WizardDomain>) = list.map { WizardCache(name = it.name,photo = it.photo,age = it.age, description = it.description) }
    fun listCacheToDomain(list: List<WizardCache>) = list.map { WizardDomain(name = it.name,photo = it.photo,age = it.age, description = it.description) }

    fun toDomain(cache: WizardCache): WizardDomain{
        return WizardDomain(
            name = cache.name,photo = cache.photo,age = cache.age, description = cache.description
        )
    }

}