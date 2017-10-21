create or replace view CUX_USERCOMP_V as
SELECT ur.user_name,
       ffv.flex_value,
       ffv.description
  FROM apps.fnd_flex_value_sets  ffs
      ,apps.fnd_flex_values_vl   ffv
      ,(SELECT substr(frv.responsibility_name,0,4) comp,
               fu.user_name
                    FROM apps.fnd_user_resp_groups_direct fur
                    ,apps.fnd_user                    fu
                    ,apps.fnd_responsibility_vl       frv
                    WHERE fu.user_id = fur.user_id
                        AND fur.responsibility_application_id = frv.application_id
                        AND fur.responsibility_id = frv.responsibility_id) ur
 WHERE ffs.flex_value_set_id = ffv.flex_value_set_id
   and ffs.flex_value_set_name = 'TBEA_COA_CO'
   and ffv.flex_value = ur.comp;
						
