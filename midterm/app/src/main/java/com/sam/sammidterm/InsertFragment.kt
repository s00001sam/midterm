package com.sam.sammidterm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sam.sammidterm.databinding.FragmentInsertBinding

/**
 * A simple [Fragment] subclass.
 */
class InsertFragment : DialogFragment() {

    private val viewModel: InsertViewModel by lazy {
        ViewModelProvider(this).get(InsertViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.LoginDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentInsertBinding>(inflater,
            R.layout.fragment_insert, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        binding.buttonInsert.setOnClickListener {
            viewModel.addData(viewModel.enterTitle.value.toString(), viewModel.enterTag.value.toString(),
                viewModel.enterContent.value.toString())
            (activity as MainActivity).getAll()

            findNavController().navigateUp()
        }

        viewModel.leave.observe(viewLifecycleOwner, Observer {
            it?.let {
                dismiss()
                viewModel.onLeaveCompleted()
            }
        })

        return binding.root
    }

}
