package com.viceboy.popularmovies.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VB : ViewDataBinding> : Fragment(){

    protected var binding : VB ? = null

    @LayoutRes
    abstract fun layoutRes() : Int

    abstract fun setUpBinding(binding: VB?)
    abstract fun onCreate(binding: VB)
    abstract fun observeLiveData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<VB>(
            inflater,
            layoutRes(),
            container,
            false
        )
        onCreate(binding!!)
        observeLiveData()
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpBinding(binding)
        super.onViewCreated(view, savedInstanceState)
    }
}