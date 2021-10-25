package com.example.sbtechincaltest.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sbtechincaltest.R
import com.example.sbtechincaltest.data.internal.RequestState
import com.example.sbtechincaltest.databinding.FragmentPhotosBinding
import com.example.sbtechincaltest.toast

class PhotosFragment : Fragment() {

    private val viewModel: PhotosViewModel by activityViewModels()

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<FragmentPhotosBinding>(
            inflater, R.layout.fragment_photos, container, false
        ).apply {
            backImageView.setOnClickListener { view?.findNavController()?.navigateUp() }
            photosRecyclerView.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            photosRecyclerView.adapter = PhotosAdapter().also { adapter ->
                subscribeUi(adapter)
            }
        }
        viewModel.fetchPhotos()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeUi(adapter: PhotosAdapter) {
        viewModel.requestState.observe(viewLifecycleOwner) { requestState ->
            when (requestState) {
                is RequestState.Success -> {
                    hideSpinner()
                    adapter.updateData(requestState.data)
                }
                is RequestState.Pending -> showSpinner()
                is RequestState.Error -> {
                    hideSpinner()
                    toast(requestState.message)
                }
            }
        }
    }

    private fun showSpinner() {
        binding.progressLoader.visibility = View.VISIBLE
    }

    private fun hideSpinner() {
        binding.progressLoader.visibility = View.GONE
    }

}