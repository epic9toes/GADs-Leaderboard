package com.looptrace.gadsleaderboard.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.looptrace.gadsleaderboard.Repositories.HourRepo;
import com.looptrace.gadsleaderboard.models.Hour;

import java.util.List;

public class HourViewModel extends ViewModel {
    private MutableLiveData<List<Hour>> mHoursLiveData;
    private MutableLiveData<Throwable> mThrowableMutableLiveData;
    private MutableLiveData<String> mErrorCodeMutable;
    private HourRepo mHourRepo;

    public HourViewModel() {
        init();
    }

    private void init() {
        if (mHoursLiveData != null) {
            return;
        }
        mHourRepo = HourRepo.getInstance();
        mHoursLiveData = mHourRepo.GetHours();
        mThrowableMutableLiveData = mHourRepo.GetThrowableError();
        mErrorCodeMutable = mHourRepo.GetErrorCode();
    }

    public LiveData<List<Hour>> getHours() {
        return mHoursLiveData;
    }

    public LiveData<Throwable> getThrowableError() {
        return mThrowableMutableLiveData;
    }

    public LiveData<String> getErrorCode() {
        return mErrorCodeMutable;
    }

    public void Retry() {
        mHoursLiveData = mHourRepo.GetHours();
    }
}

