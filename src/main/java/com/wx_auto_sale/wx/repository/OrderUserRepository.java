package com.wx_auto_sale.wx.repository;

import com.wx_auto_sale.wx.model.entity.OrderUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author wangyu
 * @Create: 2020/5/7 11:17 下午
 * @Description:
 */
@Repository
public interface OrderUserRepository extends JpaRepository<OrderUserEntity,String>, JpaSpecificationExecutor {
}
