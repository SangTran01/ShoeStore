package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeListFragment : Fragment() {
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // 1
        val bundle = arguments
        if (bundle == null) {
            Log.e("Shoe List", "ShoeListFragmentArgs did not receive")
        }
        // 2
        val args = bundle?.let { ShoeListFragmentArgs.fromBundle(it) }
        var shoe = args?.shoe

        if (shoe != null)
        {
            viewModel.addShoe(shoe)
        }

        var binding: FragmentShoeListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        var view = binding.root


        var fabAddShoe: FloatingActionButton =
            view.findViewById(R.id.floatingActionButtonAddShoeDetail)

        fabAddShoe.setOnClickListener(View.OnClickListener { it ->
            it.findNavController().navigate(R.id.action_shoeListFragment_to_detailFragment)
        })

        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            if (it != null)
            {
                var linearLayout: LinearLayout = view.findViewById(R.id.linearLayoutShoes)

                for (s in viewModel.shoes.value!!) {
                    var textViewName = TextView(context)
                    textViewName.text = s.name
                    textViewName.textSize = 24F
                    textViewName.height = 100

                    linearLayout.addView(textViewName)

                    var textViewSize = TextView(context)
                    textViewSize.text = s.size.toString()
                    textViewSize.textSize = 24F
                    textViewSize.height = 100

                    linearLayout.addView(textViewSize)

                    var textViewCompany = TextView(context)
                    textViewCompany.text = s.company
                    textViewCompany.textSize = 24F
                    textViewCompany.height = 100

                    linearLayout.addView(textViewCompany)

                    var textViewDesc = TextView(context)
                    textViewDesc.text = s.description
                    textViewDesc.textSize = 24F
                    textViewDesc.height = 100

                    linearLayout.addView(textViewDesc)
                }
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }
}