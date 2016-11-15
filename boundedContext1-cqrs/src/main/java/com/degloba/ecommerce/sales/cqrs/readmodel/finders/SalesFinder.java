package com.degloba.ecommerce.sales.cqrs.readmodel.finders;

import static com.google.common.collect.Lists.transform;

import java.util.ArrayList;
import java.util.List;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;

import com.degloba.cqrs.query.PaginatedResult;
import com.degloba.domain.annotations.FinderImpl;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.offer.cqrs.readmodel.OfferQuery;
import com.degloba.ecommerce.sales.offer.cqrs.readmodel.dtos.OfferedProductDto;
import com.degloba.ecommerce.sales.orders.cqrs.readmodel.OrderQuery;
import com.degloba.ecommerce.sales.orders.cqrs.readmodel.OrderStatus;
import com.degloba.ecommerce.sales.orders.cqrs.readmodel.dtos.OrderDto;
import com.degloba.ecommerce.sales.orders.cqrs.readmodel.dtos.OrderedProductDto;

// CQRS (ecommerce)

import com.degloba.ecommerce.sales.purchase.domain.persistence.rdbms.jpa.Purchase;
import com.degloba.ecommerce.sales.reservation.domain.persistence.rdbms.jpa.Reservation;
import com.degloba.ecommerce.sales.reservation.domain.persistence.rdbms.jpa.ReservedProduct;
import com.google.common.base.Function;




@FinderImpl
public class SalesFinder implements ISalesFinder {

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<OfferedProductDto> find(OfferQuery query) {
		@SuppressWarnings("unused")
		boolean bestBeforeExpired = query.isBestBeforeExpired();
		// TODO take into consideration in query

		return (List<OfferedProductDto>) entityManager
				.createQuery(
						"SELECT NEW com.degloba.ecommerce.sales.readmodel.offer.OfferedProductDto(p.aggregateId) FROM Product p")
				.getResultList();
	}
	
	@Override
	public OrderDto find(AggregateId orderId) {
		Reservation reservation = entityManager.find(Reservation.class, orderId);
		Purchase purchase = entityManager.find(Purchase.class, orderId);
		
		return toOrderDto(reservation, purchase);
	}

	private OrderDto toOrderDto(Reservation reservation, Purchase purchase) {
		OrderDto dto = new OrderDto();
		dto.setOrderId(reservation.getAggregateId());
		List<ReservedProduct> reservedProducts = reservation.getReservedProducts();
		dto.setOrderedProducts(new ArrayList<OrderedProductDto>(transform(reservedProducts,
				reservedProductToOrderedProductDto())));
		if (purchase != null) {
			dto.setStatus(OrderStatus.CONFIRMED);

			// TODO CHECK PAYMENT!
			
		} else {
			dto.setStatus(OrderStatus.NEW);
		}
		return dto;
	}

	private static Function<ReservedProduct, OrderedProductDto> reservedProductToOrderedProductDto() {
		return new Function<ReservedProduct, OrderedProductDto>() {
			public OrderedProductDto apply(ReservedProduct product) {
				OrderedProductDto dto = new OrderedProductDto();
				dto.setOfferId(product.getProductId());
				return dto;
			}
		};
	}

	@Override
	public PaginatedResult<OrderDto> query(OrderQuery orderQuery) {
		return null;
	}

}
