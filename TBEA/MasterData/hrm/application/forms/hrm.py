#!/usr/bin/env python
# -*- coding: utf-8 -*-

from flask_wtf import Form
from wtforms import StringField, SubmitField
# from wtforms.validators import DataRequired


class UserIdForm(Form):
    user_id = StringField('请输入待查询员工身份证：')
    submit = SubmitField('查询')