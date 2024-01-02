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
import org.springframework.shell.ShellApplicationRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        return 0;
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