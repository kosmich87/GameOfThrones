package ru.skillbranch.gameofthrones.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.ui.splash.SplashFragmentDirections
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_GameOfThrones);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        savedInstanceState ?: prepareData()
    }

    private fun prepareData() {
        mainViewModel.syncDataIfNeed().observe(this, Observer<LoadResult<Boolean>> {
            when (it) {
                is LoadResult.Loading -> {
                    navController.navigate(R.id.nav_splash)
                }
                is LoadResult.Success -> {
                    navController.navigate(SplashFragmentDirections.actionNavSplashToNavHouses())
                }
                is LoadResult.Error -> {
                    Snackbar.make(nav_host_fragment,
                    it.errorMessage.toString(),
                    Snackbar.LENGTH_INDEFINITE)
                        .show()
                }
            }
        })
    }

    override fun androidInjector(): AndroidInjector<Any>? = dispatchingAndroidInjector
}