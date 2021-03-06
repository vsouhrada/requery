/*
 * Copyright 2016 requery.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.requery.sql;

import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

class CompositeStatementListener implements StatementListener {

    private final Set<StatementListener> listeners = new HashSet<>();

    CompositeStatementListener(Set<StatementListener> listeners) {
        if (listeners != null) {
            this.listeners.addAll(listeners);
        }
    }

    public void add(StatementListener listener) {
        listeners.add(listener);
    }

    public void remove(StatementListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void beforeExecuteUpdate(Statement statement, String sql, BoundParameters parameters) {
        for (StatementListener interceptor : listeners) {
            interceptor.beforeExecuteUpdate(statement, sql, parameters);
        }
    }

    @Override
    public void afterExecuteUpdate(Statement statement, int count) {
        for (StatementListener interceptor : listeners) {
            interceptor.afterExecuteUpdate(statement, count);
        }
    }

    @Override
    public void beforeExecuteBatchUpdate(Statement statement, String sql) {
        for (StatementListener interceptor : listeners) {
            interceptor.beforeExecuteBatchUpdate(statement, sql);
        }
    }

    @Override
    public void afterExecuteBatchUpdate(Statement statement, int[] count) {
        for (StatementListener interceptor : listeners) {
            interceptor.afterExecuteBatchUpdate(statement, count);
        }
    }

    @Override
    public void beforeExecuteQuery(Statement statement, String sql, BoundParameters parameters) {
        for (StatementListener interceptor : listeners) {
            interceptor.beforeExecuteQuery(statement, sql, parameters);
        }
    }

    @Override
    public void afterExecuteQuery(Statement statement) {
        for (StatementListener interceptor : listeners) {
            interceptor.afterExecuteQuery(statement);
        }
    }
}
