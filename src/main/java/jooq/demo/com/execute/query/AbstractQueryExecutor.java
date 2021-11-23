package jooq.demo.com.execute.query;

import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Batch;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Result;
import org.jooq.ResultQuery;
import org.jooq.TableRecord;
import org.jooq.UpdatableRecord;
import org.springframework.util.Assert;

@Slf4j
public abstract class AbstractQueryExecutor implements QueryExecutor {

  protected final DSLContext dslContext;

  protected AbstractQueryExecutor(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  @Override
  public int execute(Query query) {
    Assert.notNull(query, "Query not null");
    return dslContext.execute(query);
  }

  @Override
  public Result<?> fetch(ResultQuery<?> query) {
    return dslContext.fetch(query);
  }

  @Override
  public <R extends Record> R fetchOne(ResultQuery<R> query) {
    return dslContext.fetchOne(query);
  }

  @Override
  public int insertBatch(Collection<? extends TableRecord<?>> records) {
    Batch batch = dslContext.batchInsert(records);
    batch.execute();
    return batch.size();
  }

  @Override
  public int updateBatch(Collection<? extends UpdatableRecord<?>> records) {
    Batch batch = dslContext.batchUpdate(records);
    batch.execute();
    return  batch.size();
  }

  @Override
  public <E> List<E> fetchInto(ResultQuery<?> resultQuery, Class<E> clazz) {
    return dslContext.fetch(resultQuery).into(clazz);
  }

  @Override
  public <E, R extends Record> List<E> fetchInto(ResultQuery<R> resultQuery,
      RecordMapper<Record, E> mapper) {
    return dslContext.fetch(resultQuery).map(mapper);
  }
}
