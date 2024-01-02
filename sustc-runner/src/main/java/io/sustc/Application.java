package io.sustc;

import io.sustc.benchmark.BenchmarkService;
import io.sustc.dto.*;
import io.sustc.service.DanmuService;
import io.sustc.service.DatabaseService;
import io.sustc.service.UserService;
import io.sustc.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.shell.ShellApplicationRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import java.sql.*;
@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener
    public void onApplicationReady(ApplicationStartedEvent event) {
        val ctx = event.getApplicationContext();
        val runnerBeans = ctx.getBeansOfType(ShellApplicationRunner.class);
        val benchmarkServiceBeans = ctx.getBeansOfType(BenchmarkService.class);
        log.debug("{} {}", runnerBeans, benchmarkServiceBeans);
        if (runnerBeans.size() != 1 || benchmarkServiceBeans.size() != 1) {
            log.error("Failed to verify beans");
            SpringApplication.exit(ctx, () -> 1);
        }
    }
}


class DanmuS implements DanmuService{
    @Override
    public long sendDanmu(AuthInfo auth, String bv, String content, float time) {
        return 0;
    }

    @Override
    public List<Long> displayDanmu(String bv, float timeStart, float timeEnd, boolean filter) {
        return null;
    }

    @Override
    public boolean likeDanmu(AuthInfo auth, long id) {
        return false;
    }
}

class DatabaseS implements DatabaseService{
    @Override
    public List<Integer> getGroupMembers() {
        List<Integer> GroupMembersID = new ArrayList<>();
        GroupMembersID.add(12212721);
        GroupMembersID.add(12212723);
        return GroupMembersID;
    }

    @Override
    public void importData(List<DanmuRecord> danmuRecords, List<UserRecord> userRecords, List<VideoRecord> videoRecords) {

    }

    @Override
    public void truncate() {

    }


    @Override
    public Integer sum(int a, int b) {
        return null;
    }
}

class UserS implements UserService{
    @Override
    public long register(RegisterUserReq req) {
        String password = req.getPassword();
        String qq = req.getQq();
        String wechat = req.getWechat();
        String name = req.getName();
        RegisterUserReq.Gender sex = req.getSex();
        String birthday = req.getBirthday();
        String sign = req.getSign();
        long mid = (long) -1;
        if(checknotnull(password) && checknotnull(name) && checksex(sex) && checkbirthday(birthday)){
            //缺了验证qq和wechat唯一性

            mid = (long) 00;//不知道怎么生成新的mid
        }
        return mid;
    }
    public boolean checkbirthday(String birthday){
        int l = birthday.length();
        String day = birthday.substring(l-1,l);
        String m1 = birthday.substring(0,1);
        String m2 = birthday.substring(1,2);
        if(day.equals("日") && (m1.equals("月") || m2.equals("月")) && checknotnull(birthday)){
            return true;
        }else {
            return false;
        }
    }
    public boolean checksex(RegisterUserReq.Gender sex){
        if(sex== RegisterUserReq.Gender.MALE || sex == RegisterUserReq.Gender.FEMALE || sex == RegisterUserReq.Gender.UNKNOWN){
            return true;
        }else {
            return false;
        }
    }
    public boolean checknotnull(String item){
        if(item.length()==0 || item.equals(null)){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean deleteAccount(AuthInfo auth, long mid) {
        return false;
    }

    @Override
    public boolean follow(AuthInfo auth, long followeeMid) {
        return false;
    }

    @Override
    public UserInfoResp getUserInfo(long mid) {
//        Connection con =
        return null;
    }
}

class VideoS implements VideoService{
    @Override
    public String postVideo(AuthInfo auth, PostVideoReq req) {
        return null;
    }

    @Override
    public boolean deleteVideo(AuthInfo auth, String bv) {
        return false;
    }

    @Override
    public boolean updateVideoInfo(AuthInfo auth, String bv, PostVideoReq req) {
        return false;
    }

    @Override
    public List<String> searchVideo(AuthInfo auth, String keywords, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public double getAverageViewRate(String bv) {
        return 0;
    }

    @Override
    public Set<Integer> getHotspot(String bv) {
        return null;
    }

    @Override
    public boolean reviewVideo(AuthInfo auth, String bv) {
        return false;
    }

    @Override
    public boolean coinVideo(AuthInfo auth, String bv) {
        return false;
    }

    @Override
    public boolean collectVideo(AuthInfo auth, String bv) {
        return false;
    }

    @Override
    public boolean likeVideo(AuthInfo auth, String bv) {
        return false;
    }
}