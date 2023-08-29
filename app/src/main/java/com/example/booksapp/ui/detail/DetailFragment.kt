package com.example.booksapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.booksapp.R
import com.example.booksapp.common.gone
import com.example.booksapp.common.loadImage
import com.example.booksapp.common.viewBinding
import com.example.booksapp.common.visible
import com.example.booksapp.databinding.FragmentDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBooksDetail(args.id)
        observeData()
    }

    private fun observeData() = with(binding) {
       viewModel.loadingLiveData.observe(viewLifecycleOwner){
           binding.progressBar.isVisible=it
       }
        viewModel.booksDetailLiveData.observe(viewLifecycleOwner) { book ->
            if (book != null) {
                textName.text = book.name
                textAuthor.text = book.author
                textPublisher.text = book.publisher
                textPrice.text = "${book.price} ₺"
                imageBook.loadImage(book.imageUrl)
                if (book.bestSeller == true){
                    bestSellerBadgeD.visible()
                }else{
                     bestSellerBadgeD.gone()
                }

            } else {
                Snackbar.make(requireView(), "Book not found!", 1000).show()

            }
        }
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), it, 1000).show()
        }

    }


}
