package com.brunets.pottersworld

import com.brunets.pottersworld.ui.adapter.WizardsAdapter
import entities.WizardDomain
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class WizardAdapterTest {

    @Test
    fun `test click adapter`(){
     lateinit var adapter: WizardsAdapter
        val list = listOf(WizardDomain("Harry Potter", "", 0, ""))
        adapter = WizardsAdapter(list)

        adapter.onItemClick = {
            assert(it != WizardDomain("Hermione", "", 0, ""))
        }
        adapter.onItemClick!!.invoke(list.first())
    }
}