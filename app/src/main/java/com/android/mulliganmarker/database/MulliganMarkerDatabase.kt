package com.android.mulliganmarker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.mulliganmarker.model.Round
import com.android.mulliganmarker.model.Scorecard
import com.android.mulliganmarker.model.Player

@Database(entities = [Round::class, Player::class, Scorecard::class], version = 1, exportSchema = false)
@TypeConverters(RoundTypeConverters::class)
abstract class MulliganMarkerDatabase:RoomDatabase() {

    //Insert DAOs
    abstract fun playerDao(): PlayerDAO
    abstract fun roundDao(): RoundDAO
    abstract fun scorecardDao(): ScorecardDAO

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