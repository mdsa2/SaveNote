package com.mohammed.savenote.ui.Fragments

import android.os.Bundle

import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation

import androidx.navigation.fragment.navArgs
import com.ViewModel.NotesView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.model.Notes
import com.mohammed.savenote.R
import com.mohammed.savenote.databinding.FragmentEditenotefragmentBinding
import java.util.*

class editenotefragment : Fragment() {
    val oldNotes by navArgs<editenotefragmentArgs>()
    val viewModel : NotesView by viewModels()
    var priority:String ="1"
    lateinit var binding:FragmentEditenotefragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentEditenotefragmentBinding.inflate(layoutInflater,container,false)

        // Inflate the layout for this fragment
setHasOptionsMenu(true)
        binding.EditTitle.setText(oldNotes.data.title)
        binding.Editsub.setText(oldNotes.data.subTitle)
        binding.Editnotes.setText(oldNotes.data.notes)
        when(oldNotes.data.priority)
        {
            "1"->{
                priority="1"
                binding.greenDot.setImageResource(R.drawable.ic_baseline_done_24)
                binding.redDot.setImageResource(0)
                binding.yellowDot.setImageResource(0)
            }
            "2"->{
                priority="2"
                binding.yellowDot.setImageResource(R.drawable.ic_baseline_done_24)
                binding.redDot.setImageResource(0)
                binding.greenDot.setImageResource(0)
            }
            "3"->{
                priority="3"
                binding.redDot.setImageResource(R.drawable.ic_baseline_done_24)
                binding.greenDot.setImageResource(0)
                binding.yellowDot.setImageResource(0)
            }





        }
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
binding.btnEditsavenote.setOnClickListener {
    updateNotes(it)
}

        return binding.root
    }



    private fun updateNotes(it: View?) {
        val title =   binding.EditTitle.text.toString()
        val subTitle=binding .Editsub.text.toString()
        val notes =binding.Editnotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format ("MMMM d, yyyy ", d.time)
        val data = Notes(oldNotes.data.id  ,
            title = title,
            subTitle=subTitle,
            notes =notes,
            data = notesDate.toString(),
            priority)

        viewModel.addNotes(data)
        Toast.makeText(requireContext(),"Notes Update  Succesfuly", Toast.LENGTH_SHORT ).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editenotefragment_to_homefragment2)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delet,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.delete)
        {
val bottomSheet:BottomSheetDialog= BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dailog_delet)

val textviewyes=bottomSheet.findViewById<TextView>(R.id.yes_btn)
            val textviewNo=bottomSheet.findViewById<TextView>(R.id.no_btn)
            textviewyes?.setOnClickListener {


 viewModel.deletNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
                view?.let { Navigation.findNavController(it).navigate(R.id.action_editenotefragment_to_homefragment2) }
             


            }
            textviewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }




}
