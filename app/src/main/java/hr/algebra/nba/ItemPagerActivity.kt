package hr.algebra.nba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.algebra.nba.adapter.ItemPagerAdapter
import hr.algebra.nba.databinding.ActivityItemPagerBinding
import hr.algebra.nba.databinding.FragmentItemsBinding
import hr.algebra.nba.framework.fetchItems
import hr.algebra.nba.model.Player

const val POSITION = "hr.algebra.nba.position"
class ItemPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemPagerBinding
    private lateinit var items: MutableList<Player>
    private var itemPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initPager()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initPager() {
        items = fetchItems()
        itemPosition = intent.getIntExtra(POSITION, itemPosition)
        binding.viewPager.adapter = ItemPagerAdapter(this, items)
        binding.viewPager.currentItem = itemPosition
    }
}