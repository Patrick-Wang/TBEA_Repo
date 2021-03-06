create or replace view CUX_USERLEDGER_V as
SELECT  ur.user_name,
		gl.ledger_id,
		gl.name ledger_name
  FROM apps.fnd_profile_values_resp_v fpv,
       apps.fnd_profile_options_vl    fpo,
       apps.fnd_responsibility_vl     fr,
	   apps.gl_ledgers gl,	
       (SELECT frv.responsibility_id,
               fu.user_name
          FROM apps.fnd_user_resp_groups_direct fur
              ,apps.fnd_user                    fu 
              ,apps.fnd_responsibility_vl       frv 
         WHERE fu.user_id = fur.user_id 
           AND fur.responsibility_application_id = frv.application_id 
           AND fur.responsibility_id = frv.responsibility_id) ur
 WHERE fpv.level_value = fr.responsibility_id
   AND fpv.profile_option_id = fpo.profile_option_id
   AND fpo.profile_option_name = 'GL_SET_OF_BKS_ID'
   AND fr.RESPONSIBILITY_ID = ur.responsibility_id
   AND gl.ledger_id = fpv.profile_option_value