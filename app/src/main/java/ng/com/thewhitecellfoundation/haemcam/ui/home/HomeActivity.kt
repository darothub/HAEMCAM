package ng.com.thewhitecellfoundation.haemcam.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ng.com.thewhitecellfoundation.haemcam.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
