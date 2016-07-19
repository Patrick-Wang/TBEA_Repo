#!/usr/bin/env python
# -*- coding: utf-8 -*-

#  @ file : account.py
#  @ time : 2016/7/19 14:08
#  @ author : Patrick Wang

from flask import Blueprint

blueprint = Blueprint('account_blueprint', __name__)


@blueprint.route('/login', methods=['GET', 'POST'])
def user_login():
    pass
