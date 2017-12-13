create or replace view cux_purorder_v as
select
      rownum id,
      PANV.FULL_NAME person, --采购员
       pha.segment1 order_num, --采购订单号
       pha.CREATION_DATE create_date, --创建时间
       (select          a.meaning
  FROM  APPS.fnd_lookup_values  a
 WHERE a.lookup_type = 'AUTHORIZATION STATUS'
   AND a.lookup_code = pha.authorization_status
   AND a.LANGUAGE = 'ZHS' ) AS approve_status, --审批状态
       PV.VENDOR_NAME vender, --供应商名称
       pla.LINE_NUM line_num, --采购订单行号
       msi.SEGMENT1 materiel_code, --物料编码
       PLA.ITEM_DESCRIPTION materiel_intr, --物料描述
       pla.unit_meas_lookup_code unit, --单位
       pla.quantity num, --订单数量
       pla.GLOBAL_ATTRIBUTE1 tax_rate, --税码
       pla.UNIT_PRICE unit_price, --单价
       pla.UNIT_PRICE * pla.quantity amount, --不含税金额
       pla.GLOBAL_ATTRIBUTE2 tax_unit_price, --含税单价
       pla.GLOBAL_ATTRIBUTE3 tax_amount, --含税总额
       plla.PROMISED_DATE promise_date,--承诺日期

       plla.NEED_BY_DATE EDD, --预计交货日期
       (select min(rt1.TRANSACTION_DATE)
          from apps.rcv_transactions rt1
         where rt1.TRANSACTION_TYPE = 'RECEIVE'
           and rt1.PO_HEADER_ID = pha.PO_HEADER_ID
           and rt1.PO_LINE_ID = pla.PO_LINE_ID
           and rt1.PO_LINE_LOCATION_ID = plla.LINE_LOCATION_ID) REC_DATE, --  接收日期
       (select MIN(aia.ACCOUNTING_DATE)
          from apps.ap_invoice_lines_all aia
         WHERE AIA.PO_LINE_LOCATION_ID = PLLA.LINE_LOCATION_ID) AS bill_date, --开票日期
       plla.QUANTITY ord_num, --已订购数量
       plla.QUANTITY_BILLED bill_num, --已开单数量
       decode(plla.RECEIVING_ROUTING_ID, 1, '是', '否') require_check, --要求检验
       --未质检数量
       nvl((pla.quantity -
           (select sum(rt1.QUANTITY)
               from apps.rcv_transactions rt1
              where rt1.TRANSACTION_TYPE = 'ACCEPT'
                and rt1.PO_HEADER_ID = pha.PO_HEADER_ID
                and rt1.PO_LINE_ID = pla.PO_LINE_ID
                and rt1.PO_LINE_LOCATION_ID = plla.LINE_LOCATION_ID) -
           (select sum(rt1.QUANTITY)
               from apps.rcv_transactions rt1
              where rt1.TRANSACTION_TYPE = 'REJECT'
                and rt1.PO_HEADER_ID = pha.PO_HEADER_ID
                and rt1.PO_LINE_ID = pla.PO_LINE_ID
                and rt1.PO_LINE_LOCATION_ID = plla.LINE_LOCATION_ID)),
           0) as uncheck_num,

       nvl(((select sum(rt1.QUANTITY)
               from apps.rcv_transactions rt1
              where rt1.TRANSACTION_TYPE = 'ACCEPT'
                and rt1.PO_HEADER_ID = pha.PO_HEADER_ID
                and rt1.PO_LINE_ID = pla.PO_LINE_ID
                and rt1.PO_LINE_LOCATION_ID = plla.LINE_LOCATION_ID) +
           (select sum(rt1.QUANTITY)
               from apps.rcv_transactions rt1
              where rt1.TRANSACTION_TYPE = 'REJECT'
                and rt1.PO_HEADER_ID = pha.PO_HEADER_ID
                and rt1.PO_LINE_ID = pla.PO_LINE_ID
                and rt1.PO_LINE_LOCATION_ID = plla.LINE_LOCATION_ID)),
           0) as checked_num, --已质检数量

       nvl((select sum(rt1.QUANTITY)
             from apps.rcv_transactions rt1
            where rt1.TRANSACTION_TYPE = 'ACCEPT'
              and rt1.PO_HEADER_ID = pha.PO_HEADER_ID
              and rt1.PO_LINE_ID = pla.PO_LINE_ID
              and rt1.PO_LINE_LOCATION_ID = plla.LINE_LOCATION_ID),
           0) as qualified_num, --合格数量
       nvl((select sum(rt1.QUANTITY)
             from apps.rcv_transactions rt1
            where rt1.TRANSACTION_TYPE = 'REJECT'
              and rt1.PO_HEADER_ID = pha.PO_HEADER_ID
              and rt1.PO_LINE_ID = pla.PO_LINE_ID
              and rt1.PO_LINE_LOCATION_ID = plla.LINE_LOCATION_ID),
           0) as unqualified_num, --不合格数量
       plla.QUANTITY_CANCELLED cancel_num, --已取消数量
       plla.QUANTITY_RECEIVED rec_num, --已接收数量
       pla.GLOBAL_ATTRIBUTE2 * plla.QUANTITY_RECEIVED rec_amount,--含税总价
       (select OOD.organization_NAME
          from apps.rcv_transactions rt1, apps.org_organization_definitions ood
         where rt1.TRANSACTION_TYPE = 'RECEIVE'
           and rt1.PO_HEADER_ID = pha.PO_HEADER_ID
           and rt1.PO_LINE_ID = pla.PO_LINE_ID
           and rt1.PO_LINE_LOCATION_ID = plla.LINE_LOCATION_ID
           AND RT1.ORGANIZATION_ID = OOD.ORGANIZATION_ID
           AND ROWNUM = 1) AS rec_org, --接收组织
       nvl(（select sum(moqd.primary_transaction_quantity) from
          apps.mtl_onhand_quantities_detail moqd where
           moqd.organization_id = msi.organization_id and
           moqd.INVENTORY_ITEM_ID = msi.INVENTORY_ITEM_ID ）,
           0) as stock_num, --现有量
       nvl(（select sum(moqd.primary_transaction_quantity) from
           apps.mtl_onhand_quantities_detail moqd where
           moqd.organization_id = msi.organization_id and
           moqd.INVENTORY_ITEM_ID = msi.INVENTORY_ITEM_ID ）,
           0) as stock_available_num,
       pla.ATTRIBUTE6 figure_num,--图号
       pra.project_number contract_num, --项目号
     pha.ORG_ID   ---OU ID
  from apps.po_headers_all pha,
       apps.PO_LINES_ALL PLA,
       apps.PO_LINE_LOCATIONS_all plla,
       apps.po_vendors pv,
       apps.po_agents_name_v panv,
       apps.mtl_system_items_b msi,
       (select * from apps.mtl_parameters mp where mp.ORGANIZATION_code = 'MST') MST,
       apps.PO_DISTRIBUTIONS_ALL pda,
       apps.pjm_projects_all_v pra
 where PHA.PO_HEADER_ID = PLA.PO_HEADER_ID
   and PLLA.PO_HEADER_ID = PHA.PO_HEADER_ID
   and pLLa.Po_Line_Id = pla.po_line_id
   AND PHA.VENDOR_ID = PV.VENDOR_ID
   and panv.BUYER_ID = pha.agent_id
   and pla.item_id = msi.inventory_item_id
   AND MSI.ORGANIZATION_ID = MST.ORGANIZATION_ID
   and pla.po_line_id = pda.po_line_id
   and plla.line_location_id = pda.line_location_id
   and pda.project_id = pra.project_id(+)