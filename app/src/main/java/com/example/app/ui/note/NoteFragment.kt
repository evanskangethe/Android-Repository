package com.example.app.ui.note

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.adapter.NoteAdapter
import com.example.app.extension.toast
import com.example.app.newnote.NewNoteFragment
import kotlinx.android.synthetic.main.note_fragment.*

class NoteFragment : Fragment() {

    companion object {
        fun newInstance() = NoteFragment()
    }

    private lateinit var viewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.note_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        val mAdapter = NoteAdapter()
        viewManager = LinearLayoutManager(context)
//
        recyclerView = recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }
        viewModel.getAllnotes().observe(this, Observer{notes ->
            notes?.let {
                mAdapter.setNotes(it)
            }
        })

        fab.setOnClickListener { view ->
            fragmentManager!!.beginTransaction()
                .replace(R.id.container,NewNoteFragment.newInstance())
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit()
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteNote(mAdapter.getNoteAt(viewHolder.adapterPosition))
            }

        }).attachToRecyclerView(recyclerView)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.delete_note,menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.delete_all_notes -> {
                viewModel.deleteAllNotes()
                context?.toast("Deleted all Notes")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
