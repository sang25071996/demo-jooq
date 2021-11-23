package jooq.demo.com.execute.query;

import java.util.Collection;
import java.util.List;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Result;
import org.jooq.ResultQuery;
import org.jooq.TableRecord;
import org.jooq.UpdatableRecord;

public interface QueryExecutor {

  int execute(Query query);

  Result<?> fetch(ResultQuery<?> query);

  <R extends Record> R fetchOne(ResultQuery<R> query);

  int insertBatch(Collection<? extends TableRecord<?>> records);

  int updateBatch(Collection<? extends UpdatableRecord<?>> records);

  <E> List<E> fetchInto(ResultQuery<?> resultQuery, Class<E> clazz);

  <E, R extends Record> List<E> fetchInto(ResultQuery<R> resultQuery, RecordMapper<Record, E> mapper);
}
