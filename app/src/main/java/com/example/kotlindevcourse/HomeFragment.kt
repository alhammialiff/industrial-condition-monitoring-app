package com.example.kotlindevcourse

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class HomeFragment: Fragment() {

    /* Lifecycle - INIT - onCreateView */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        /* Returns a rendering function to render home_fragment */
        return inflater.inflate(R.layout.home_fragment, container, false)

    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ){

        /* Super */
        super.onViewCreated(view, savedInstanceState)


    }

}