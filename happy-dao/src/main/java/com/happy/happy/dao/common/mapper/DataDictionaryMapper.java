package com.happy.happy.dao.common.mapper;

import com.happy.happy.dao.common.model.DataDictionary;
import com.happy.happy.dao.common.model.DataDictionaryExample;
import org.apache.ibatis.annotations.Param;

public interface DataDictionaryMapper {
    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    int insert(DataDictionary record);

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    int insertSelective(DataDictionary record);

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    DataDictionary selectByPrimaryKey(Integer id);

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    int updateByExampleSelective(@Param("record") DataDictionary record, @Param("example") DataDictionaryExample example);

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    int updateByExample(@Param("record") DataDictionary record, @Param("example") DataDictionaryExample example);

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    int updateByPrimaryKeySelective(DataDictionary record);

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    int updateByPrimaryKey(DataDictionary record);
}