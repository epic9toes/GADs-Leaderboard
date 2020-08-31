package com.looptrace.gadsleaderboard.Repositories;

import androidx.lifecycle.MutableLiveData;

import com.looptrace.gadsleaderboard.dataSource.GadsApi;
import com.looptrace.gadsleaderboard.dataSource.RetrofitBase;
import com.looptrace.gadsleaderboard.models.Hour;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HourRepo {

    private static HourRepo instance = null;
    private List<Hour> mHours = new ArrayList<>();
    private MutableLiveData<Throwable> mThrowableMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> ErrorCode = new MutableLiveData<>();

    public static HourRepo getInstance() {
        if (instance == null) {
            instance = new HourRepo();
        }
        return instance;
    }


    public MutableLiveData<List<Hour>> GetHours() {
        final MutableLiveData<List<Hour>> data = new MutableLiveData<>();

        GadsApi gadsApi = RetrofitBase.getInstance().buildRetrofit().create(GadsApi.class);
        Call<List<Hour>> call = gadsApi.getHours();
        call.enqueue(new Callback<List<Hour>>() {
            @Override
            public void onResponse(Call<List<Hour>> call, Response<List<Hour>> response) {
                if (!response.isSuccessful()) {
                    ErrorCode.setValue("An " + response.code() + " has occurred, please try again later.");
                    GetErrorCode();
                    return;
                }
                mHours = response.body();
                data.setValue(mHours);
            }

            @Override
            public void onFailure(Call<List<Hour>> call, Throwable t) {
                mThrowableMutableLiveData.setValue(t);
                GetThrowableError();
            }
        });
        return data;
    }

    public MutableLiveData<Throwable> GetThrowableError() {
        return mThrowableMutableLiveData;
    }

    public MutableLiveData<String> GetErrorCode() {
        return ErrorCode;
    }


}
