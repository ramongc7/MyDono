package com.canonicalexamples.myDono.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.canonicalexamples.myDono.R
import com.canonicalexamples.myDono.app.MyDonoApp
import com.canonicalexamples.myDono.databinding.FragmentOngListBinding
import com.canonicalexamples.myDono.util.observeEvent
import com.canonicalexamples.myDono.viewmodels.OngsListViewModel
import com.canonicalexamples.myDono.viewmodels.OngsListViewModelFactory

class OngsListFragment : Fragment() {

    private lateinit var binding: FragmentOngListBinding
    private val viewModel: OngsListViewModel by viewModels {
        val app = activity?.application as MyDonoApp
        OngsListViewModelFactory(app.database, app.webservice)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOngListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = OngsListAdapter(viewModel = viewModel)
        binding.fab.setOnClickListener {
            viewModel.addButtonClicked()
        }

        viewModel.navigate.observeEvent(viewLifecycleOwner) { navigate ->
            if (navigate) {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
    }
}
