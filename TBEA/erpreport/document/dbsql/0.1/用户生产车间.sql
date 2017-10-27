create or replace view CUX_USERDEP_V as
SELECT DISTINCT bd.department_code, 
       bd.description,
       org.user_name
  FROM apps.bom_departments bd, 
       apps.org_organization_definitions mp,
       (SELECT ood.organization_name inv_org_name, -- 库存组织名
               fu.user_name
          FROM apps.fnd_user                     fu,
               apps.fnd_user_resp_groups_direct  usr_resp,
               apps.fnd_responsibility_tl        t,
               apps.fnd_profile_option_values    fpov,
               apps.hr_operating_units           hou,
               apps.org_organization_definitions ood,
               apps.bom_departments              bd
         WHERE fu.user_id = usr_resp.user_id
           and bd.organization_id = ood.organization_id
           AND usr_resp.responsibility_id = t.responsibility_id
           AND fpov.profile_option_id = 1991 --'MO：业务实体'
           AND fpov.level_value = t.responsibility_id
           AND fpov.level_id = 10003 -- resp level
           AND t.language = 'ZHS'
           AND fpov.profile_option_value = hou.organization_id
           AND ood.operating_unit = hou.organization_id) org
 WHERE bd.organization_id = mp.organization_id
   AND mp.organization_name = org.inv_org_name