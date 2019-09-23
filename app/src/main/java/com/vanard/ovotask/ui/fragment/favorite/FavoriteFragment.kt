package com.vanard.ovotask.ui.fragment.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment

class FavoriteFragment : Fragment() {
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