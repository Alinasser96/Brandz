package com.hamalawey.brandz.itemDetails

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bumptech.glide.Glide
import com.hamalawey.brandz.DotView
import com.hamalawey.brandz.R
import com.hamalawey.brandz.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    lateinit var viewPagerAdapter: ImageSlideAdapter

    private var dots: ArrayList<View> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val config: Configuration = resources.configuration
        if (config.layoutDirection === View.LAYOUT_DIRECTION_RTL) {
            binding.dotsIndicator.rotation = 180f
        }

        viewModel.getItemDetails(arguments?.getInt("itemId") ?: 0)
        lifecycleScope.launch {
            viewModel.details.collect {
                it?.let {
                    binding.apply {
                        detailsName.text = it.data.name
                        detailsLayout.background =
                            getDrawable(requireContext(), R.drawable.rounded_top_white_bg)
                        heartLayout.background =
                            getDrawable(requireContext(), R.drawable.rounded_white_bg)
                        heartLayout.heart_iv.setImageResource(R.drawable.heart)
                        shareLayout.background =
                            getDrawable(requireContext(), R.drawable.rounded_white_bg)
                        shareLayout.share_iv.setImageResource(R.drawable.share)

                        detailsPromotion.text = it.data.promotion.sub_title
                        detailsPrice.text = it.data.price.currency + " " + it.data.price.amount
                        Glide.with(requireContext()).load(it.data.brand.logo).into(detailsLogo)
                        viewPagerAdapter = ImageSlideAdapter(requireContext(), it.data.images)
                        if (it.data.promotion.title != null) {
                            detailsPromoBadge.apply {
                                isVisible = true
                                text = it.data.promotion.title
                            }
                        } else {
                            detailsPromoBadge.isVisible = false
                        }
                        viewpager.adapter = viewPagerAdapter
                        addDots(it.data.images.size)
                    }

                }
            }
        }
    }


    private fun addDots(numPages: Int) {
        dots = ArrayList()
        val dotsLayout = binding.dotsIndicator
        for (i in 0 until numPages) {
            val dot = DotView(requireContext())
            dot.setDotSelected(false)
            val params = ViewGroup.MarginLayoutParams(
                36,
                36
            )
            dot.setOnClickListener {
                binding.viewpager.currentItem = dots.indexOf(it)
            }
            dotsLayout.addView(dot, params)
            dots.add(dot)
        }
        selectDot(0, numPages)
        binding.viewpager.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                selectDot(position, numPages)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    fun selectDot(idx: Int, numPages: Int) {
        for (i in 0 until numPages) {
            val isDotSelected = (i == idx)
            (dots[i] as DotView).setDotSelected(isDotSelected)
        }
    }
}