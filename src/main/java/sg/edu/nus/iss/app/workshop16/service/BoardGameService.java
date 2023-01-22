package sg.edu.nus.iss.app.workshop16.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.app.workshop16.model.Mastermind;
@Service
public class BoardGameService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    //Task 1
    public int saveGame(final Mastermind mds) {
        System.out.println("mds " + mds.toJSON().toString());
        redisTemplate.opsForValue().set(mds.getId(), mds.toJSON().toString());
        String result = (String) redisTemplate.opsForValue().get(mds.getId());
        if (result != null)
            return 1;
        return 0;
    }
    //Task 2
    public Mastermind findById(final String msid) throws IOException {
        String mdsStr = (String)redisTemplate.opsForValue().get(msid);
        Mastermind m = Mastermind.create(mdsStr);
        return m;
    }
    //Task 3
    public int update(final Mastermind mds) {
        System.out.println("upsert ? " + mds.isUpSert());
        if (mds.isUpSert()) {
            //if key exists, UPDATE record
            System.out.println("upsert is true");
            redisTemplate.opsForValue().set(mds.getId(), mds.toJSON().toString());
        } else {
            //if key is absent, INSERT record
            System.out.println("upsert is false");
            redisTemplate.opsForValue().setIfAbsent(mds.getId(), mds.toJSON().toString());
        }
        String result = (String) redisTemplate.opsForValue()
                .get(mds.getId());

        if (null != result)
            return 1;
        return 0;
    }
}
