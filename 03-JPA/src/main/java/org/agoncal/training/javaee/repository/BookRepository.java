package org.agoncal.training.javaee.repository;

import org.agoncal.training.javaee.model.Book;
import org.apache.deltaspike.data.api.AbstractFullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public abstract class BookRepository extends AbstractFullEntityRepository<Book, Long>{

}
