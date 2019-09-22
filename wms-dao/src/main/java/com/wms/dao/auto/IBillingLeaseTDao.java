package com.wms.dao.auto;

import com.wms.dao.example.BillingLeaseTExample;
import com.wms.entity.auto.BillingLeaseTEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IBillingLeaseTDao {
    long countByExample(BillingLeaseTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int deleteWithVersionByExample(@Param("version") Long version, @Param("example") BillingLeaseTExample example);

    int deleteByExample(BillingLeaseTExample example);

    int insert(BillingLeaseTEntity record);

    int insertSelective(BillingLeaseTEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    BillingLeaseTEntity selectOneByExample(BillingLeaseTExample example);

    List<BillingLeaseTEntity> selectByExample(BillingLeaseTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int updateWithVersionByExample(@Param("version") Long version, @Param("record") BillingLeaseTEntity record, @Param("example") BillingLeaseTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int updateWithVersionByExampleSelective(@Param("version") Long version, @Param("record") BillingLeaseTEntity record, @Param("example") BillingLeaseTExample example);

    int updateByExampleSelective(@Param("record") BillingLeaseTEntity record, @Param("example") BillingLeaseTExample example);

    int updateByExample(@Param("record") BillingLeaseTEntity record, @Param("example") BillingLeaseTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") BillingLeaseTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteWithVersionByExample(@Param("version") Long version, @Param("example") BillingLeaseTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Long billingLeaseId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteWithVersionByPrimaryKey(@Param("version") Long version, @Param("key") Long billingLeaseId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    BillingLeaseTEntity selectByPrimaryKeyWithLogicalDelete(@Param("billingLeaseId") Long billingLeaseId, @Param("andLogicalDeleted") boolean andLogicalDeleted);
}