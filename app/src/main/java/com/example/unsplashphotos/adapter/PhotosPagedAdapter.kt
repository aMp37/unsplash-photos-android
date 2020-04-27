package com.example.unsplashphotos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashphotos.R
import com.example.unsplashphotos.databinding.PictureRecyclerviewItemBinding
import com.example.unsplashphotos.model.UnsplashPhoto
import com.example.unsplashphotos.viewmodel.PicturesViewModel

class PhotosPagedAdapter(private var mViewModel: PicturesViewModel): PagedListAdapter<UnsplashPhoto, PhotosPagedAdapter.PhotoViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var binding = DataBindingUtil.inflate<PictureRecyclerviewItemBinding>(LayoutInflater.from(parent.context), R.layout.picture_recyclerview_item,parent,false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) = holder.bind(getItem(position)!!,mViewModel)   //TODO error handling

    class PhotoViewHolder(private val mBinding: PictureRecyclerviewItemBinding): RecyclerView.ViewHolder(mBinding.root){
        fun bind(photo: UnsplashPhoto, viewModel: PicturesViewModel) {
            mBinding.also {
                it.image = photo
                it.viewModel = viewModel
                it.executePendingBindings()
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object :DiffUtil.ItemCallback<UnsplashPhoto>(){
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UnsplashPhoto,
                newItem: UnsplashPhoto
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}