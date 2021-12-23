package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.udacity.shoestore.models.Shoe

class DetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_detail, container, false)

        var cancelBtn: Button = view.findViewById(R.id.buttonCancel)
        cancelBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_detailFragment_to_shoeListFragment)
        }

        var submitBtn: Button = view.findViewById(R.id.buttonSubmit)
        submitBtn.setOnClickListener {

            var shoe = Shoe("Name",10.0,"Comp","Desc")

            val directions = DetailFragmentDirections.actionDetailFragmentToShoeListFragment(shoe)

            view.findNavController().navigate(directions)
        }
        return view
    }
}