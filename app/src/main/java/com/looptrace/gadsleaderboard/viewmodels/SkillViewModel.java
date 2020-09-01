package com.looptrace.gadsleaderboard.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.looptrace.gadsleaderboard.Repositories.HourRepo;
import com.looptrace.gadsleaderboard.Repositories.SkillRepo;
import com.looptrace.gadsleaderboard.models.Hour;
import com.looptrace.gadsleaderboard.models.Skill;

import java.util.List;

public class SkillViewModel extends ViewModel {

    private MutableLiveData<List<Skill>> mSkillMutableData;
    private MutableLiveData<Throwable> mThrowableMutableLiveData;
    private MutableLiveData<String> mErrorCodeMutable;
    private SkillRepo mSkillRepo;

    public void init() {
        if (mSkillMutableData != null) {
            return;
        }
        mSkillRepo = SkillRepo.getInstance();
        mSkillMutableData = mSkillRepo.GetSkillIQ();
        mThrowableMutableLiveData = mSkillRepo.GetThrowableError();
        mErrorCodeMutable = mSkillRepo.GetErrorCode();
    }

    public LiveData<List<Skill>> getSkill() {
        return mSkillMutableData;
    }

    public LiveData<Throwable> getThrowableError() {
        return mThrowableMutableLiveData;
    }

    public LiveData<String> getErrorCode() {
        return mErrorCodeMutable;
    }

    public void Retry() {
        mSkillMutableData = mSkillRepo.GetSkillIQ();
    }
}

