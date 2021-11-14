package jooq.demo.com.demojooq.config;

import lombok.extern.slf4j.Slf4j;
import org.jooq.ExecuteContext;
import org.jooq.ExecuteType;
import org.jooq.impl.DefaultExecuteListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class PrettyPrinter extends DefaultExecuteListener {

    public static final Map<ExecuteType, Integer> STATISTICS = new ConcurrentHashMap<>();

    @Override
    public void start(ExecuteContext ctx) {
        STATISTICS.compute(ctx.type(), (k, v) -> v == null ? 1 : v + 1);
    }

    @Override
    public void end(ExecuteContext ctx) {
        // Extract the start time from the current context
        Long time = (Long) ctx.data("time");
        if (time != null) {
            log.debug("Execution time : {}, ms. Query :  {}" + ((System.nanoTime() - time) / 1000 / 1000.0), ctx.sql());
        }
    }

}
