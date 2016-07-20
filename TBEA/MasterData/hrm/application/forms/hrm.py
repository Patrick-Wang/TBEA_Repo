#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : hrm.py
#  @ time : 2016/7/20 11:53
#  @ author : Patrick Wang

from flask_wtf import Form
from wtforms import StringField, SubmitField
from wtforms.validators import DataRequired, Length


class UserIdForm(Form):
    user_id = StringField('请输入员工身份证号：', validators=[DataRequired(), Length(18,18)])
    submit = SubmitField('查询')
