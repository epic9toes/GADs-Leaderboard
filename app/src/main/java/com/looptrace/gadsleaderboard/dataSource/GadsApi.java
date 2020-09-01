package com.looptrace.gadsleaderboard.dataSource;

import androidx.lifecycle.MutableLiveData;

import com.looptrace.gadsleaderboard.models.Hour;
import com.looptrace.gadsleaderboard.models.Skill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GadsApi {

    @GET("hours")
    Call<List<Hour>> getHours();

    @GET("skilliq")
    Call<List<Skill>> getSkill();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Void> submitForm(
            @Field("entry.1824927963") String EmailAddress,
            @Field("entry.1877115667") String Name,
            @Field("entry.2006916086") String LastName,
            @Field("entry.284483984") String ProjectLink
    );


}
