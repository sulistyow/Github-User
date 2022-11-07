package com.sulistyo.github.user.presentation.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.sulistyo.github.user.core.domain.model.UserModel
import com.sulistyo.github.user.databinding.FragmentFavoriteBinding
import com.sulistyo.github.user.presentation.adapter.UserAdapter
import com.sulistyo.github.user.presentation.detail.UserDetailActivity
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {

    private var _bind: FragmentFavoriteBinding? = null
    private val bind get() = _bind!!

    private lateinit var mAdapter: UserAdapter
    private val viewModel: FavoriteViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _bind = FragmentFavoriteBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = UserAdapter()

        viewModel.favorites.observe(viewLifecycleOwner) { result ->
            if (result.isNotEmpty()) {
                bind.apply {
                    rvMain.adapter = mAdapter
                    rvMain.isVisible = true
                    viewEmpty.isVisible = false
                    mAdapter.setList(result)
                }
            } else {
                bind.apply {
                    rvMain.isVisible = false
                    viewEmpty.isVisible = true

                }
            }
        }
        bind.rvMain.adapter = mAdapter
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
}