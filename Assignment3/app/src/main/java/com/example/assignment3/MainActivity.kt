package com.example.assignment3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import com.example.assignment3.TaskEntryController.taskEntries
import com.example.assignment3.model.Task
import com.google.gson.Gson
import java.io.File

private const val TAG = "JSONTaskFile"
private const val FILENAME = "task.json"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

/*
    override fun onDestroy() {
        super.onDestroy()

        val gson = Gson()
        var taskJSON = gson.toJson(taskEntries)

        Log.d(TAG, "task as JSON: $taskJSON")

        val path = this.getExternalFilesDir(null)
        val file = File(path, FILENAME)

        if(!file.exists()) {
            file.createNewFile()
        }

        file.writeText(taskJSON)

        Log.d(TAG, "finished writing task JSON")
    }
*/

/*
    override fun onStart() {
        super.onStart()

        val path = this.getExternalFilesDir(null)
        val file = File(path, FILENAME)

        if (file.exists()) {

            val bufferedReader = file.bufferedReader()

            val taskJSON = bufferedReader.use { it.readText() }

            Log.d(TAG, "task data read : $taskJSON")

            val gson = Gson()
            val task = gson.fromJson<Task>(taskJSON, Task::class.java)

            Log.d(TAG, "saved task description is ${task.description}")
        }
        else {
            Log.d(TAG, "no task saved")
        }
    }
*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_add -> {
                findNavController(R.id.fragment_item_list).navigate(R.id.action_taskListFragment_to_taskCreateFragment)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
} //end class