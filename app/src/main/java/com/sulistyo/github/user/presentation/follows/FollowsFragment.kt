package com.sulistyo.github.user.presentation.follows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.sulistyo.github.user.core.data.remote.ApiResponse
import com.sulistyo.github.user.core.domain.model.UserModel
import com.sulistyo.github.user.core.utils.DataMapper
import com.sulistyo.github.user.databinding.FragmentFollowsBinding
import com.sulistyo.github.user.presentation.adapter.UserAdapter
import com.sulistyo.github.user.presentation.detail.UserDetailActivity
import org.koin.android.ext.android.inject

class FollowsFragment : Fragment() {
    private var _bind: FragmentFollowsBinding? = null
    private val bind get() = _bind!!

    private lateinit var mAdapter: UserAdapter
    private val viewModel: FollowsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _bind = FragmentFollowsBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mAdapter = UserAdapter()
        val username = arguments?.getString(USERNAME) ?: ""

        viewModel.getFollowers(username)
        viewModel.followers.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResponse.Success -> {
                    showLoading(false)
                    val mData = DataMapper.responseToDomain(result.data)
                    if (mData.isNotEmpty()) {
                        bind.rvFollows.isVisible = true
                        bind.viewEmpty.isVisible = false
                        mAdapter.setList(mData)
                    } else {
                        bind.rvFollows.isVisible = false
                        bind.viewEmpty.isVisible = true
                    }

                }
                is ApiResponse.Loading -> {
                    showLoading(true)
                }
                is ApiResponse.Error -> {
                    showLoading(false)
                    bind.rvFollows.isVisible = false
                    bind.viewEmpty.isVisible = true
                }
            }
        }

        bind.rvFollows.apply {
            adapter = mAdapter
        }
        mAdapter.setOnItemClickCallback(object : UserAdapter.OnItemCallback {
            override fun onItemClicked(data: UserModel) {
                val intent = Intent(activity, UserDetailActivity::class.java)
                intent.putExtra(UserDetailActivity.EXTRA_USER, data)
                startActivity(intent)
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }

    private fun showLoading(isLoading: Boolean) {
        bind.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        private const val USERNAME = "username"

        fun newInstance(username: String?): FollowsFragment {
            val fragment = FollowsFragment()
            val bundle = Bundle()
            bundle.putString(USERNAME, username)

            fragment.arguments = bundle
            return fragment
        }
    }
}