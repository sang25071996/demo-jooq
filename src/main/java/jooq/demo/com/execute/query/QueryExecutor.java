package jooq.demo.com.execute.query;

import java.util.Collection;
import java.util.List;
import org.jooq.Condition;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Result;
import org.jooq.ResultQuery;
import org.jooq.Table;
import org.jooq.TableRecord;
import org.jooq.UpdatableRecord;

public interface QueryExecutor {

  int execute(Query query);

  Result<?> fetch(ResultQuery<?> query);

  <R extends Record> R fetchOne(ResultQuery<R> query);

  int batchInsert(Collection<? extends TableRecord<?>> records);

  <R extends Record> R insert(Table<?> table, Record record);

  void insert(Table<?> table, Collection<? extends TableRecord<?>> records);

  int update(TableRecord<?> tableRecord, Condition condition);

  int batchUpdate(Collection<? extends UpdatableRecord<?>> records);

  int update(UpdatableRecord<?> updatableRecord);

  <E> List<E> fetchInto(ResultQuery<?> resultQuery, Class<E> clazz);

  <E, R extends Record> List<E> fetchInto(ResultQuery<R> resultQuery, RecordMapper<Record, E> mapper);
}
