package com.tool.cartisianwidget.service.repository;

import com.tool.cartisianwidget.model.CartisianWidget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SQLCartisianWidgetRepository extends JpaRepository<CartisianWidget, Integer> {

    @Query("select max(zcoordinate) from CartisianWidget")
    Integer getMaxZCoordiate();

    @Query("select w from CartisianWidget w where w.zcoordinate >= :zcoordinate order by w.zcoordinate asc")
    List<CartisianWidget> findByZCoordinateGreaterThan(@Param("zcoordinate") Integer zcoordinate);

    @Query("select w from CartisianWidget w where w.zcoordinate = :zcoordinate")
    CartisianWidget findByZCoordinate(@Param("zcoordinate") Integer zcoordinate);
}
