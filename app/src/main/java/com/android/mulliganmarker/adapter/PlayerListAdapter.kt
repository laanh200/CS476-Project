package com.android.mulliganmarker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.DiffUtil.PlayerDiffUtil
import com.android.mulliganmarker.databinding.PlayerCustomRowBinding
import com.android.mulliganmarker.model.Player


class PlayerListAdapter:RecyclerView.Adapter<PlayerListAdapter.MyViewHolder>() {

    //create an empty list of player
    private var playerList = emptyList<Player>()

    class MyViewHolder(val binding: PlayerCustomRowBinding):RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //Create the view in the file fragment player list
       // return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player_custom_row,parent,false))
        return MyViewHolder(PlayerCustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Create a player variable at the current position of the list
        val currentPlayer = playerList[position]

        /*
        holder.itemView.findViewById<TextView>(R.id.player_firstName).text = "Name: " + currentPlayer.first_name
        holder.itemView.findViewById<TextView>(R.id.player_lastName).text = " " + currentPlayer.last_name
        holder.itemView.findViewById<TextView>(R.id.player_email).text = "Email: " + currentPlayer.email
        holder.itemView.findViewById<TextView>(R.id.player_phoneNum).text = "Phone Number: "+ currentPlayer.phone_number
        holder.itemView.findViewById<TextView>(R.id.player_handicap).text = "Handicap: "+ currentPlayer.handicap.toString()
*/
        with(holder){
            binding.playerFirstName.text =  "Name: " + currentPlayer.first_name
            binding.playerLastName.text = " " + currentPlayer.last_name
            binding.playerEmail.text = "Email: " + currentPlayer.email
            binding.playerPhoneNum.text = "Phone Number: "+ currentPlayer.phone_number
            binding.playerHandicap.text = "Handicap: "+ currentPlayer.handicap.toString()
        }
    }

    //Function to return the size of the list
    override fun getItemCount(): Int {
        return playerList.size
    }

    //Function to set the players parameter to the current player list
    fun setData(newPlayersList: List<Player>){
        /*this.playerList= players
        /Notify recyclerview for changes been made
        notifyDataSetChanged()

         */
        val diffUtil = PlayerDiffUtil(playerList, newPlayersList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        playerList = newPlayersList
        diffResult.dispatchUpdatesTo(this)
    }

    //Return the player item in the players list
    fun getPlayerAtPosition(position: Int):Player{
        return playerList[position]
    }

}