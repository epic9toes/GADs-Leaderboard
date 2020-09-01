package com.looptrace.gadsleaderboard.views;

import androidx.lifecycle.MutableLiveData;

import com.looptrace.gadsleaderboard.models.Hour;

import java.util.List;

public interface HoursView {

    Throwable OnError(Throwable throwable);

    String OnEmptyResult(String Message);
}
