package demo.merchant.api.tech.domain.query;

import com.google.common.reflect.TypeToken;

public interface QueryHandler<QueryType extends Query<ResponseType>, ResponseType> {

  ResponseType execute(QueryType query) throws Exception;

  default Class<QueryType> getQueryType() {
    TypeToken<QueryType> type = new TypeToken<>(getClass()) {
    };

    return (Class<QueryType>) type.getRawType();
  }
}
