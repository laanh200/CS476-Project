package com.android.mulliganmarker

import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.mulliganmarker.databinding.FragmentPlayerListBinding
import com.android.mulliganmarker.adapter.PlayerListAdapter
import com.android.mulliganmarker.model.Player
import com.android.mulliganmarker.viewmodel.PlayerViewModel


class PlayerListFragment : Fragment() {

    private val binding: FragmentPlayerListBinding by viewBinding()

    private lateinit var mPlayerViewMode: PlayerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playerListRecyclerView

        val adapter = PlayerListAdapter()

        //Set the recycler view adapter equal to the expense list adapter
        binding.playerListRecyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(context)

        //Display orientation set vertical ->display list down
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        //Set layout equal to recycler view layout
        binding.playerListRecyclerView.layoutManager =  layoutManager


        mPlayerViewMode = ViewModelProvider(this).get(PlayerViewModel::class.java)

        mPlayerViewMode.readAllPlayers?.observe(viewLifecycleOwner, Observer{
            adapter.setData(it)
        })





        /*Item touch helper to use as a delete item functionality
        Swipe left or right on the item will delete the item off the recycler
        delete from database
     */
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            //Move doesn't matter
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            //When item get swiped
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                //Create an alert dialog when the user swipe on an item
                val builder = context?.let { AlertDialog.Builder(it) }
                builder?.setTitle("Delete player?")
                builder?.setMessage("Are you sure you want to delete the current selected player?")?.setCancelable(false)
                //If they select the yes option
                builder?.setPositiveButton("Yes") { _, _ ->
                    //Call function to delete the swiped item
                    deleteItem(viewHolder, adapter)
                }
                //If the select the no option
                builder?.setNegativeButton("No") { _, _ ->
                    adapter.notifyDataSetChanged();
                    //Get the target item being swiped
                    var itemTarget: View = viewHolder.itemView
                    //When they are swiped but answered no then turn back the background
                    itemTarget.setBackgroundColor(0)
                 //   itemTarget.setBackgroundResource(R.drawable.layout_border)
                }
                //create and show the dialog alert
                builder?.create()?.show()
            }
            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                //Create a value that hold the original position of item
                val originalPosition:Float = 0.0F
                //Get the target item
                var itemTarget: View = viewHolder.itemView
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE && isCurrentlyActive) {
                    if (dX != originalPosition){
                        //Set the background to red
                        itemTarget.setBackgroundColor(resources.getColor(R.color.red))
                    }
                }else{
                    //If they release the swipe then turn the background back to default
                    itemTarget.setBackgroundColor(0)
                  //  itemTarget.setBackgroundResource(R.drawable.layout_border)
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
            //Attach the item touch helper to the recycler view
        }).attachToRecyclerView(binding.playerListRecyclerView)
    }

    //This function is used to call to delete the expense item in the expenseList database
    private fun deleteItem(viewHolder: RecyclerView.ViewHolder, adapter: PlayerListAdapter){

        //Create variable and store the view holder position of swiped item
        val position = viewHolder.adapterPosition

        //Create an player target variable and call the get player at position by using position as parameter
        val currentTarget: Player = adapter.getPlayerAtPosition(position)

        //Call function to delete the current target player item from database
        mPlayerViewMode.deletePlayer(currentTarget)

        //Toast to let user know that item is being deleted
        Toast.makeText(context, "Deleting:" + currentTarget.first_name, Toast.LENGTH_SHORT).show()

        //Let the adapter know that the income item at the swiped position got deleted
        adapter.notifyItemRemoved(position)
    }
}