SELECT rownum, --���--
       mmt.source_project_id,
       (SELECT project_number
          FROM apps.pjm_projects_v pp
         WHERE 1 = 1
           AND pp.project_id = mmt.source_project_id) product_number, --������--
       --mmt.transaction_source_id,
       we.wip_entity_name, --������--
       msi.segment1, --���ϱ���--
       msi.description, --����˵��--
       msi.attribute2 project_model, --��Ʒ�ͺ�--
       NULL pic_model, --ͼ��  ����--
       order_info.customer_name order_company, --������λ--
       mmt.transaction_date, --�깤���ʱ��
       mmt.transaction_quantity, --̨��--
       order_info.unit_price * mmt.transaction_quantity output_value, --��ֵ--
       msi.attribute5 vol,--��̨����--
       mmt.transaction_quantity * msi.attribute5  output, --����--
       NULL electric_level, --��ѹ�ȼ� ����--
       --apps.cux_public_pkg.get_person_name(p_user_id => mmt.created_by) resp_peorson, --�깤��--
       (SELECT papf.full_name
  FROM apps.per_all_people_f papf,
       apps.fnd_user         fu
 WHERE papf.person_id = fu.employee_id
   AND SYSDATE BETWEEN nvl(papf.effective_start_date,
                           SYSDATE - 1) AND nvl(papf.effective_end_date,
                                                SYSDATE + 1)
   AND fu.user_id = mmt.created_by) resp_peorson, --�깤��--
       (SELECT bd.attribute1
          FROM (SELECT row_number() over(PARTITION BY 1 ORDER BY wo.operation_seq_num ASC) rn,
                       wo.department_id
                  FROM apps.wip_operations wo
                 WHERE 1 = 1
                   AND wo.wip_entity_id = we.wip_entity_id) t,
               apps.bom_departments bd
         WHERE 1 = 1
           AND t.department_id = bd.department_id
           AND t.rn = 1) product_center --��������  ���޸�--

  FROM apps.mtl_material_transactions mmt,
       apps.wip_entities we,
       apps.mtl_system_items_b msi,
       (SELECT oola.project_id,
               ooha.sold_to_org_id,
               unit_selling_price / 1.17 / decode(ordered_quantity,
                                                  0,
                                                  1,
                                                  ordered_quantity) unit_price,
               (SELECT ac.customer_name
                  FROM apps.ar_customers ac
                 WHERE 1 = 1
                   AND ac.customer_id = ooha.sold_to_org_id) customer_name
          FROM apps.oe_order_headers_all ooha,
               apps.oe_order_lines_all   oola
         WHERE 1 = 1
           AND ooha.header_id = oola.header_id) order_info
 WHERE 1 = 1
   AND order_info.project_id(+) = mmt.source_project_id
   AND we.wip_entity_id = mmt.transaction_source_id
   AND msi.inventory_item_id = mmt.inventory_item_id
   AND msi.organization_id = mmt.organization_id
   AND transaction_type_id IN (44,
                               17);
