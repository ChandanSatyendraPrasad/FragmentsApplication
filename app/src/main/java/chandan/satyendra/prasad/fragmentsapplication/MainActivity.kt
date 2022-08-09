package chandan.satyendra.prasad.fragmentsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.fragment.app.Fragment
import chandan.satyendra.prasad.fragmentsapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val myFirstFragment = OneFragment()
    val mySecondFragment = TwoFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSubView()
        initButton()
        addNavigationListener()
    }

    private fun addNavigationListener() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.pageHome -> {
                   replaceFragment(myFirstFragment)
                    true
                }
                R.id.pageMusic -> {
                   replaceFragment(mySecondFragment)
                    true
                }
                R.id.pageSettings -> {
                    // replaceFragment(mySecondFragment)
                    val i = Intent(Settings.ACTION_SETTINGS)
                    startActivity(i)
                    true
                }
                else -> false
            }
        }
    }

    private fun initButton() {
        findViewById<Button>(R.id.button_change).setOnClickListener {
            showSecondFragment()
        }

        findViewById<Button>(R.id.button_remove).setOnClickListener {
            removeFragment(mySecondFragment)
        }

    }

    private fun removeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
    }

    private fun showSecondFragment() {
        replaceFragment(mySecondFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()

    }

    private fun initSubView() {
        addFragment(myFirstFragment)
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment)
        transaction.commit()
    }
}