package com.sulistyo.github.user.presentation.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sulistyo.github.user.core.data.remote.ApiResponse
import com.sulistyo.github.user.core.domain.model.UserModel
import com.sulistyo.github.user.core.utils.DataMapper
import com.sulistyo.github.user.databinding.FragmentHomeBinding
import com.sulistyo.github.user.presentation.adapter.UserAdapter
import com.sulistyo.github.user.presentation.detail.UserDetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@ExperimentalCoroutinesApi
@FlowPreview
class HomeFragment : Fragment() {

    private var _bind: FragmentHomeBinding? = null
    private val bind get() = _bind!!

    private lateinit var mAdapter: UserAdapter
    private val viewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _bind = FragmentHomeBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = UserAdapter()

        with(bind) {
            etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // do nothing
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.toString().isBlank()) {
                        viewDefault.isVisible = true
                        rvMain.isVisible = false
                        viewLoading.isVisible = false
                        viewEmpty.root.isVisible = false
                    } else {
                        lifecycleScope.launch {
                            viewModel.queryChannel.value = s.toString()
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    // do nothing
                }
            })

            viewModel.searchResult.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ApiResponse.Success -> {
                        viewDefault.isVisible = false
                        rvMain.isVisible = true
                        viewLoading.isVisible = false
                        viewEmpty.root.isVisible = false
                        val mData =
                            DataMapper.responseToDomain(result.data)
                        mAdapter.setData(mData)
                    }
                    is ApiResponse.Loading -> {
                        viewDefault.isVisible = false
                        rvMain.isVisible = false
                        viewLoading.isVisible = true
                        viewEmpty.root.isVisible = false
                    }
                    is ApiResponse.Error -> {
                        viewDefault.isVisible = false
                        rvMain.isVisible = false
                        viewLoading.isVisible = false
                        viewEmpty.root.isVisible = true
                    }
                }

                bind.rvMain.apply {
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
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }

}