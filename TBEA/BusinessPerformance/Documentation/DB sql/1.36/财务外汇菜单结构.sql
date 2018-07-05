insert into [navigator_item] (name, extend, parent) values
  ('财务外汇管理-录入', 0, 167),
  ('财务外汇管理-导入', 0, 167),
  ('财务外汇管理-查看', 0, 167);


  insert into navigator_positive_auth (itemId, authId) values
  ((select id from [navigator_item] where name = '财务外汇管理-录入'), 0),
  ((select id from [navigator_item] where name = '财务外汇管理-导入'), 0),
  ((select id from [navigator_item] where name = '财务外汇管理-查看'), 0)

  update [navigator_item] set parent = (select id from [navigator_item] where name = '财务外汇管理-录入') where id in 
  (169, 170, 175, 177, 179, 181) 
    update [navigator_item] set parent = (select id from [navigator_item] where name = '财务外汇管理-导入') where id in 
  (168) 
  
    update [navigator_item] set parent = (select id from [navigator_item] where name = '财务外汇管理-查看') where id in 
  (171,172,174,176, 178, 180, 182) 