package com.aldidwiki.myquizapp.ui.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldidwiki.myquizapp.adapter.LeaderBoardAdapter
import com.aldidwiki.myquizapp.databinding.FragmentLeaderboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LeaderBoardFragment : Fragment() {
    private val viewModel: LeaderBoardViewModel by viewModels()
    private var _binding: FragmentLeaderboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var leaderBoardActivity: AppCompatActivity

    @Inject
    lateinit var leaderBoardAdapter: LeaderBoardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentLeaderboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        leaderBoardActivity.supportActionBar?.show()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leaderBoardActivity = activity as AppCompatActivity
        leaderBoardActivity.supportActionBar?.hide()

        subscribeData()
        initRecyclerView()
    }

    private fun subscribeData() {
        viewModel.getUsers().observe(viewLifecycleOwner) {
            leaderBoardAdapter.submitList(it)
        }
    }

    private fun initRecyclerView() {
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvLeaderboard.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(itemDecoration)
            adapter = leaderBoardAdapter
        }
    }
}