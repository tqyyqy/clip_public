package xyz.tqyyqy.clip_board.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import xyz.tqyyqy.clip_board.entity.TextHistory;

public interface TextHistoryMapper extends BaseMapper<TextHistory> {
    //    @Select("SELECT * FROM `cdxc`.`app_user` where account = #{account} and password = #{password} limit 1;")
//    AppUser findByNameAndPassword(AppUser appUser);
    @Select("SELECT  max(id) from text_history;")
    int getMaxId();

//select max(id) from text_history

}
