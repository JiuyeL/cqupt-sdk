package com.github.juzi214032.cqupt.sdk.jwzx.api;

import com.github.juzi214032.cqupt.sdk.jwzx.bean.JwzxCoursePlan;

import java.util.List;

/**
 * 授课计划
 *
 * @author Juzi - https://juzibiji.top
 * @date 2020/1/9 10:25
 */
public interface JwzxCoursePlanService {

    String COURSE_PLAN_URL = "/student/skjh.php";

    /**
     * 获取授课计划
     *
     * @param username 账号
     * @param password 密码
     * @return 授课计划
     */
    List<JwzxCoursePlan> getCoursePlans(String username, String password);

}
