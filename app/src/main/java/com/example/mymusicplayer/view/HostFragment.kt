package com.example.mymusicplayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HostFragment : Fragment() {

    lateinit var viewBinder: HostFragmentBinder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinder = HostFragmentBinder(this)

        return viewBinder.onCreateView(inflater, container)
    }
}
