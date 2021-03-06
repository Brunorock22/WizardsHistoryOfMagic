package com.brunets.pottersworld.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.brunets.pottersworld.R
import com.brunets.pottersworld.databinding.WizardItemBinding
import com.brunets.pottersworld.utils.loadImage
import entities.WizardDomain
import kotlinx.android.synthetic.main.wizard_item.view.*

class WizardsAdapter(private val wizards: List<WizardDomain>): RecyclerView.Adapter<WizardsAdapter.WizardHolder>() {


    var onItemClick: ((WizardDomain) -> Unit)? = null

    inner class WizardHolder(var view: View):RecyclerView.ViewHolder(view){
            val binding: WizardItemBinding? = DataBindingUtil.bind(view)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(wizards[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WizardHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.wizard_item,parent,false)
        return WizardHolder(view)
    }

    override fun getItemCount(): Int = wizards.size

    override fun onBindViewHolder(holder: WizardHolder, position: Int) {
        val binding = (holder as WizardHolder).binding
        holder.itemView.wizard_name.text = wizards[position].name
        holder.itemView.wizard_age.text = wizards[position].age.toString()
        holder.itemView.wizad_photo.loadImage(wizards[position].photo)

    }
}