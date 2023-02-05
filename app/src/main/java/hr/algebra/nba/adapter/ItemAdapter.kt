package hr.algebra.nba.adapter

import android.content.ContentUris
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.nba.ItemPagerActivity
import hr.algebra.nba.NASA_PROVIDER_CONTENT_URI
import hr.algebra.nba.POSITION
import hr.algebra.nba.R
import hr.algebra.nba.framework.startActivity
import hr.algebra.nba.model.Player
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

class ItemAdapter(private val context: Context, private val items: MutableList<Player>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem = itemView.findViewById<ImageView>(R.id.ivItem)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

        fun bind(item: Player) {

            Picasso
                .get()
                .load(File(""))
                .error(R.drawable.nba_logo)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivItem
                )
            tvTitle.text = item.firstName + " " + item.lastName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.setOnLongClickListener {
            deleteItem(position)
            true
        }

        holder.itemView.setOnClickListener {
            context.startActivity<ItemPagerActivity>(POSITION, position)
        }


        holder.bind(item)
    }

    private fun deleteItem(position: Int) {
        val item = items[position]
        context.contentResolver.delete(
            ContentUris.withAppendedId(NASA_PROVIDER_CONTENT_URI, item.id!!.toLong()),
            null,
            null
        )

        items.removeAt(position)
        notifyDataSetChanged()
    }
}