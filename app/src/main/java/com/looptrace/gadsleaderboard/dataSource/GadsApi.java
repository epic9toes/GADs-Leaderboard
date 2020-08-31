package com.looptrace.gadsleaderboard.dataSource;

import androidx.lifecycle.MutableLiveData;

import com.looptrace.gadsleaderboard.models.Hour;
import com.looptrace.gadsleaderboard.models.Skill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GadsApi {

    @GET("hours")
    Call<List<Hour>> getHours();

    @GET("skilliq")
    Call<List<Skill>> getSkill();

}
