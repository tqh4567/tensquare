package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao;
    public int selectCount(String userid, String friendid) {
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if(friend!=null){
            return 0;
        }
        friend=new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        if(friendDao.findByUseridAndFriendid(friendid, userid)!=null){
            friendDao.updateIslike("1",userid,friendid);
            friendDao.updateIslike("1",friendid,userid);
        }
        return 1;
    }
}
