package com.sulistyo.github.user.presentation.detail

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sulistyo.github.user.R
import com.sulistyo.github.user.core.data.remote.ApiResponse
import com.sulistyo.github.user.core.domain.model.UserModel
import com.sulistyo.github.user.databinding.ActivityUserDetailBinding
import com.sulistyo.github.user.presentation.adapter.SectionPagerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    private val viewModel: DetailUserViewModel by inject()

    private lateinit var user: UserModel
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.user_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        user = intent.getParcelableExtra<UserModel>(EXTRA_USER) as UserModel

        initView()

    }

    private fun initView() {
        showLoading(true)
        viewModel.getDetailUser(user.login.toString())
            .observe(this) { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        showLoading(false)
                        binding.progressBar.isVisible = false
                        val data = response.data
                        Glide.with(this@UserDetailActivity)
                            .load(data.avatarUrl)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .circleCrop()
                            .into(binding.imgAvatar)

                        binding.apply {
                            tvName.text = data.name
                            tvUsername.text = "@${data.login}"
                            tvLocation.text = data.location
                            tvRepository.text = "${data.publicRepos.toString()} Repository"
                            tvFollower.text = "${data.followers} followers"
                            tvFollowing.text = "${data.following} following"
                        }
                    }
                    is ApiResponse.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is ApiResponse.Error -> {
                        Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()
                    }

                }
            }

        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(user.id)
            withContext(Dispatchers.Main) {
                isFavorite = if (count > 0) {
                    binding.fab.setImageResource(R.drawable.ic_favorite)
                    true
                } else {
                    binding.fab.setImageResource(R.drawable.ic_favorite_border)
                    false
                }
            }
        }

        binding.fab.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                binding.fab.setImageResource(R.drawable.ic_favorite)
                viewModel.setFavorite(user, isFavorite)
                Toast.makeText(this, getString(R.string.added_to_favorite), Toast.LENGTH_SHORT)
                    .show()
            } else {
                binding.fab.setImageResource(R.drawable.ic_favorite_border)
                viewModel.removeFavorite(user.id)
                Toast.makeText(this, "Remove From Favorite", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val sectionPagerAdapter = SectionPagerAdapter(this)
        sectionPagerAdapter.username = user.login
        binding.viewPager.adapter = sectionPagerAdapter

        var tabSelect: TabLayout.Tab? = null
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
            if (position == 0) {
                tabSelect = tab
            }
        }.attach()
        tabSelect?.select()


    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    companion object {
        const val EXTRA_USER = "extra_user"

        @StringRes
        private val TAB_TITLE = intArrayOf(R.string.follower, R.string.following)
    }
}