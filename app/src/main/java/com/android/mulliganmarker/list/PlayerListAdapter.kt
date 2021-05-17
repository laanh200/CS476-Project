package com.android.mulliganmarker.list

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.R
import com.android.mulliganmarker.model.Player
import java.util.jar.Attributes

class PlayerListAdapter:RecyclerView.Adapter<PlayerListAdapter.MyViewHolder>() {

    //create an empty list of player
    private var playerList = emptyList<Player>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //Create the view in the file fragment player list
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player_custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPlayer = playerList[position]


        holder.itemView.findViewById<TextView>(R.id.player_firstName).text = "Name: " + currentPlayer.first_name
        holder.itemView.findViewById<TextView>(R.id.player_lastName).text = " " + currentPlayer.last_name
        holder.itemView.findViewById<TextView>(R.id.player_email).text = "Email: " + currentPlayer.email
        holder.itemView.findViewById<TextView>(R.id.player_phoneNum).text = "Phone Number: "+ currentPlayer.phone_number
        holder.itemView.findViewById<TextView>(R.id.player_handicap).text = "Handicap: "+ currentPlayer.handicap.toString()

    }

    //Function to return the size of the list
    override fun getItemCount(): Int {
        return playerList.size
    }

    //Function to set the players parameter to the current player list
    fun setData(players: List<Player>){
        this.playerList= players
        //Notify recyclerview for changes been made
        notifyDataSetChanged()
    }

    //Return the player item in the players list
    fun getPlayerAtPosition(position: Int):Player{
        return playerList[position]
    }

}