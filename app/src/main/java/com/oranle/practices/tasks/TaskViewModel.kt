package com.oranle.practices.tasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oranle.practices.data.Task
import java.util.*

class TaskViewModel: ViewModel() {

    val Tag = this.javaClass.simpleName

    val taskId = MutableLiveData<String>()
    val taskName = MutableLiveData<String>()
    val taskCotent = MutableLiveData<String>()

    val taskState = MutableLiveData<Boolean>()

    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task> = _task

    private val _snackBarText = MutableLiveData<String>()
    val snackbarText = _snackBarText


    fun saveTask() {

        taskId.value = UUID.randomUUID().toString()

        val id = taskId.value
        val name = taskName.value
        val content = taskCotent.value
        val state = taskState.value

        Log.d(Tag, "complete task id: ${taskId.value}," +
                " ${taskName.value}, ${taskCotent.value}, ${taskState.value}")

        if (id == null || name == null || content.isNullOrBlank() || state == null) {
            _snackBarText.value = "complete info first!"
            return
        }

        createTask(Task(id, name, content, state))
    }

    private fun createTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}