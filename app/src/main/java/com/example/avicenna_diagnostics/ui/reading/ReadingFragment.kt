package com.example.avicenna_diagnostics.ui.reading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.avicenna_diagnostics.databinding.FragmentReadingBinding

class AdvancedFragment : Fragment() {

    private var _binding: FragmentReadingBinding? = null
    private val binding get() = _binding!!
    private val advancedViewModel: ReadingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReadingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val readingTextView: TextView = binding.readingTextView

        advancedViewModel.readingAtSixPointFive.observe(viewLifecycleOwner, Observer { reading ->
            readingTextView.text = "$reading mmol/L"
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
