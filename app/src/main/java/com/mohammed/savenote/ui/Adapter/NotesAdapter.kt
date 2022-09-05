package com.mohammed.savenote.ui.Adapter

import android.content.Context
import android.net.wifi.hotspot2.pps.HomeSp
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.model.Notes
import com.mohammed.savenote.R
import com.mohammed.savenote.databinding.ItemNotesBinding
import com.mohammed.savenote.ui.Fragments.homefragmentDirections

class NotesAdapter(val requireContext: Context,var  notesList: List<Notes>) :RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {
    fun filtering(newFilterdList:ArrayList<Notes>){
notesList=newFilterdList
        notifyDataSetChanged()
    }

    class notesViewHolder(val binding:ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
   return notesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context) ,parent,false))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
holder.binding.notesTitle.text = data.title
        holder.binding.notesSubtitle.text=data.subTitle
        holder.binding.notesData.text=data.data
        when(data.priority)
        {
                   "1"->{
                       holder.binding.viewPriority.setBackgroundResource(R.drawable.greendot)
                   }
                   "2"->{
                       holder.binding.viewPriority.setBackgroundResource(R.drawable.yellowdot)
                   }
                   "3"->{     holder.binding.viewPriority.setBackgroundResource(R.drawable.reddot)}

        }









holder.binding.root.setOnClickListener {

val action=homefragmentDirections.actionHomefragment2ToEditenotefragment(data)
   Navigation.findNavController(it).navigate(action)
}
    }

    override fun getItemCount()=notesList.size
}