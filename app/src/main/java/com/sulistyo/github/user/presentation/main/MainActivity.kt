package com.sulistyo.github.user.presentation.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sulistyo.github.user.core.data.remote.ApiResponse
import com.sulistyo.github.user.core.utils.DataMapper.responseToDomain
import com.sulistyo.github.user.databinding.ActivityMainBinding
import com.sulistyo.github.user.presentation.adapter.UserAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    private val viewModel: MainViewModel by inject()
    private lateinit var mAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        initView()

    }

    private fun initView() {
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
                    if (s.toString().trim() == "") {
                        viewDefault.visibility = View.VISIBLE
                        rvMain.visibility = View.GONE
                        viewLoading.visibility = View.GONE
                        viewEmpty.root.visibility = View.GONE

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

            viewModel.searchResult.observe(this@MainActivity) { result ->
                when (result) {
                    is ApiResponse.Success -> {
                        viewDefault.visibility = View.GONE
                        rvMain.visibility = View.VISIBLE
                        viewLoading.visibility = View.GONE
                        viewEmpty.root.visibility = View.GONE

                        val mData = responseToDomain(result.data)
                        mAdapter.setList(mData)
                    }
                    is ApiResponse.Loading -> {
                        viewDefault.visibility = View.GONE
                        rvMain.visibility = View.GONE
                        viewLoading.visibility = View.VISIBLE
                        viewEmpty.root.visibility = View.GONE
                    }
                    is ApiResponse.Error -> {
                        viewDefault.visibility = View.GONE
                        rvMain.visibility = View.GONE
                        viewLoading.visibility = View.GONE
                        viewEmpty.root.visibility = View.VISIBLE
                    }
                }

                bind.rvMain.apply {
                    adapter = mAdapter
                }
            }
        }


    }

}