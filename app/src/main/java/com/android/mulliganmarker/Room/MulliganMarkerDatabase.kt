package com.android.mulliganmarker.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.mulliganmarker.Model.Round
import com.android.mulliganmarker.Model.Scorecard
import com.android.mulliganmarker.Model.Player

@Database(entities = [Round::class, Player::class, Scorecard::class], version = 1, exportSchema = false)
abstract class MulliganMarkerDatabase:RoomDatabase() {

    //Insert DAOs
    abstract fun PlayerDao(): PlayerDAO
    abstract fun RoundDao(): RoundDAO
    abstract fun ScorecardDao(): ScorecardDAO

    companion object{
        @Volatile
        //Create an empty instance of expense database
        private var INSTANCE: MulliganMarkerDatabase? = null

        //Function to get the database
        fun getDatabase(context: Context):MulliganMarkerDatabase{
            val temporaryInstance = INSTANCE
            //Check if the temporary instance is already exist
            if(temporaryInstance != null){
                //If yes, then return that instance
                return temporaryInstance
            }
            //Creating a new instance if not exist
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MulliganMarkerDatabase::class.java,
                        "Mulligan_Marker_Database"
                ).fallbackToDestructiveMigration().build()
                //Set the instance equal to the brand new created instance
                INSTANCE = instance
                //return the new instance
                return instance
            }
        }
    }
}