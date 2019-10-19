package com.sharing.common;

import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.Set;

public class Const {
    public static final String CURRENT_USER="currentUser";
    public static final String EMAIL="email";
    public static final String USERNAME="username";
    public static final String TOKEN_PREFIX="token_";

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC= Sets.newHashSet("price_desc","price_asc");
    }

    public enum OrderStatusEnum{
        PUBLISHED(0,"published"),
        ACCEPTED(10,"accepted"),
        PAST(20,"past"),
        ORDER_SUCCESS(30,"finish"),
        CANCELED(40,"cancel");


        OrderStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static OrderStatusEnum codeOf(int code){
            for(OrderStatusEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("no corresponding enum");
        }
    }

    public enum tag{
        STUDY(0,"study"),
        OFFICE(10,"office"),
        SPORT(20,"sport"),
        ELECTRONIC(30,"electronic"),
        HOME(40,"home");


        tag(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static tag codeOf(int code){
            for(tag t : values()){
                if(t.getCode() == code){
                    return t;
                }
            }
            throw new RuntimeException("no corresponding enum");
        }
    }

    public interface RedisCacheExtime{
        int REDIS_SESSION_EXTIME = 60 * 30;
    }

    public interface REDIS_LOCK{
        String CLOSE_ORDER_TASK_LOCK="CLOSE_ORDER_TASK_LOCK";
    }

    public interface PositionRange{
        BigDecimal RANGE=new BigDecimal("0.001");
    }

    public interface PageSettings{
        int PAGE_NUMBER=1;
        int PAGE_SIZE=10;
    }

}
