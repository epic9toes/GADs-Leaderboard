package com.looptrace.gadsleaderboard.views;

import com.looptrace.gadsleaderboard.models.Skill;

import java.util.List;

public interface SkillIQView {

    void GetSkills(List<Skill> skills);

    void OnError(Throwable throwable);

    void OnEmptyResult(String Message);
}
