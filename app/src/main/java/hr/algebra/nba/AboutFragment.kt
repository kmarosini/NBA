package hr.algebra.nba

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieDrawable
import hr.algebra.nba.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(layoutInflater)

        binding.animationView.setAnimation(R.raw.baller)
        binding.animationView.repeatCount = LottieDrawable.INFINITE
        binding.animationView.playAnimation()

        return binding.root
    }


}