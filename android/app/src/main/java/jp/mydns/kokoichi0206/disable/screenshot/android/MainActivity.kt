package jp.mydns.kokoichi0206.disable.screenshot.android

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import jp.mydns.kokoichi0206.disable.screenshot.android.ui.theme.AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

                    var state by remember { mutableStateOf("flag disabled") }

                    MainView(
                        state,
                    ) {
                        // Toggle FLAG_SECURE
                        if (hasSecureFlag()) {
                            window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
                            state = "flag disabled"
                        } else {
                            window.setFlags(
                                WindowManager.LayoutParams.FLAG_SECURE,
                                WindowManager.LayoutParams.FLAG_SECURE
                            )
                            state = "flag enabled"
                        }
                    }

                }
            }
        }
    }

    fun hasSecureFlag(): Boolean {
        val FLAG = WindowManager.LayoutParams.FLAG_SECURE
        val attrs = window.attributes

        // 状況確認用。
        Log.d("foobar", "attrs: $attrs")
        Log.d("foobar", "attrs.flags: ${attrs.flags}")
        Log.d("foobar", "FLAG: $FLAG")
        Log.d("foobar", "attrs.flags and FLAG: ${attrs.flags and FLAG}")

        return attrs.flags and FLAG != 0
    }
}
