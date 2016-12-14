package com.happy.happy.dao.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataDictionaryExample {
    /**
     * data_dict
     * @mbg.generated 2016-25-08 18:25:32
     */
    protected String orderByClause;

    /**
     * data_dict
     * @mbg.generated 2016-25-08 18:25:32
     */
    protected boolean distinct;

    /**
     * data_dict
     * @mbg.generated 2016-25-08 18:25:32
     */
    protected List<Criteria> oredCriteria;

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    public DataDictionaryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDictKeyIsNull() {
            addCriterion("dict_key is null");
            return (Criteria) this;
        }

        public Criteria andDictKeyIsNotNull() {
            addCriterion("dict_key is not null");
            return (Criteria) this;
        }

        public Criteria andDictKeyEqualTo(String value) {
            addCriterion("dict_key =", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotEqualTo(String value) {
            addCriterion("dict_key <>", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyGreaterThan(String value) {
            addCriterion("dict_key >", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyGreaterThanOrEqualTo(String value) {
            addCriterion("dict_key >=", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyLessThan(String value) {
            addCriterion("dict_key <", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyLessThanOrEqualTo(String value) {
            addCriterion("dict_key <=", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyLike(String value) {
            addCriterion("dict_key like", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotLike(String value) {
            addCriterion("dict_key not like", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyIn(List<String> values) {
            addCriterion("dict_key in", values, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotIn(List<String> values) {
            addCriterion("dict_key not in", values, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyBetween(String value1, String value2) {
            addCriterion("dict_key between", value1, value2, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotBetween(String value1, String value2) {
            addCriterion("dict_key not between", value1, value2, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictTextIsNull() {
            addCriterion("dict_text is null");
            return (Criteria) this;
        }

        public Criteria andDictTextIsNotNull() {
            addCriterion("dict_text is not null");
            return (Criteria) this;
        }

        public Criteria andDictTextEqualTo(String value) {
            addCriterion("dict_text =", value, "dictText");
            return (Criteria) this;
        }

        public Criteria andDictTextNotEqualTo(String value) {
            addCriterion("dict_text <>", value, "dictText");
            return (Criteria) this;
        }

        public Criteria andDictTextGreaterThan(String value) {
            addCriterion("dict_text >", value, "dictText");
            return (Criteria) this;
        }

        public Criteria andDictTextGreaterThanOrEqualTo(String value) {
            addCriterion("dict_text >=", value, "dictText");
            return (Criteria) this;
        }

        public Criteria andDictTextLessThan(String value) {
            addCriterion("dict_text <", value, "dictText");
            return (Criteria) this;
        }

        public Criteria andDictTextLessThanOrEqualTo(String value) {
            addCriterion("dict_text <=", value, "dictText");
            return (Criteria) this;
        }

        public Criteria andDictTextLike(String value) {
            addCriterion("dict_text like", value, "dictText");
            return (Criteria) this;
        }

        public Criteria andDictTextNotLike(String value) {
            addCriterion("dict_text not like", value, "dictText");
            return (Criteria) this;
        }

        public Criteria andDictTextIn(List<String> values) {
            addCriterion("dict_text in", values, "dictText");
            return (Criteria) this;
        }

        public Criteria andDictTextNotIn(List<String> values) {
            addCriterion("dict_text not in", values, "dictText");
            return (Criteria) this;
        }

        public Criteria andDictTextBetween(String value1, String value2) {
            addCriterion("dict_text between", value1, value2, "dictText");
            return (Criteria) this;
        }

        public Criteria andDictTextNotBetween(String value1, String value2) {
            addCriterion("dict_text not between", value1, value2, "dictText");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Short value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Short value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Short value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Short value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Short value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Short value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Short> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Short> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Short value1, Short value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Short value1, Short value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * Corresponds to the database table { data_dict }
     * @mbg.generated 2016-25-08 18:25:32
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}