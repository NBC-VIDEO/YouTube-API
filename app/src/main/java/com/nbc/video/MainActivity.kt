package com.nbc.video

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.nbc.video.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val homeFragment by lazy { HomeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btn.setOnClickListener {
            setFragment(homeFragment)
        }
        // 메인 함수에서 싱글톤 객체의 데이터 접근 및 출력
        /* println("Items:")
        DummyData.homeData.item.forEach { item ->
            println(" - Title: ${item.title}, Thumbnail URL: ${item.thumbnailUrl}")
        }

        println("\nCategories:")
        DummyData.homeData.category.forEach { category ->
            println(" - Name: ${category.name}")
        }*/

    }

    private fun setFragment(frag: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frame, frag)
            setReorderingAllowed(true)
            addToBackStack(null)
        }

    }
}
