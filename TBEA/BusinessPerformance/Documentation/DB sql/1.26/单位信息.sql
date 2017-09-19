update jygk_dwxx set name ='众和公司(事业部)' where id = 60000
alter table jygk_dwxx add unique (name)
alter table jygk_account add unique (name)