package edu.uw.zhewenz.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.uw.zhewenz.dotify.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private val safeArgs: ProfileFragmentArgs by navArgs()
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        val myCharacter = safeArgs.profile
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