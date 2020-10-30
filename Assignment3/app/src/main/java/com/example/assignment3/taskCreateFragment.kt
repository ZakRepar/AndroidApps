package com.example.assignment3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.assignment3.model.Task
import kotlinx.android.synthetic.main.fragment_task_create.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [taskCreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class taskCreateFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_task_create, container, false)

        view.findViewById<Button>(R.id.buttonSave).setOnClickListener {
            onSaveCLick()
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment taskCreateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            taskCreateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    } //end companion


    private fun onSaveCLick() {

        val description = editTextDescription.text.toString()

        var emoji: String? = null
        if (radioButtonCompleted.isChecked) emoji = radioButtonCompleted.text.toString()
        if (radioButtonDoToday.isChecked) emoji = radioButtonDoToday.text.toString()
        if (radioButtonUpcoming.isChecked) emoji = radioButtonUpcoming.text.toString()

        var priority: String? = null
        if (radioButtonHigh.isChecked) priority = radioButtonHigh.text.toString()
        if (radioButtonMedium.isChecked) priority = radioButtonMedium.text.toString()
        if (radioButtonLow.isChecked) priority = radioButtonLow.text.toString()

        var state: String? = null
        if (radioButtonOpened.isChecked) state = radioButtonOpened.text.toString()
        if (radioButtonClosed.isChecked) state = radioButtonClosed.text.toString()

        if (description != null) {
            val task = Task(description, emoji, priority, state)
            TaskEntryController.add(task)

        }

        findNavController().popBackStack()
    }

} // end class