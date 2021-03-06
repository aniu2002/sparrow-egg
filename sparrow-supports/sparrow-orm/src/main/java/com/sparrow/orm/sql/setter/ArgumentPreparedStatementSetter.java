/**  
 * Project Name:http-server  
 * File Name:ArgumentPreparedStatementSetter.java  
 * Package Name:au.orm.sql
 * Date:2013-12-20上午9:27:44  
 * Copyright (c) 2013, Boco.com All Rights Reserved.  
 *  
 */

package com.sparrow.orm.sql.setter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sparrow.orm.sql.PreparedStatementSetter;
import com.sparrow.orm.sql.SqlParameterValue;
import com.sparrow.orm.util.JdbcUtil;
import com.sparrow.orm.util.sql.StatementUtils;

public class ArgumentPreparedStatementSetter implements PreparedStatementSetter {
	private final Object[] args;

	public ArgumentPreparedStatementSetter(Object[] args) {
		this.args = args;
	}

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		if (this.args != null) {
			for (int i = 0; i < this.args.length; i++) {
				Object arg = this.args[i];
				doSetValue(ps, i + 1, arg);
			}
		}
	}

	protected void doSetValue(PreparedStatement ps, int parameterPosition,
			Object argValue) throws SQLException {
		if (argValue instanceof SqlParameterValue) {
			SqlParameterValue paramValue = (SqlParameterValue) argValue;
			StatementUtils.setParameterValue(ps, parameterPosition, paramValue,
					paramValue.getValue());
		} else {
			int sqlType = JdbcUtil.getSqlType(argValue);
			StatementUtils.setParameterValue(ps, parameterPosition, sqlType,
					argValue);
		}
	}

	public void cleanupParameters() {
	}

	@Override
	public Object[] getParameters() {
		return this.args;
	}
}
