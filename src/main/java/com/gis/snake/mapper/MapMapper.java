package com.gis.snake.mapper;

import com.gis.snake.pojo.TbScenicReviewInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MapMapper {

    @Insert("insert into tb_scenic_review_info values(#{reviewId},#{author},#{authorProfileUrl},#{picInfo},#{review},#{score},#{srcName},#{time},#{sid})")
    Integer insertScenicReviewInfo(TbScenicReviewInfo tbScenicReviewInfo);

    @Select("select count(*) from tb_scenic_review_info where sid =#{sid}")
    int selectCountReview(@Param("sid") String id);

}
