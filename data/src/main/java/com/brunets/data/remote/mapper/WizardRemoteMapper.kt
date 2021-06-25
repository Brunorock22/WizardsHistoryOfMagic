package com.brunets.data.remote.mapper

import com.brunets.data.remote.model.WizardPlayload
import entities.WizardDomain

object WizardRemoteMapper {

    fun map(payload: WizardPlayload) = WizardDomain(
        name = payload.name,
        description = payload.description,
        photo = payload.photo,
        age = payload.age
    )

    fun mapList(list: List<WizardPlayload>) = list.map {WizardDomain(name = it.name,photo = it.photo,age = it.age, description = it.description) }
}