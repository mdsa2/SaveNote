package com.mohammed.savenote.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.ViewModel.NotesView
import com.model.Notes
import com.mohammed.savenote.R
import com.mohammed.savenote.databinding.FragmentCreatenotefragmentBinding
import java.lang.String.format

import java.util.*

class createnotefragment : Fragment() {
    lateinit var binding:FragmentCreatenotefragmentBinding
     var priority:String="1"
    val viewModel : NotesView by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCreatenotefragmentBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        binding.greenDot.setImageResource(R.drawable.ic_baseline_done_24)
binding.greenDot.setOnClickListener {
    priority="1"
    binding.greenDot.setImageResource(R.drawable.ic_baseline_done_24)
    binding.redDot.setImageResource(0)
    binding.yellowDot.setImageResource(0)
}
        binding.yellowDot.setOnClickListener {
            priority="2"
            binding.yellowDot.setImageResource(R.drawable.ic_baseline_done_24)
            binding.redDot.setImageResource(0)
            binding.greenDot.setImageResource(0)
        }
        binding.redDot.setOnClickListener {
            priority="3"
            binding.redDot.setImageResource(R.drawable.ic_baseline_done_24)
            binding.greenDot.setImageResource(0)
            binding.yellowDot.setImageResource(0)
        }
binding.btnsavenote.setOnClickListener {
    createNotes(it)
}

        return binding.root
    }

    private fun createNotes(it: View?) {
      val title =   binding.EditTitle.text.toString()
        val subTitle=binding .Editsub.text.toString()
        val notes =binding.Editnotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format ("MMMM d, yyyy ", d.time)
        val data = Notes(null,
            title = title,
            subTitle=subTitle,
        notes =notes,
            data = notesDate.toString(),
        priority)
viewModel.addNotes(data)
        Toast.makeText(getActivity(),"Notes Created Succesfuly",Toast.LENGTH_SHORT ).show()
Navigation.findNavController(it!!).navigate(R.id.action_createnotefragment2_to_homefragment2)
    }
}


