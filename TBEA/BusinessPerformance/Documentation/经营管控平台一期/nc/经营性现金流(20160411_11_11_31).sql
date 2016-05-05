---------------------------经营性现金流（当月值）--------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd9.m10138 xssptglwsd, --销售商品、提供劳务收到的现金
       imd9.m10052 sdsffh, --收到的税费返还
       imd5.m10025 sddqtyjyhdygxj, --收到的其他与经营活动有关的现金
       imd9.m10181 fksdxj, --其中：罚款所收到的现金
       imd5.m10008 zfbzsd, --其中：政府补助所收到的现金
       imd9.m10242 sdbdwtbbzj, --其中：收到本单位向外投标退回所收到的投标保证金
       imd9.m10148 sdwdwtbbzj, --其中：收到外单位投标保证金所收到的现金
       imd9.m10100 rcywjzthssdxj, --其中：日常业务借支退回所收到的现金
       imd5.m10053 yhcklxssdxj, --其中：银行存款利息所收到到的现金
       imd5.m10025 sddqtyjyhdygxj, --其中：收到的其他与经营活动有关的现金
       imd9.m10142 jyhdxjlr, --现金流入小计
       imd9.m10150 gmspjslwszfxj, --购买商品、接受劳务所支付的现金
       imd5.m10062 zfgzxj, --支付给职工以及为职工支付的现金
       imd9.m10309 zfgxsf, --支付的各项税费
       imd9.m10210 zfqtjyhdygxj, --支付的其他与经营活动有关的现金
       imd9.m10337 zfbdwtbbzj, --其中：本单位向外投标所支付的投标保证金
       imd5.m10040 tfwdwtbbzj, --其中：退付外单位投标保证金所支付的现金
       imd9.m10111 dlzxfzfxj, --其中：代理咨询费所支付的现金
       imd9.m10198 zbfwfzfxj, --其中：中标服务费所支付的现金
       imd9.m10177 rcywjzzfxj, --其中：日常业务借支所支付的现金
       imd5.m10059 yhxgywsxfzfxj, --其中：银行相关业务手续费所支付的现金
       imd5.m10039 qtjyhdygxj, --其中：支付的其他与经营活动有关的现金
       imd5.m10061 jyhdxjlc, --现金流出小计
       imd9.m10029 jyhdcsdxjllje --经营活动产生的现金流量净额
  from iufo_measure_data_9hzo24a7 imd9
  left join iufo_measure_data_56m8ewc1 imd5
    on imd9.alone_id = imd5.alone_id
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd5.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
/*   and iui.unit_name like '%新疆变压器厂%'
   and substr(inputdate, 1, 7) = '2016-03'*/


---------------------------经营性现金流（本年累计数）--------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd5.m10034 xssptglwsdlj, --销售商品、提供劳务收到的现金(本年累计数)
       imd9.m10056 sdsffhlj, --收到的税费返还(本年累计数)
       imd9.m10304 sddqtyjyhdygxjlj, --收到的其他与经营活动有关的现金(本年累计数)
       imd9.m10178 fksdxjlj, --其中：罚款所收到的现金(本年累计数)
       imd9.m10197 zfbzsdlj, --其中：政府补助所收到的现金(本年累计数)
       imd9.m10283 sdbdwtbbzjlj, --其中：收到本单位向外投标退回所收到的投标保证金(本年累计数)
       imd9.m10037 sdwdwtbbzjlj, --其中：收到外单位投标保证金所收到的现金(本年累计数)
       imd9.m10345 rcywjzthssdxjlj, --其中：日常业务借支退回所收到的现金(本年累计数)
       imd9.m10232 yhcklxssdxjlj, --其中：银行存款利息所收到到的现金(本年累计数)
       imd5.m10035 sddqtyjyhdygxjlj, --其中：收到的其他与经营活动有关的现金(本年累计数)
       imd9.m10120 jyhdxjlrlj, --现金流入小计(本年累计数)
       imd9.m10228 gmspjslwszfxjlj, --购买商品、接受劳务所支付的现金(本年累计数)
       imd9.m10008 zfgzxjlj, --支付给职工以及为职工支付的现金(本年累计数)
       imd9.m10066 zfgxsflj, --支付的各项税费(本年累计数)
       imd5.m10046 zfqtjyhdygxjlj, --支付的其他与经营活动有关的现金(本年累计数)
       imd5.m10052 zfbdwtbbzjlj, --其中：本单位向外投标所支付的投标保证金(本年累计数)
       imd9.m10306 tfwdwtbbzjlj, --其中：退付外单位投标保证金所支付的现金(本年累计数)
       imd9.m10108 dlzxfzfxjlj, --其中：代理咨询费所支付的现金(本年累计数)
       imd9.m10224 zbfwfzfxjlj, --其中：中标服务费所支付的现金(本年累计数)
       imd9.m10110 rcywjzzfxjlj, --其中：日常业务借支所支付的现金(本年累计数)
       imd9.m10124 yhxgywsxfzfxjlj, --其中：银行相关业务手续费所支付的现金(本年累计数)
       imd5.m10046 qtjyhdygxjlj, --其中：支付的其他与经营活动有关的现金(本年累计数)
       imd5.m10064 jyhdxjlclj, --现金流出小计(本年累计数)
       imd9.m10114 jyhdcsdxjlljelj --经营活动产生的现金流量净额(本年累计数)
  from iufo_measure_data_9hzo24a7 imd9
  left join iufo_measure_data_56m8ewc1 imd5
    on imd9.alone_id = imd5.alone_id
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd5.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
/*and iui.unit_name like '%新疆变压器厂%'
and substr(inputdate,1,7) = '2016-03'*/
