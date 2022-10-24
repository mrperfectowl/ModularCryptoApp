package com.example.modularcryptoapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modularcryptoapp.databinding.FragmentMainBinding
import com.example.modularcryptoapp.ui.adapter.ViewItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var lm: LinearLayoutManager
    private val coinsAdapter = ItemAdapter<ViewItem<*>>()

    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        val coinListObserver = Observer<CoinListState> { state ->
            when(state) {
                is CoinListState.Success -> {
                    val items = state.coins.map { coin ->
                        CoinViewItem.toViewItem(coin)
                    }
                    coinsAdapter.setNewList(items)
                }
                is CoinListState.Error -> { }
                CoinListState.Loading -> { }
            }

        }
        viewModel.coinListState.observe(viewLifecycleOwner, coinListObserver)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        lm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvHome.apply {
            itemAnimator = null
            layoutManager = lm
            adapter = FastAdapter.with(
                listOf(
                    coinsAdapter,
                )
            )
        }
    }
}