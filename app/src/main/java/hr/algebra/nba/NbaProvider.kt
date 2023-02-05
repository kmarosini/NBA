package hr.algebra.nba

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import hr.algebra.nba.dao.NbaRepository
import hr.algebra.nba.dao.NbaSqlHelper
import hr.algebra.nba.factory.getNbaRepository
import hr.algebra.nba.model.Player


private const val AUTHORITY = "hr.algebra.nba.api.provider"
private const val PATH = "items"
val NASA_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH")

private const val ITEMS = 10
private const val ITEM_ID = 20
private val URI_MATCHER = with(UriMatcher(UriMatcher.NO_MATCH)) {
    addURI(AUTHORITY, PATH, ITEMS)
    addURI(AUTHORITY, "$PATH/#", ITEM_ID)
    this
}


class NbaProvider : ContentProvider() {

    private lateinit var nbaRepository: NbaRepository

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        when(URI_MATCHER.match(uri)) {
            ITEMS -> return nbaRepository.delete(selection, selectionArgs)
            ITEM_ID -> {
                uri.lastPathSegment?.let {
                    return nbaRepository.delete("${Player::id.name}=?", arrayOf(it))

                }
            }
        }
        throw  java.lang.IllegalArgumentException("no such uri")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = nbaRepository.insert(values)
        return ContentUris.withAppendedId(NASA_PROVIDER_CONTENT_URI, id)
    }

    override fun onCreate(): Boolean {
        nbaRepository = getNbaRepository(context)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = nbaRepository.query(projection, selection, selectionArgs, sortOrder)

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        when(URI_MATCHER.match(uri)) {
            ITEMS -> return nbaRepository.update(values,selection, selectionArgs)
            ITEM_ID -> {
                uri.lastPathSegment?.let {
                    return nbaRepository.update(values, "${Player::id.name}=?", arrayOf(it))

                }
            }
        }
        throw  java.lang.IllegalArgumentException("no such uri")
    }
}