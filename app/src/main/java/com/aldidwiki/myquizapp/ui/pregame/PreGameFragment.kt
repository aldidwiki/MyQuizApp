package com.aldidwiki.myquizapp.ui.pregame

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.afollestad.materialdialogs.list.listItems
import com.aldidwiki.myquizapp.R
import com.aldidwiki.myquizapp.databinding.FragmentPreGameBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PreGameFragment : Fragment() {
    private var _binding: FragmentPreGameBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<PreGameViewModel>()

    @Inject
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        _binding = FragmentPreGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.edtName.requestFocus()
        binding.btnChangeDifficulties.setOnClickListener {
            val listMenu = listOf("Easy", "Medium", "Hard")
            MaterialDialog(requireContext()).show {
                title(text = "Choose Difficulties")
                listItems(items = listMenu) { _, _, text ->
                    val difficulties = "Difficulties : $text"
                    binding.btnChangeDifficulties.text = difficulties
                }
                lifecycleOwner(viewLifecycleOwner)
            }
        }

        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_preGameFragment_to_gameFragment)
        }

        lifecycleScope.launchWhenCreated {
            prefs.edit { putString("token", viewModel.getToken()?.token) }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
        }
        return true
    }
}