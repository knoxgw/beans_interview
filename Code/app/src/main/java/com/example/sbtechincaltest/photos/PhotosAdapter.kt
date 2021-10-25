package com.example.sbtechincaltest.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.sbtechincaltest.data.internal.Photo
import com.example.sbtechincaltest.databinding.ItemPhotoRowBinding

class PhotosAdapter :
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    private var photos: List<Photo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder =
        PhotosViewHolder(
            ItemPhotoRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount() = photos.count()

    fun updateData(photos: List<Photo>) {
        this.photos = photos
        notifyDataSetChanged()
    }

    class PhotosViewHolder(private val binding: ItemPhotoRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            with(binding.photoImageView) {
                Glide.with(context).load(photo.url)
                    .apply(RequestOptions().transform(RoundedCorners(15))).into(this)
            }
            binding.photoTextView.text = photo.title
        }
    }

}