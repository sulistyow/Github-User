package com.sulistyo.github.user.presentation.following

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
import com.sulistyo.github.user.databinding.FragmentFollowingBinding
import com.sulistyo.github.user.presentation.adapter.UserAdapter
import com.sulistyo.github.user.presentation.detail.UserDetailActivity
import org.koin.android.ext.android.inject

class FollowingFragment : Fragment() {
    private var _bind: FragmentFollowingBinding? = null
    private val bind get() = _bind!!

    private lateinit var mAdapter: UserAdapter
    private val viewModel: FollowingViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _bind = FragmentFollowingBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString(USERNAME) ?: ""
        mAdapter = UserAdapter()
        viewModel.getFollowing(username)
        viewModel.following.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResponse.Success -> {
                    showLoading(false)
                    val mData = DataMapper.responseToDomain(result.data)
                    if (mData.isNotEmpty()) {
                        bind.rvFollowing.isVisible = true
                        bind.viewEmpty.isVisible = false
                        mAdapter.setList(mData)
                    } else {
                        bind.rvFollowing.isVisible = false
                        bind.viewEmpty.isVisible = true
                    }
                }
                is ApiResponse.Loading -> {
                    showLoading(true)

                }
                is ApiResponse.Error -> {
                    showLoading(false)
                    bind.rvFollowing.isVisible = false
                    bind.viewEmpty.isVisible = true
                }
            }
        }

        bind.rvFollowing.apply {
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

        fun newInstance(username: String?): FollowingFragment {
            val fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putString(USERNAME, username)

            fragment.arguments = bundle
            return fragment
        }
    }
}