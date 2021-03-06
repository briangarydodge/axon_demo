package com.dodgeb.axon_demo.config;

import org.hibernate.dialect.PostgreSQL94Dialect;
import org.hibernate.type.descriptor.sql.BinaryTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

import java.sql.Types;

public class AxonPostgreSQLDialect extends PostgreSQL94Dialect {

    public AxonPostgreSQLDialect() {
        super();
        this.registerColumnType(Types.BLOB, "BYTEA");
    }

    @Override
    public SqlTypeDescriptor remapSqlTypeDescriptor(SqlTypeDescriptor sqlTypeDescriptor) {
        if (sqlTypeDescriptor.getSqlType() == java.sql.Types.BLOB) {
            return BinaryTypeDescriptor.INSTANCE;
        }
        return super.remapSqlTypeDescriptor(sqlTypeDescriptor);
    }


}
