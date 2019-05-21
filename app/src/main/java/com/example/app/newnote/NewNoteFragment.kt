package com.example.app.newnote

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.app.R
import com.example.app.database.Note
import com.example.app.extension.toast
import com.example.app.ui.note.NoteViewModel
import kotlinx.android.synthetic.main.new_note_fragment.*


class NewNoteFragment : Fragment() {

    companion object {
        fun newInstance() = NewNoteFragment()
    }

    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_note_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        number_picker.maxValue = 10
        number_picker.minValue = 1

        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.add_note,menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return  when(item?.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun saveNote() {
        var title = note_title.text.toString()
        var description =  note_description.text.toString()
        var priority = number_picker.value

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            context?.toast("Please provide a valid title and description")
        }

        viewModel.insertNote(Note(title = title,description = description,priority = priority))
        context?.toast("Note Saved Successfully")

    }
}
