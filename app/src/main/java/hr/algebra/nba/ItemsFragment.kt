package hr.algebra.nba

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.nba.adapter.ItemAdapter
import hr.algebra.nba.databinding.FragmentItemsBinding
import hr.algebra.nba.framework.fetchItems
import hr.algebra.nba.model.Player

/**
 * A simple [Fragment] subclass.
 * Use the [ItemsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemsFragment : Fragment() {
    private lateinit var  binding: FragmentItemsBinding
    private lateinit var items: MutableList<Player>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        items = requireContext().fetchItems()
        binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ItemAdapter(requireContext(), items)
        }
    }

    override fun onResume() {
        super.onResume()
        items = requireContext().fetchItems()
        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ItemAdapter(requireContext(), items)
        }
    }


}