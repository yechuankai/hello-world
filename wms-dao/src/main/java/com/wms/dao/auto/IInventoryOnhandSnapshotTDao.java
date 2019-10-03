package com.wms.dao.auto;

import com.wms.dao.example.InventoryOnhandSnapshotTExample;
import com.wms.entity.auto.InventoryOnhandSnapshotTEntity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IInventoryOnhandSnapshotTDao {
    long countByExample(InventoryOnhandSnapshotTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INVENTORY_ONHAND_SNAPSHOT_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int deleteWithVersionByExample(@Param("version") Long version, @Param("example") InventoryOnhandSnapshotTExample example);

    int deleteByExample(InventoryOnhandSnapshotTExample example);

    int insert(InventoryOnhandSnapshotTEntity record);

    int insertSelective(InventoryOnhandSnapshotTEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INVENTORY_ONHAND_SNAPSHOT_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    InventoryOnhandSnapshotTEntity selectOneByExample(InventoryOnhandSnapshotTExample example);

    List<InventoryOnhandSnapshotTEntity> selectByExample(InventoryOnhandSnapshotTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INVENTORY_ONHAND_SNAPSHOT_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int updateWithVersionByExample(@Param("version") Long version, @Param("record") InventoryOnhandSnapshotTEntity record, @Param("example") InventoryOnhandSnapshotTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INVENTORY_ONHAND_SNAPSHOT_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int updateWithVersionByExampleSelective(@Param("version") Long version, @Param("record") InventoryOnhandSnapshotTEntity record, @Param("example") InventoryOnhandSnapshotTExample example);

    int updateByExampleSelective(@Param("record") InventoryOnhandSnapshotTEntity record, @Param("example") InventoryOnhandSnapshotTExample example);

    int updateByExample(@Param("record") InventoryOnhandSnapshotTEntity record, @Param("example") InventoryOnhandSnapshotTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INVENTORY_ONHAND_SNAPSHOT_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") InventoryOnhandSnapshotTExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INVENTORY_ONHAND_SNAPSHOT_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteWithVersionByExample(@Param("version") Long version, @Param("example") InventoryOnhandSnapshotTExample example);
}