package com.jeeldesai.android.feedyou.ui

import androidx.appcompat.widget.Toolbar

interface OnToolbarInflated {

    fun onToolbarInflated(toolbar: Toolbar, isNavigableUp: Boolean = true)
}