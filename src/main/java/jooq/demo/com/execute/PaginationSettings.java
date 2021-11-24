package jooq.demo.com.execute;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.jooq.Table;

public class PaginationSettings implements Serializable {

  protected boolean isRenderSortDefault = false;
  protected String sortFieldDefault = "";
  protected Map<Table, Table> tableMapExtractor = new HashMap<>();

  public void setRenderSortDefault(boolean renderSortDefault) {
    isRenderSortDefault = renderSortDefault;
  }

  public void setTableMapExtractor(Map<Table, Table> tableMapExtractor) {
    this.tableMapExtractor = tableMapExtractor;
  }

  public String getSortFieldDefault() {
    return sortFieldDefault;
  }

  public void setSortFieldDefault(String sortFieldDefault) {
    this.sortFieldDefault = sortFieldDefault;
  }

  public boolean isRenderSortDefault() {
    return isRenderSortDefault;
  }

  public Map<Table, Table> getTableMapExtractor() {
    return tableMapExtractor;
  }
}
