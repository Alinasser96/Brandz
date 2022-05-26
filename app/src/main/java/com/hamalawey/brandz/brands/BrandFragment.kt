package com.hamalawey.brandz.brands

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hamalawey.brandz.R
import com.hamalawey.brandz.databinding.FragmentBrandBinding
import com.hamalawey.brandz.utils.getConfig
import com.hamalawey.brandz.utils.overrideFonts
import com.hamalawey.domain.entities.brand.Item
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_brand.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BrandFragment : Fragment() {
    private var results = mutableListOf<Item>()
    private var _binding: FragmentBrandBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BrandsViewModel by viewModels()
    private lateinit var adapter: BrandsAdapter
    private lateinit var font: String
    private var color: Int = 0
    private var pageIndex = 1
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrandBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        font = getConfig(requireContext()).font_family
        color = getConfig(requireContext()).app_color.toInt()
        overrideFonts(requireContext(), view, font)

        setupRecyclerView(view)

        viewModel.getBrandData(1, 5)

        lifecycleScope.launch {
            viewModel.brand.collect {
                it?.let {
                    binding.apply {
                        toolbar.text = it.brand.name
                        Glide.with(requireContext()).load(it.brand.banner).into(brandImage)
                        Glide.with(requireContext()).load(it.brand.logo).into(brandLogo)
                        descriptionTextview.text = it.brand.description
                    }
                    results.addAll(it.data)
                    adapter.notifyDataSetChanged()
                    isLastPage = it.cursor.next == null
                }
                pageIndex++
                Log.d("Main", it.toString())

            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collect {
                isLoading = it
                binding.loading.isVisible = it
            }
        }
    }

    private fun setupRecyclerView(view: View) {
        adapter = BrandsAdapter(font, color) {
            val bundle = Bundle()
            bundle.putInt("itemId", it)
            Navigation.findNavController(view)
                .navigate(R.id.action_brandFragment_to_detailsFragment, bundle)
        }

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.itemsRecyclerview.layoutManager = layoutManager
        binding.itemsRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if (!isLoading) {
                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        pageIndex++
                        viewModel.getBrandData(pageIndex, 5)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        binding.itemsRecyclerview.adapter = adapter
        adapter.submitList(results)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}