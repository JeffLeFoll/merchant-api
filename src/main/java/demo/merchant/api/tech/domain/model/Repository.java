package demo.merchant.api.tech.domain.model;

import java.util.Optional;

public interface Repository<IdType, ElementType> {

  void add(ElementType element);

  void delete(ElementType element);

  Optional<ElementType> get(IdType id);

}
