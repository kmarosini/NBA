package hr.algebra.nba.factory

import android.content.Context
import hr.algebra.nba.dao.NbaSqlHelper

fun getNbaRepository(context: Context?) = NbaSqlHelper(context)