package com.mohammed.savenote.ui.Fragments

import android.os.Bundle
import android.provider.ContactsContract
import android.view.*
import android.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation

import androidx.recyclerview.widget.GridLayoutManager

import com.ViewModel.NotesView
import com.model.Notes
import com.mohammed.savenote.R
import com.mohammed.savenote.databinding.FragmentHomeBinding
import com.mohammed.savenote.ui.Adapter.NotesAdapter


class homefragment : Fragment() {
    lateinit var fav: MenuItem
    val viewModel : NotesView by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter:NotesAdapter
lateinit var binding: FragmentHomeBinding
    


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false )
        viewModel.getNotes().observe(viewLifecycleOwner,{notesList ->
            oldMyNotes=notesList as ArrayList<Notes>
binding.rcvAllnote.layoutManager=GridLayoutManager(requireContext(),2)
            adapter=NotesAdapter(requireContext(),notesList)
            binding.rcvAllnote.adapter=adapter







        })
        binding.btnAdd.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homefragment2_to_createnotefragment2)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_options, menu)
        inflater.inflate(R.menu.search_menu, menu)

        val item=menu.findItem(R.id.search_sra)
        val searchview1 =item.actionView as SearchView
        searchview1.queryHint="Enter Notes Here ..."
        searchview1.setOnQueryTextListener(object:SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFiltering(newText)
return true

            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun NotesFiltering(newText:String?) {
val newFilterdList= arrayListOf<Notes>()
for (i in oldMyNotes){
    if(i.title.contains(newText!!) || i.subTitle.contains(newText!!)){
        newFilterdList.add(i)
    }
}

adapter.filtering(newFilterdList)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val result = when(item.itemId){

      R.id.red2->{
          viewModel.getHighNotes().observe(
              viewLifecycleOwner,
              { notesList ->
                  oldMyNotes=notesList as ArrayList<Notes>
                  binding.rcvAllnote.layoutManager = GridLayoutManager(requireContext(), 2)
                  binding.rcvAllnote.adapter = NotesAdapter(requireContext(), notesList)

              })
true
      }
            R.id.green2->{
                viewModel.getLowNotes().observe(
                    viewLifecycleOwner,
                    { notesList ->
                        oldMyNotes=notesList as ArrayList<Notes>
                        binding.rcvAllnote.layoutManager = GridLayoutManager(requireContext(), 2)
                        adapter=NotesAdapter(requireContext(),notesList)
                        binding.rcvAllnote.adapter = adapter

                    })
                true
            }
            R.id.yellow2->{
                viewModel.getMediumNotes().observe(
                    viewLifecycleOwner,
                    { notesList ->
                        oldMyNotes=notesList as ArrayList<Notes>
                        binding.rcvAllnote.layoutManager = GridLayoutManager(requireContext(), 2)
                        adapter=NotesAdapter(requireContext(),notesList)
                        binding.rcvAllnote.adapter =adapter

                    })
                true
            }
            R.id.allNotes->{
                viewModel.getNotes().observe(
                    viewLifecycleOwner,
                    { notesList ->
                        oldMyNotes=notesList as ArrayList<Notes>
                        binding.rcvAllnote.layoutManager = GridLayoutManager(requireContext(), 2)
                        adapter=NotesAdapter(requireContext(),notesList)
                        binding.rcvAllnote.adapter = adapter

                    })
                true
            }



            else -> {
               false
            }
        }



        return super.onOptionsItemSelected(item)
    }



}


