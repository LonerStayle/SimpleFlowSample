package kr.loner.flowsample.ui.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kr.loner.flowsample.R
import kr.loner.flowsample.data.BlogRepository
import kr.loner.flowsample.databinding.ActivitySecondHomeWorkBinding
import kr.loner.flowsample.ui.view.adapter.SecondHomeWorkAdapter
import kr.loner.flowsample.ui.viewmodel.FirstHomeWorkViewModel
import kr.loner.flowsample.ui.viewmodel.SecondHomeWorkViewModel
import kr.loner.flowsample.ui.viewmodel.ViewModelFactory

class SecondHomeWorkActivity : AppCompatActivity() {
    private val viewModel by viewModels<SecondHomeWorkViewModel> { ViewModelFactory(BlogRepository()) }
    lateinit var binding: ActivitySecondHomeWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondHomeWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        collectTheShowMsg()
    }

    private fun collectTheShowMsg() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showMsg.collect {
                    Toast.makeText(
                        this@SecondHomeWorkActivity,
                        it.msg,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setAdapter() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.blogList.onCompletion {
                    viewModel.showToast(SecondHomeWorkViewModel.ShowMsg("조회 완료!!"))
                }.collect {
                    binding.rvBlogs.adapter = SecondHomeWorkAdapter(it)
                }
            }
        }
    }
}