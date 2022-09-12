package kr.loner.flowsample.ui.view.activity

import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import kr.loner.flowsample.R
import kr.loner.flowsample.data.BlogRepository
import kr.loner.flowsample.databinding.ActivityFirstHomeWorkBinding
import kr.loner.flowsample.ui.view.adapter.FirstHomeWorkAdapter
import kr.loner.flowsample.ui.viewmodel.FirstHomeWorkUiEvent
import kr.loner.flowsample.ui.viewmodel.FirstHomeWorkViewModel
import kr.loner.flowsample.ui.viewmodel.ViewModelFactory

class FirstHomeWorkActivity : AppCompatActivity() {
    private val viewModel by viewModels<FirstHomeWorkViewModel> { ViewModelFactory(BlogRepository()) }
    lateinit var binding: ActivityFirstHomeWorkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstHomeWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeByUiState()
        setBtnEvents()
        collectTheEvent()
    }

    private fun observeByUiState() {
        fun Button.setBackgroundTintByUiState(selected: Boolean) {
            val color = if (selected) {
                getColor(R.color.teal_200)
            } else {
                getColor(R.color.disable)
            }
            backgroundTintList = ColorStateList.valueOf(color)
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.firstHomeWorkUiBlogState.collect { uiState ->
                    binding.rvBlogs.adapter = FirstHomeWorkAdapter(uiState)
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.completeOnlyNaverSelect.collect { selected ->
                    binding.btnCompleteOnlyNaverSelect.setBackgroundTintByUiState(selected)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.completeOnlyDaumSelect.collect { selected ->
                    binding.btnCompleteOnlyDaumSelect.setBackgroundTintByUiState(selected)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.completeFourCountSelect.collect { selected ->
                    binding.btnCompleteFourCountSelect.setBackgroundTintByUiState(selected)
                }
            }
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.completeComplexSelect.collect { selected ->
                    binding.btnCompleteComplexSelect.setBackgroundTintByUiState(selected)
                }
            }
        }
    }


    private fun setBtnEvents() {
        with(binding) {
            btnCompleteOnlyNaverSelect.setOnClickListener { viewModel.uiEvent(FirstHomeWorkUiEvent.CompleteOnlyNaverSelectMsg) }
            btnCompleteOnlyDaumSelect.setOnClickListener { viewModel.uiEvent(FirstHomeWorkUiEvent.CompleteOnlyDaumSelectMsg) }
            btnCompleteFourCountSelect.setOnClickListener { viewModel.uiEvent(FirstHomeWorkUiEvent.CompleteFourCountSelectMsg) }
            btnCompleteComplexSelect.setOnClickListener { viewModel.uiEvent(FirstHomeWorkUiEvent.CompleteComplexSelectMsg) }
        }
    }

    private fun collectTheEvent() {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect { event ->
                    if (event is FirstHomeWorkUiEvent.ShowMsg) {
                        Toast.makeText(
                            this@FirstHomeWorkActivity.applicationContext,
                            event.msg,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}