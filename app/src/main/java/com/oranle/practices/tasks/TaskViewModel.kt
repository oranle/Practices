package com.oranle.practices.tasks

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oranle.practices.base.BaseViewModel
import com.oranle.practices.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class TaskViewModel : BaseViewModel() {

    val Tag = this.javaClass.simpleName

    val taskId = MutableLiveData<String>()
    val taskName = MutableLiveData<String>()
    val taskCotent = MutableLiveData<String>()

    val taskState = MutableLiveData<Boolean>()

    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task> = _task

    private val _snackBarText = MutableLiveData<String>()
    val snackbarText = _snackBarText

    override fun start(context: Context) {

        viewModelScope.launch(Dispatchers.IO) {
            val dataBase = getDB(context)

            val tasks = dataBase.taskDao().getTasks()
            val task = tasks[tasks.size - 1]
            taskId.postValue(task.id)
            taskName.postValue(task.name)
            taskCotent.postValue(task.content)
            taskState.postValue(task.isCompleted)
            Timber.v("${Thread.currentThread().name} task----- $task -----")

        }

    }

    fun saveTask(view: View) {
        taskId.value = UUID.randomUUID().toString()

        val id = taskId.value
        val name = taskName.value
        val content = taskCotent.value
        val state = taskState.value

        Log.d(Tag, "complete task id: ${taskId.value}, ${taskName.value}, ${taskCotent.value}, ${taskState.value}")

        if (id == null || name == null || content.isNullOrBlank() || state == null) {
            _snackBarText.value = "complete info first!"
            return
        }

        Timber.d("start save")

        val task1 = Task(id, name, content, state)
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("save...")
            try {
                getDB(view.context).taskDao().addTask(task1)
            } catch (e: Exception) {
                e.printStackTrace()
                Timber.d(e)
                Timber.d("Exception...")
            }
            Timber.d("end...")
        }
    }

}