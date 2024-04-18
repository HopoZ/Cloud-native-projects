package org.software.productclient.common.handler;


import cn.hutool.json.JSONUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * lsit转换string
 * </p>
 */
public class ListHandler extends BaseTypeHandler<List> {


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List list, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i, JSONUtil.toJsonStr(list));
    }

    @Override
    public List getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return JSONUtil.parseArray(resultSet.getString(s));
    }

    @Override
    public List getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return JSONUtil.parseArray(resultSet.getString(i));
    }

    @Override
    public List getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return JSONUtil.parseArray(callableStatement.getString(i));
    }


}


