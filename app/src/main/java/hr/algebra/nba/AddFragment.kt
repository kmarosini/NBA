package hr.algebra.nba

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import hr.algebra.nba.databinding.FragmentAddBinding
import hr.algebra.nba.databinding.FragmentItemsBinding
import hr.algebra.nba.model.Player
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AddFragment : Fragment() {

    private lateinit var  binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddPlayer.setOnClickListener {
            val item = Player(
                null,
                binding.etFirstNameEdit.text.toString(),
                binding.etLastNameEdit.text.toString(),
                binding.etHeightFeetEdit.text.toString(),
                binding.etHeightInchesEdit.text.toString(),
                null,
                null,
                binding.etWeightPoundsEdit.text.toString()
            )

            GlobalScope.launch {
                context?.contentResolver?.insert(
                    NASA_PROVIDER_CONTENT_URI,
                    ContentValues().apply {
                        put(Player::firstName.name, item.firstName)
                        put(Player::lastName.name, item.lastName)
                        put(Player::heightFeet.name, item.heightFeet)
                        put(Player::heightInches.name, item.heightInches)
                        put(Player::weightPounds.name, item.weightPounds)
                    })
            }
        }
    }


}