package com.example.sbtechincaltest.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sbtechincaltest.R
import com.example.sbtechincaltest.databinding.FragmentPhotosBinding

class PhotosFragment : Fragment() {

    private val viewModel: PhotosViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentPhotosBinding>(
            inflater, R.layout.fragment_photos, container, false
        ).apply {
            backImageView.setOnClickListener { view?.findNavController()?.navigateUp() }
            photosRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            photosRecyclerView.adapter = PhotosAdapter().also { adapter ->
                subscribeUi(adapter)
            }
        }
        viewModel.fetchPhotos()
        return binding.root
    }

    private fun subscribeUi(adapter: PhotosAdapter) {
        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            adapter.updateData(photos)
        }
    }
}