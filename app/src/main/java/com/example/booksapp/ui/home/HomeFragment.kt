package com.example.booksapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.booksapp.MainApplication
import com.example.booksapp.R
import com.example.booksapp.common.viewBinding
import com.example.booksapp.common.visible
import com.example.booksapp.data.model.GetBooksResponse
import com.example.booksapp.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), BooksAdapter.BookListener {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewmodel by viewModels<HomeViewModel>()

    private val booksAdapter by lazy { BooksAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBook.adapter = booksAdapter
        viewmodel.getBooks()
        observeData()
    }

    private fun observeData() {
        viewmodel.loadingLiveData.observe(viewLifecycleOwner){
            binding.progressBar.isVisible=it
        }
        viewmodel.booksLiveData.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                booksAdapter.submitList(list)
            } else {
                Snackbar.make(requireView(), "empty list", 1000).show()
            }

        }
        viewmodel.errorMessageLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), it, 1000).show()//normalde bu kısım textViewİmage olamlı

        }
    }


    override fun onBookClick(id: Int) {
        val action = HomeFragmentDirections.homeToDetail(id)
        findNavController().navigate(action)
    }


}





