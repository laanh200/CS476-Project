package com.android.mulliganmarker.DiffUtil

import androidx.recyclerview.widget.DiffUtil
import com.android.mulliganmarker.model.Player

class PlayerDiffUtil(
        private val oldList: List<Player>,
        private val newList: List<Player>
): DiffUtil.Callback() {

    //Function to return old list size
    override fun getOldListSize(): Int {
        return oldList.size
    }
    //Function to return new list size
    override fun getNewListSize(): Int {
        return newList.size
    }

    //Function to return a boolean true if the player id of the two list in their item position are the same.
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].player_id == newList[newItemPosition].player_id
    }

    //Function to check each values of the item
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //If they are not the same then return false
       return when {
           oldList[oldItemPosition].player_id != newList[newItemPosition].player_id ->{
               false
           }
           oldList[oldItemPosition].first_name != newList[newItemPosition].first_name ->{
               false
           }
           oldList[oldItemPosition].email != newList[newItemPosition].email ->{
               false
           }
           oldList[oldItemPosition].phone_number != newList[newItemPosition].phone_number ->{
               false
           }
           oldList[oldItemPosition].handicap != newList[newItemPosition].handicap ->{
               false
           }
           else -> true
        }
    }

}