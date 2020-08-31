package com.looptrace.gadsleaderboard.views;

import com.looptrace.gadsleaderboard.models.Hour;

import java.util.List;

public interface HoursView {

    void GetHours(List<Hour> hours);

    void OnError(Throwable throwable);

    void OnEmptyResult(String Message);
}
