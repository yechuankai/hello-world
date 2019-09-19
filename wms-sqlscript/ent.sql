
-- ----------------------------
-- 企业货主
-- ----------------------------
create or replace view ent_owner_v as
(
select DISTINCT 0 OWNER_ID,
       OWNER_CODE,
       OWNER_DESCR,
       CONTACT1,
       CONTACT2,
       PHONE1,
       PHONE2,
       ADDRESS1,
       ADDRESS2,
       FAX,
       EMAIL1,
	   EMAIL2,
       '' BARCODE_PREFIX,
       0 BARCODE_LENGTH,
       0 BARCODE_START,
       'Y' ACTIVE,
       '' REMARK,
       COMPANY_ID,
       0 WAREHOUSE_ID,
       'N' DEL_FLAG,
       '-1' CREATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') CREATE_TIME,
       '-1' UPDATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') UPDATE_TIME,
       0 UPDATE_VERSION,
       '' DESCRIPTION
  from owner_t
);


-- ----------------------------
-- 企业客户
-- ----------------------------
create or replace view ent_customer_v as
(
select DISTINCT 0 CUSTOMER_ID,
       CUSTOMER_CODE,
       CUSTOMER_DESCR,
       CONTACT1,
       CONTACT2,
       PHONE1,
       PHONE2,
       ADDRESS1,
       ADDRESS2,
       FAX,
       EMAIL1,
	   EMAIL2,
       '' SHIP_LABEL,
       'Y' ACTIVE,
       '' REMARK,
       COMPANY_ID,
       0 WAREHOUSE_ID,
       'N' DEL_FLAG,
       '-1' CREATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') CREATE_TIME,
       '-1' UPDATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') UPDATE_TIME,
       0 UPDATE_VERSION,
       '' DESCRIPTION
  from customer_t
);


-- ----------------------------
-- 企业供应商
-- ----------------------------
create or replace view ent_supplier_v as
(
select DISTINCT 0 SUPPLIER_ID,
       SUPPLIER_CODE,
       SUPPLIER_DESCR,
       CONTACT1,
       CONTACT2,
       PHONE1,
       PHONE2,
       ADDRESS1,
       ADDRESS2,
       FAX,
       EMAIL1,
	   EMAIL2,
       'Y' ACTIVE,
       '' REMARK,
       COMPANY_ID,
       0 WAREHOUSE_ID,
       'N' DEL_FLAG,
       '-1' CREATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') CREATE_TIME,
       '-1' UPDATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') UPDATE_TIME,
       0 UPDATE_VERSION,
       '' DESCRIPTION
  from supplier_t
);



-- ----------------------------
-- 企业供承运人
-- ----------------------------
create or replace view ent_carrier_v as
(
select DISTINCT 0 CARRIER_ID,
       CARRIER_CODE,
       CARRIER_DESCR,
       CONTACT1,
       CONTACT2,
       PHONE1,
       PHONE2,
       ADDRESS1,
       ADDRESS2,
       FAX,
       EMAIL1,
	   EMAIL2,
       'Y' ACTIVE,
       '' REMARK,
       COMPANY_ID,
       0 WAREHOUSE_ID,
       'N' DEL_FLAG,
       '-1' CREATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') CREATE_TIME,
       '-1' UPDATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') UPDATE_TIME,
       0 UPDATE_VERSION,
       '' DESCRIPTION
  from carrier_t
);

-- ----------------------------
-- 企业货品
-- ----------------------------
create or replace view ent_sku_v as
(
select DISTINCT 0 SKU_ID,
       0 OWNER_ID,
       OWNER_CODE,
       SKU_CODE,
       SKU_ALIAS,
       BARCODE,
       SKU_DESCR,
       SKU_TYPE,
       0 PACK_ID,
       '' PACK_CODE,
       '' UOM,
       0 VOLUME,
       0 LENGTH,
       0 WIDTH,
       0 HEIGHT,
       0 WEIGHT_GROSS,
       0 WEIGHT_NET,
       0 WEIGHT_TARE,
       0 LOT_VALIDATE_ID,
       '' LOT_VALIDATE_CODE,
       0 PUTAWAY_STRATEGY_ID,
       '' PUTAWAY_STRATEGY_CODE,
       0 PUTAWAY_ZONE_CODE,
       '' PUTAWAY_LOCATION_CODE,
       0 ALLOCATE_STRATEGY_ID,
       '' ALLOCATE_STRATEGY_CODE,
       '' LOT_ATTRIBUTE1_LABEL,
       '' LOT_ATTRIBUTE2_LABEL,
       '' LOT_ATTRIBUTE3_LABEL,
       '' LOT_ATTRIBUTE4_LABEL,
       '' LOT_ATTRIBUTE5_LABEL,
       '' LOT_ATTRIBUTE6_LABEL,
       '' LOT_ATTRIBUTE7_LABEL,
       '' LOT_ATTRIBUTE8_LABEL,
       '' LOT_ATTRIBUTE9_LABEL,
       '' LOT_ATTRIBUTE10_LABEL,
       '' LOT_ATTRIBUTE11_LABEL,
       '' LOT_ATTRIBUTE12_LABEL,
       'Y' ACTIVE,
       '' REMARK,
       COMPANY_ID,
       0 WAREHOUSE_ID,
       'N' DEL_FLAG,
       '-1' CREATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') CREATE_TIME,
       '-1' UPDATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') UPDATE_TIME,
       0 UPDATE_VERSION,
       '' DESCRIPTION
  from sku_t
);


