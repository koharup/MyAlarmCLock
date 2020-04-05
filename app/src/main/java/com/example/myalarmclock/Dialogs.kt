package com.example.myalarmclock

import android.content.Context
import androidx.fragment.app.DialogFragment


class TimeAlertDialog : DialogFragment(){
    interface  Listener{
        fun getUp()
        fun snooze()


    }
    private var listener:Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context){
            is Listener -> listener = context
        }
    }
}

