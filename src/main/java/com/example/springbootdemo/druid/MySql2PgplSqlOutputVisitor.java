package com.example.springbootdemo.druid;

/**
 * @author gaosen
 * @since 2023/7/28 15:01
 */
import com.alibaba.druid.sql.ast.*;
import com.alibaba.druid.sql.ast.statement.*;

import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;

public class MySql2PgplSqlOutputVisitor extends MySqlOutputVisitor {

    public MySql2PgplSqlOutputVisitor(Appendable appender) {
        super(appender);
    }

    public MySql2PgplSqlOutputVisitor(Appendable appender, boolean parameterized) {
        super(appender, parameterized);

        try {
            configFromProperty();
        } catch (Exception e) {
            // skip
        }
    }

    @Override
    public boolean visit(SQLAssignItem x) {

        x.getTarget().accept(this);
        print0(" := ");

        x.getValue().accept(this);
        return false;
    }

    @Override
    public boolean visit(SQLSetStatement x) {
        SQLSetStatement.Option option = x.getOption();
        if (option != null) {
            print(option.name());
            print(' ');
        }

        if (option == SQLSetStatement.Option.PASSWORD) {
            print0("FOR ");
        }

        printAndAccept(x.getItems(), ", ");

        if (x.getHints() != null && x.getHints().size() > 0) {
            print(' ');
            printAndAccept(x.getHints(), " ");
        }

        return false;
    }

    @Override
    public boolean visit(SQLWhileStatement x) {
        String label = x.getLabelName();

        if (label != null && label.length() != 0) {
            print0(x.getLabelName());
            print0(": ");
        }
        print0(ucase ? "WHILE " : "while ");
        x.getCondition().accept(this);
        print0(ucase ? " LOOP" : " loop");
        println();
        for (int i = 0, size = x.getStatements().size(); i < size; ++i) {
            SQLStatement item = x.getStatements().get(i);
            item.accept(this);
            if (i != size - 1) {
                println();
            }
        }
        println();
        print0(ucase ? "END LOOP" : "end loop");
        if (label != null && label.length() != 0) {
            print(' ');
            print0(label);
        }
        return false;
    }
} //

