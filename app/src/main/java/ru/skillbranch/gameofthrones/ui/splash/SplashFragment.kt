package ru.skillbranch.gameofthrones.ui.splash

import android.animation.Animator
import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.splash_fragment.*
import ru.skillbranch.gameofthrones.R


class SplashFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.splash_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*val animator = AnimatorInflater.loadAnimator(requireContext(), R.animator.heart_bit)
        animator.apply {
            setTarget(splash_bg)
            start()
        }*/
    }
}