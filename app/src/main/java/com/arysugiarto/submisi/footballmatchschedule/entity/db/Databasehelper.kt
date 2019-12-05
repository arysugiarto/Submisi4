package com.arysugiarto.submisi.footballmatchschedule.entity.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class Databasehelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: Databasehelper? = null

        @Synchronized
        fun getInstance(ctx: Context): Databasehelper {
            if (instance == null) {
                instance = Databasehelper(ctx.applicationContext)
            }
            return instance as Databasehelper
        }
    }

    override fun onCreate(position: SQLiteDatabase) {
        position.createTable(DbFavoriteMatch.TB_FAVORITE, true,
            DbFavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            DbFavoriteMatch.EVENT_ID to TEXT + UNIQUE,
            DbFavoriteMatch.ID_TEAM_KANDANG to TEXT,
            DbFavoriteMatch.ID_TEAM_TANDANG to TEXT
        )
    }


    override fun onUpgrade(position0: SQLiteDatabase, p1: Int, p2: Int) {
        position0.dropTable(DbFavoriteMatch.TB_FAVORITE, true)
    }
}
// Access property for Context
val Context.database: Databasehelper
    get() = Databasehelper.getInstance(applicationContext)