-- ----------------------------
-- 企业包装
-- ----------------------------
create or replace view wms.ent_pack_v as
(
select DISTINCT 0 PACK_ID,
       PACK_CODE,
       PACK_DESCR,
       UOM,
       QTY,
       UOM_INNER,
       QTY_INNER,
       UOM_CASE,
       QTY_CASE,
       volume_inner,
       weight_gross_inner,
       weight_net_inner,
       weight_tare_inner,
       volume_case,
       weight_gross_case,
       weight_net_case,
       weight_tare_case,
       'Y' ACTIVE,
       '' REMARK,
       COMPANY_ID,
       0 WAREHOUSE_ID,
       'N' DEL_FLAG,
       '-1' CREATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') CREATE_TIME,
       '-1' UPDATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') UPDATE_TIME,
       0 UPDATE_VERSION,
       '' DESCRIPTION
  from pack_t
);


-- ----------------------------
-- 企业包装
-- ----------------------------
create or replace view ent_invetnroy_onhand_v as
(
select 0 INVENTORY_ONHAND_ID,
       0 OWNER_ID,
       ONHAND.OWNER_CODE,
       0 SKU_ID,
       ONHAND.SKU_CODE,
       ONHAND.SKU_ALIAS,
       SKU.PACK_CODE,
       LOT.LOT_ATTRIBUTE1,
       LOT.LOT_ATTRIBUTE2,
       LOT.LOT_ATTRIBUTE3,
       LOT.LOT_ATTRIBUTE4,
       LOT.LOT_ATTRIBUTE5,
       LOT.LOT_ATTRIBUTE6,
       LOT.LOT_ATTRIBUTE7,
       LOT.LOT_ATTRIBUTE8,
       LOT.LOT_ATTRIBUTE9,
       LOT.LOT_ATTRIBUTE10,
       LOT.LOT_ATTRIBUTE11,
       LOT.LOT_ATTRIBUTE12,
       SUM(QUANTITY_ONHAND) QUANTITY_ONHAND,
       SUM(QUANTITY_ALLOCATED) QUANTITY_ALLOCATED,
       SUM(QUANTITY_LOCKED) QUANTITY_LOCKED,
       SUM(CEIL(QUANTITY_ONHAND/DECODE(PACK.QTY_CASE,0,1,PACK.QTY_CASE))) QTY_CASE,
       SUM(QUANTITY_ONHAND * SKU.VOLUME) VOLUME,
       SUM(QUANTITY_ONHAND * SKU.WEIGHT_GROSS) WEIGHT_GROSS,
       SUM(QUANTITY_ONHAND * SKU.WEIGHT_NET) WEIGHT_NET,
       SUM(QUANTITY_ONHAND * SKU.WEIGHT_TARE) WEIGHT_TARE,
       0 LOCATION_ID,
       '' LOCATION_CODE,
       0 LPN_ID,
       '' LPN_NUMBER,
       0 LOT_ID,
       '' LOT_NUMBER,
       ONHAND.COMPANY_ID,
       ONHAND.WAREHOUSE_ID,
       'N' DEL_FLAG,
       '-1' CREATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') CREATE_TIME,
       '-1' UPDATE_BY,
       to_date('1999-01-01','YYYY-MM-DD') UPDATE_TIME,
       0 UPDATE_VERSION,
       '' DESCRIPTION
  from inventory_onhand_t onhand
       inner join sku_t sku on onhand.sku_id = sku.sku_id 
       inner join pack_t pack on sku.pack_id = pack.pack_id
       inner join lot_attribute_t lot on onhand.lot_id = lot.lot_attribute_id
  group by 
        ONHAND.OWNER_CODE,
        ONHAND.SKU_CODE,
        ONHAND.SKU_ALIAS,
        SKU.PACK_CODE,
        LOT.LOT_ATTRIBUTE1,
        LOT.LOT_ATTRIBUTE2,
        LOT.LOT_ATTRIBUTE3,
        LOT.LOT_ATTRIBUTE4,
        LOT.LOT_ATTRIBUTE5,
        LOT.LOT_ATTRIBUTE6,
        LOT.LOT_ATTRIBUTE7,
        LOT.LOT_ATTRIBUTE8,
        LOT.LOT_ATTRIBUTE9,
        LOT.LOT_ATTRIBUTE10,
        LOT.LOT_ATTRIBUTE11,
        LOT.LOT_ATTRIBUTE12,
        ONHAND.COMPANY_ID,
        ONHAND.WAREHOUSE_ID
);


