package com.happy.happy.dao.user.mapper;

import com.happy.happy.dao.user.model.WebUser;
import com.happy.happy.dao.user.model.WebUserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface WebUserMapper {
    /**
     * Corresponds to the database table { web_user }
     * @mbg.generated 2016-26-08 14:59:54
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * Corresponds to the database table { web_user }
     * @mbg.generated 2016-26-08 14:59:54
     */
    int insert(WebUser record);

    /**
     * Corresponds to the database table { web_user }
     * @mbg.generated 2016-26-08 14:59:54
     */
    int insertSelective(WebUser record);

    /**
     * Corresponds to the database table { web_user }
     * @mbg.generated 2016-26-08 14:59:54
     */
    WebUser selectByPrimaryKey(Integer id);

    /**
     * Corresponds to the database table { web_user }
     * @mbg.generated 2016-26-08 14:59:54
     */
    int updateByExampleSelective(@Param("record") WebUser record, @Param("example") WebUserExample example);

    /**
     * Corresponds to the database table { web_user }
     * @mbg.generated 2016-26-08 14:59:54
     */
    int updateByExample(@Param("record") WebUser record, @Param("example") WebUserExample example);

    /**
     * Corresponds to the database table { web_user }
     * @mbg.generated 2016-26-08 14:59:54
     */
    int updateByPrimaryKeySelective(WebUser record);

    /**
     * Corresponds to the database table { web_user }
     * @mbg.generated 2016-26-08 14:59:54
     */
    int updateByPrimaryKey(WebUser record);
}