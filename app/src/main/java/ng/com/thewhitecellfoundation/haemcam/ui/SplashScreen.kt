package ng.com.thewhitecellfoundation.haemcam.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import ng.com.thewhitecellfoundation.haemcam.databinding.ActivitySplashScreenBinding
import ng.com.thewhitecellfoundation.utils.activity.hideSystemUI

class SplashScreen : AppCompatActivity() {
    private val handler by lazy {
        Handler(Looper.getMainLooper())
    }
    lateinit var binding: ActivitySplashScreenBinding
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        hideSystemUI()
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
