package hr.algebra.nba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        var ivAddItem : ImageView = findViewById(R.id.ivAddItem)

        if (ivAddItem.drawable == null) {
            Picasso.get()
                .load(File(""))
                .error(R.drawable.add_image)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivAddItem)

            Toast.makeText(this, "Uso u petlju", Toast.LENGTH_SHORT).show()

            ivAddItem.setOnClickListener {
                Toast.makeText(this, "Kliknuo si na sliku", Toast.LENGTH_SHORT).show()
            }
        }


    }
}