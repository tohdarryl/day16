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
    //Saves Mastermind object in redis-server
    public int saveGame(final Mastermind mds) {
        System.out.println("mds " + mds.toJSON().toString());
        redisTemplate.opsForValue().set(mds.getId(), mds.toJSON().toString());
        String result = (String) redisTemplate.opsForValue().get(mds.getId());
        if (result != null)
            return 1;
        return 0;
    }
    //Task 2
    //Retrieves Mastermind object in redis-server by ID
    public Mastermind findById(final String msid) throws IOException {
        String mdsStr = (String)redisTemplate.opsForValue().get(msid);
        Mastermind m = Mastermind.create(mdsStr);
        return m;
    }
    //Task 3
    //Updates records in redis-server
    public int update(final Mastermind mds) {
        System.out.println("upsert ? " + mds.isUpSert());
        System.out.println(mds.getId());
        String result = (String) redisTemplate.opsForValue()
                .get(mds.getId());
        if (mds.isUpSert()) {
            System.out.println("upsert is true");
            System.out.println(result);
            //if redisTemplate has any key and value
            if(result != null)
                redisTemplate.opsForValue().set(mds.getId(), mds.toJSON().toString());
            else
            //Otherwise if key is absent in redisTemplate
                redisTemplate.opsForValue().setIfAbsent(mds.getId(), mds.toJSON().toString());
        } else {
            System.out.println("upsert is false");
            if(result != null)
                redisTemplate.opsForValue().set(mds.getId(), mds.toJSON().toString());
        }
        result = (String) redisTemplate.opsForValue()
            .get(mds.getId());

        if (null != result)
            return 1;
        return 0;
    }
}
