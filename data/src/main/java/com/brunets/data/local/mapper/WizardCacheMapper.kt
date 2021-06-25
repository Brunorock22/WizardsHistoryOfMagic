package com.brunets.data.local.mapper

import com.brunets.data.local.model.WizardCache
import entities.WizardDomain

class WizardCacheMapper {
    fun mapList(list: List<WizardDomain>) = list.map { WizardCache(name = it.name,photo = it.photo,age = it.age, description = it.description) }

}