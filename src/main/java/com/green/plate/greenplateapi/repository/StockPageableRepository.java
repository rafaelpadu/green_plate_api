package com.green.plate.greenplateapi.repository;

import com.green.plate.greenplateapi.model.Stock;
import com.green.plate.greenplateapi.model.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StockPageableRepository extends PagingAndSortingRepository<Stock, Integer> {

    @Query("SELECT DISTINCT s " +
            "FROM Stock s " +
            "join s.store st " +
            "join s.product p " +
            "WHERE st.tradeName ILIKE CONCAT('%', :queryText, '%') OR p.name ILIKE  CONCAT('%', :queryText, '%')")
    List<Stock> findStockByAnything(String queryText, Pageable pageable);
    @Query("SELECT DISTINCT s " +
            "FROM Stock s " +
            "join s.store st " +
            "join s.product p " +
            "WHERE st = :store AND st.tradeName ILIKE CONCAT('%', :queryText, '%') OR p.name ILIKE  CONCAT('%', :queryText, '%')")
    List<Stock> findStockByStoreIdByAnything(Store store, String queryText, Pageable pageable);

    List<Stock> findByStoreAndProduct_ActiveTrue(Store store, Pageable pageable);
}
