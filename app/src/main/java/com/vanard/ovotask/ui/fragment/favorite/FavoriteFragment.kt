package com.vanard.ovotask.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.vanard.ovotask.R
import com.vanard.ovotask.data.database.db
import com.vanard.ovotask.data.model.Favorite
import com.vanard.ovotask.databinding.FragmentFavoriteBinding
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.toast

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = FavoriteViewModel(requireContext())
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)

        binding.favoriteMovieList.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    @Suppress("UNCHECKED_CAST")
    private fun load(view: View) {
        view.context.db.use {
            val res = select(Favorite.TABLE_FAV)
            val favv = res.parseList(classParser<Favorite>())
            toast(favv.toString())
        }
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): FavoriteFragment {
            return FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}