package com.argusoft.abdmhackathon.common.util;

import org.hibernate.sql.JoinType;

/**
 *
 * @author jay
 */
public interface IJoinEnum {

    public String getValue();

    public String getAlias();

    public JoinType getJoinType();

}
