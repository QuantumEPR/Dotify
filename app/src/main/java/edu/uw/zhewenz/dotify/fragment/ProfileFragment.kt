package edu.uw.zhewenz.dotify.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.uw.zhewenz.dotify.DotifyApplication
import edu.uw.zhewenz.dotify.R
import edu.uw.zhewenz.dotify.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var dotifyApp : DotifyApplication
    override fun onAttach(context: Context) {
        super.onAttach(context)
        dotifyApp = context.applicationContext as DotifyApplication
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)

        val myCharacter = dotifyApp.myCharacter
        with(binding) {
            ivProfile.setImageResource(myCharacter.profilePic)
            tvProfileName.text = myCharacter.name
            tvAge.text = resources.getString(R.string.age, myCharacter.age)
            tvDate.text = resources.getString(R.string.joined_date,myCharacter.date)
            tvEmail.text = resources.getString(R.string.email, myCharacter.email)
            tvHeight.text = resources.getString(R.string.height, myCharacter.height)
        }
        return binding.root
    }
}