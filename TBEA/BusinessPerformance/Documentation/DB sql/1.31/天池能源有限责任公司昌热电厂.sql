alter table tcnycsdc_year_plan_zb add dwid int;
alter table tcnycsdc_month_plan_zb add dwid int;
alter table tcnycsdc_season_plan_zb add dwid int;
alter table tcnycsdc_day_plan_zb add dwid int;

update tcnycsdc_year_plan_zb set dwid = 705;
update tcnycsdc_month_plan_zb set dwid = 705;
update tcnycsdc_season_plan_zb set dwid = 705;
update tcnycsdc_day_plan_zb set dwid = 705;
