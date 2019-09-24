package com.vanard.ovotask.ui.fragment.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vanard.ovotask.R
import com.vanard.ovotask.databinding.FragmentTopRatedBinding
import com.vanard.ovotask.injection.ViewModelFactory

class TopRatedFragment : Fragment() {

    private lateinit var binding: FragmentTopRatedBinding
    private lateinit var viewModel: TopRatedListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_rated, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(requireActivity() as AppCompatActivity)).get(TopRatedListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

        binding.topRatedMovieList.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun showError(errorMessage:String){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): TopRatedFragment {
            return TopRatedFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}