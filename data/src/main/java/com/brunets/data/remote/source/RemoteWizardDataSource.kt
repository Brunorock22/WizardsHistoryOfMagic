package com.brunets.data.remote.source

import com.brunets.data.remote.model.WizardPlayload
import responses.ResultRemote

interface RemoteWizardDataSource {
    suspend fun getWizardsPlaylod () : ResultRemote<List<WizardPlayload>>
}