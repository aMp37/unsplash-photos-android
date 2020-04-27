package com.example.unsplashphotos.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.unsplashphotos.R
import com.example.unsplashphotos.adapter.PhotosPagedAdapter
import com.example.unsplashphotos.databinding.PicturesFragmentBindingImpl
import com.example.unsplashphotos.util.PhotosRecyclerViewItemDecroator
import com.example.unsplashphotos.viewmodel.PicturesViewModel


class PicturesFragment : Fragment() {

    companion object {
        fun newInstance() =
            PicturesFragment()
    }

    private lateinit var viewModel: PicturesViewModel

    private lateinit var binding: PicturesFragmentBindingImpl

    private lateinit var mPhotosRecyclerViewAdapter: PhotosPagedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.pictures_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PicturesViewModel::class.java)

        setupBinding()

        mPhotosRecyclerViewAdapter = PhotosPagedAdapter(viewModel)

        viewModel.photosList.observe(this as LifecycleOwner, Observer {
            mPhotosRecyclerViewAdapter.submitList(it)
        })

        viewModel.loadingState.observe(this as LifecycleOwner, Observer {
            Log.d("STATE",it.toString())
        })

        viewModel.navigationCommand.observe(this, Observer {
            when(it){
                is PicturesViewModel.NavigationCommand.To -> findNavController().navigate(it.directions)
            }
        })
        setupRecyclerView()
        }

    fun setupBinding(){
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this as LifecycleOwner
    }

    fun setupRecyclerView(){
        binding.photosRecyclerView.adapter = mPhotosRecyclerViewAdapter
        binding.photosRecyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.photosRecyclerView.addItemDecoration(PhotosRecyclerViewItemDecroator(100,50,50,50))

    }

}
