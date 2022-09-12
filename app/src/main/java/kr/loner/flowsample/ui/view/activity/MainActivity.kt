package kr.loner.flowsample.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kr.loner.flowsample.ui.theme.FlowSampleTheme
import kr.loner.flowsample.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateUi()
            CollectTheUiEvent()
        }
    }

    @Composable
    private fun CreateUi() {
        FlowSampleTheme {
            Column(modifier = Modifier.padding(start = 24.dp, top = 12.dp)) {
                CreateBtn(btnName = "1번 숙제") {
                    viewModel.openPage(MainViewModel.MainUiEvent.OpenFirstHomeWork)
                }
                CreateBtn(btnName = "2번 숙제") {
                    viewModel.openPage(MainViewModel.MainUiEvent.OpenSecondHomeWork)
                }
            }
        }
    }

    @Composable
    fun CreateBtn(btnName: String, content: () -> Unit) {
        Button(onClick = { content() }) {
            Text(text = btnName)
        }
    }

    @Composable
    private fun CollectTheUiEvent() {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect { event ->
                    if (event is MainViewModel.MainUiEvent.OpenFirstHomeWork) {
                        startActivity(Intent(this@MainActivity, FirstHomeWorkActivity::class.java))
                    } else {
                        startActivity(Intent(this@MainActivity, SecondHomeWorkActivity::class.java))
                    }
                }
            }
        }
    }


}