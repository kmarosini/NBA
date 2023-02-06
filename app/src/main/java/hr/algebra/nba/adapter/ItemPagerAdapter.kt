package hr.algebra.nba.adapter

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

class ItemPagerAdapter(private val context: Context, private val items: MutableList<Player>) : RecyclerView.Adapter<ItemPagerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivItem = itemView.findViewById<ImageView>(R.id.ivItem)


         val etFirstName: EditText = itemView.findViewById(R.id.etFirstName)
         val etLastName: EditText = itemView.findViewById(R.id.etLastName)
         val etHeightFeet: EditText = itemView.findViewById(R.id.etHeightFeet)
         val etHeightInches: EditText = itemView.findViewById(R.id.etHeightInches)
         val etWeightPounds: EditText = itemView.findViewById(R.id.etWeightPounds)
         val btnEditPlayer: Button = itemView.findViewById(R.id.btnEditPlayer)


        fun bind(item: Player) {

            etFirstName.setText(item.firstName)
            etLastName.setText(item.lastName)
            etHeightFeet.setText(item.heightFeet)
            etHeightInches.setText(item.heightInches)
            etWeightPounds.setText(item.weightPounds)
            Picasso.get()
                .load(File(""))
                .error(R.drawable.nba_about)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pager, parent, false)
        )    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]


        holder.btnEditPlayer.setOnClickListener {
            // update
            item.firstName = holder.etFirstName.text.toString()
            item.lastName = holder.etLastName.text.toString()
            item.heightFeet = holder.etHeightFeet.text.toString()
            item.heightInches = holder.etHeightInches.text.toString()
            item.weightPounds = holder.etWeightPounds.text.toString()


            context.contentResolver.update(
                ContentUris.withAppendedId(NASA_PROVIDER_CONTENT_URI, item.id!!.toLong()),
                ContentValues().apply {
                    put(Player::firstName.name, item.firstName)
                    put(Player::lastName.name, item.lastName)
                    put(Player::heightFeet.name, item.heightFeet)
                    put(Player::heightInches.name, item.heightInches)
                    put(Player::weightPounds.name, item.weightPounds)
                },
                null, null
            )
            notifyItemChanged(position)
        }

        holder.bind(item)
    }
}