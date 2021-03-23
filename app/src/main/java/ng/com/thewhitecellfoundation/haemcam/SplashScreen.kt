package ng.com.thewhitecellfoundation.haemcam

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import ng.com.thewhitecellfoundation.haemcam.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private val handler by lazy {
        Handler(Looper.getMainLooper())
    }
    lateinit var binding: ActivitySplashScreenBinding
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            statusBarColor = Color.TRANSPARENT
        }
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()

//        handler.postDelayed(
//            {
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
//            },
//            3000
//        )
    }
}
