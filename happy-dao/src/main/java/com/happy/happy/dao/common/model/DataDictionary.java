package com.happy.happy.dao.common.model;

import java.util.Date;

/**
 * Corresponds to the database table { data_dict }
 * @mbg.generated 2016-25-08 18:25:32
 */
public class DataDictionary {
    /**
     * data_dict.id
     * primary key
     * @mbg.generated 2016-25-08 18:25:32
     */
    private Integer id;

    /**
     * data_dict.dict_key
     * the key of dictionary data entry
     * @mbg.generated 2016-25-08 18:25:32
     */
    private String dictKey;

    /**
     * data_dict.dict_text
     * the text of dictionary data entry
     * @mbg.generated 2016-25-08 18:25:32
     */
    private String dictText;

    /**
     * data_dict.create_time
     * time of creating data
     * @mbg.generated 2016-25-08 18:25:32
     */
    private Date createTime;

    /**
     * data_dict.is_deleted
     * is deleted or not. 0:no,1:yes
     * @mbg.generated 2016-25-08 18:25:32
     */
    private Short isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey == null ? null : dictKey.trim();
    }

    public String getDictText() {
        return dictText;
    }

    public void setDictText(String dictText) {
        this.dictText = dictText == null ? null : dictText.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }
}